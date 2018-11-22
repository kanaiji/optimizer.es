package web.application.com.common.log;
/*package com.softbankc.gw.log;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.sun.management.OperatingSystemMXBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class LogCenterService extends org.apache.log4j.AppenderSkeleton {

    private static final Logger logger = Logger.getLogger(LogCenterService.class);

    private boolean isLux = true;
    private boolean isUse = false;

    private String server = null;
    private RedisPool pool = null;

    private Client client = null;
    private List<String> loglist = new ArrayList<>();
    private final Object loglock = new Object();

    public LogCenterService() {
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
            isLux = false;
        }
        File conf = new File(isLux ? "/app/gw.conf" : "C:/sysconf/gw.conf");
        if (conf.exists()) {
            try {
                final InputStream in = new FileInputStream(conf);
                final Properties prop = new Properties();
                prop.load(in);
                isUse = Boolean.parseBoolean(prop.getProperty("logcenter.enable"));
                if (isUse) {
                    server = prop.getProperty("server").toUpperCase();
                    GenericObjectPoolConfig pconfig = new GenericObjectPoolConfig();
                    pconfig.setMaxIdle(6);
                    pconfig.setMaxTotal(6);
                    pool = new RedisPool(pconfig, prop.getProperty("logcenter.redis.host"),
                            Integer.parseInt(prop.getProperty("logcenter.redis.port")), prop.getProperty("logcenter.redis.pwd"));
                    client = new Client(prop.getProperty("logcenter.loghub.url"), prop.getProperty("logcenter.loghub.id"), prop.getProperty("logcenter.loghub.key"));
                    pool.returnClient(pool.getClient());
                    new HeartBeat().start();
                    new SendLog().start();
                }
                in.close();
            } catch (Exception e) {
                logger.error("*SYS|LOGCENTER-FAIL|启动日志中心服务失败", e);
            }
        }
        if (!isUse) {
            logger.warn("*SYS|LOGCENTER-FAIL|未开启日志中心服务");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent event) {
        try {
            if (isUse) {
                String level = event.getLevel().toString();
                if ("DEBUG".equals(level)) {
                    return;
                }
                String msg = event.getRenderedMessage();
                if (StringUtils.isBlank(msg)) {
                    return;
                }
                if (!msg.startsWith("SYS|") && !msg.startsWith("TRADE|") && !msg.startsWith("TRIGGER|")) {
                    return;
                }
                if (msg.startsWith("TRIGGER|")) {
                    StringBuilder sb = new StringBuilder(512);
                    sb.append(System.currentTimeMillis()).append("|").append(server).append("|").append(level).append("|");
                    sb.append(msg);
                    Jedis redis = pool.getClient();
                    try {
                        redis.lpush("GWTRIGGER", sb.toString());
                        pool.returnClient(redis);
                    } catch (Exception e) {
                        logger.warn("SYS|LOGCENTER-ERROR-REDIS|推送订单触发失败|" + sb.toString());
                        pool.returnBrokenClient(redis);
                        redis = pool.getClient();
                        try {
                            redis.lpush("GWTRIGGER", sb.toString());
                            pool.returnClient(redis);
                        } catch (Exception e2) {
                            logger.error("SYS|LOGCENTER-ERROR-REDIS|推送订单触发失败|" + sb.toString());
                            pool.returnBrokenClient(redis);
                        }
                    }
                    return;
                }
                StringBuilder sb = new StringBuilder(1024);
                sb.append(System.currentTimeMillis()).append("|").append(server).append("|").append(level).append("|");
                sb.append(msg);
                if (ThreadFlag.threadLocal.get() != null) {
                    sb.append("|").append(ThreadFlag.threadLocal.get());
                }
                ThrowableInformation err = event.getThrowableInformation();
                if (err != null && err.getThrowable() != null) {
                    sb.append("|").append(err.getThrowable().getClass().getName()).append(":").append(err.getThrowable().getMessage());
                    StackTraceElement[] lines = err.getThrowable().getStackTrace();
                    int len = lines.length;
                    for (int i = 0; i < len; i++) {
                        StackTraceElement line = lines[i];
                        if (line.getClassName().indexOf("softbankc") >= 0) {
                            sb.append("|").append(line.getClassName()).append(".").append(line.getMethodName()).append(":")
                                    .append(line.getLineNumber());
                            break;
                        }
                    }
                }
                synchronized (loglock) {
                    loglist.add(sb.toString());
                }
                if (msg.startsWith("SYS|") && ("WARN".equals(level) || "ERROR".equals(level))) {
                    Jedis redis = pool.getClient();
                    try {
                        redis.lpush("SWIFTLOG", sb.toString());
                        pool.returnClient(redis);
                    } catch (Exception e) {
                        pool.returnBrokenClient(redis);
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("*SYS|LOGCENTER-FAIL|发送日志失败", e);
        }
    }

    class HeartBeat extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                    StringBuilder sb = new StringBuilder(128);
                    sb.append(System.currentTimeMillis()).append("|").append(server).append("|HEARTBEAT|GATEWAY|").append("CPU:")
                            .append((int) (osmxb.getSystemCpuLoad() * 100));
                    Jedis redis = pool.getClient();
                    try {
                        redis.lpush("SWIFTLOG", sb.toString());
                        pool.returnClient(redis);
                    } catch (Exception e) {
                        pool.returnBrokenClient(redis);
                        throw e;
                    }
                } catch (Exception e) {
                    logger.error("*SYS|LOGCENTER-FAIL|发送心跳失败", e);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void sendLog() {
        try {
            List<String> newlist = new ArrayList<>();
            List<String> tmp = null;
            synchronized (loglock) {
                tmp = loglist;
                loglist = newlist;
            }
            logger.info("*SYS|LOGCENTER-INFO|发送日志|" + tmp.size() + "条");
            Vector<LogItem> syslog_info = new Vector<>();
            Vector<LogItem> syslog_warn = new Vector<>();
            Vector<LogItem> syslog_error = new Vector<>();
            Vector<LogItem> tradelog_info = new Vector<>();
            Vector<LogItem> tradelog_warn = new Vector<>();
            Vector<LogItem> tradelog_error = new Vector<>();
            for (String log : tmp) {
                String[] info = StringUtils.split(log, "|");
                LogItem logItem = new LogItem((int) (Long.parseLong(info[0]) / 1000));
                if ("SYS".equals(info[3])) {
                    logItem.PushBack("server", info[1]);
                    logItem.PushBack("type", info[4]);
                    logItem.PushBack("all", log);
                    if ("INFO".equals(info[2])) {
                        syslog_info.add(logItem);
                    } else if ("WARN".equals(info[2])) {
                        syslog_warn.add(logItem);
                    } else {
                        syslog_error.add(logItem);
                    }
                } else {
                    logItem.PushBack("server", info[1]);
                    logItem.PushBack("type", info[4]);
                    logItem.PushBack("tradeno", info[5]);
                    logItem.PushBack("all", log);
                    if ("INFO".equals(info[2])) {
                        tradelog_info.add(logItem);
                    } else if ("WARN".equals(info[2])) {
                        tradelog_warn.add(logItem);
                    } else {
                        tradelog_error.add(logItem);
                    }
                }
            }
            if (syslog_info.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "sys", "INFO", server, syslog_info));
            }
            if (syslog_warn.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "sys", "WARN", server, syslog_warn));
            }
            if (syslog_error.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "sys", "ERROR", server, syslog_error));
            }
            if (tradelog_info.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "trade", "INFO", server, tradelog_info));
            }
            if (tradelog_warn.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "trade", "WARN", server, tradelog_warn));
            }
            if (tradelog_error.size() != 0) {
                client.PutLogs(new PutLogsRequest("gwlog", "trade", "ERROR", server, tradelog_error));
            }
            logger.info("*SYS|LOGCENTER-INFO|发送日志完成");
        } catch (Exception e) {
            logger.error("*SYS|LOGCENTER-FAIL|发送日志失败", e);
        }
    }

    class SendLog extends Thread {
        public void run() {
            while (true) {
                sendLog();

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {

                }
            }
        }
    }

}
*/