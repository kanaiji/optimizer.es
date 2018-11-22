package web.application.com.common.uitls;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import web.application.com.common.config.SystemConfig;



/**
 * 类功能说明：上传文件到FTP工具类
 */
public class FtpUtil {

	private static final Log LOG = LogFactory.getLog(FtpUtil.class);

	// public static final String FTP_CHARSET = SystemConfig.FTP_CHARSET.trim();
	// public static final String FTP_HOST = SystemConfig.FTP_HOST.trim();
	// public static final int FTP_PORT =
	// Integer.valueOf(SystemConfig.FTP_PORT.trim()).intValue();
	// public static final String FTP_USER_NAME =
	// SystemConfig.FTP_USERNAME.trim();
	// public static final String FTP_PASSWORD =
	// SystemConfig.FTP_PASSWORD.trim();
	// public static final String FTP_DEST_PATH =
	// SystemConfig.FTP_DEST_PATH.trim();

	private FtpUtil() {

	}

	/**
	 * 函数功能说明 ： 上传文件到FTP服务器 修改者名字： Toreal-lrx 修改日期： 2015-10-29 修改内容： @参数：@param
	 * projectName 所属项目 @参数：@param file 上传原文件 @参数：@param model
	 * 图片所属模块:adv、goods、notice、product、shop、merchant @参数：@param flag
	 * 图片类型：swf、img、media、zip @参数：@return @参数：@throws
	 * IOException @return：String @throws
	 */
	public static String upload(String projectName, InputStream inputStream, String fileName, String model, String flag)
			throws IOException {
		LOG.info("开始上传文件到FTP!");
		FTPClient ftpClient = null;
		// 存放路径
		String destPath = "";
		try {
			// 连接FTP服务器
			ftpClient = getFTPClient(SystemConfig.FTP_HOST, SystemConfig.FTP_PASSWORD, SystemConfig.FTP_USERNAME,
					SystemConfig.FTP_PORT);
			// 中文支持
			ftpClient.setControlEncoding(SystemConfig.FTP_CHARSET);
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// ftpClient不能一次创建多层目录,只能进入目录后再创建下一级目录.
			// 创建目录文件夹.
			ftpClient.makeDirectory(projectName + model);
			// 进入指定目录.
			ftpClient.changeWorkingDirectory(projectName + model);
			ftpClient.makeDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy"));
			ftpClient.changeWorkingDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy"));
			ftpClient.makeDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd"));
			ftpClient.changeWorkingDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd"));
			ftpClient.makeDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag);
			ftpClient.changeWorkingDirectory(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			LOG.info("上传文件" + fileName + "到FTP成功!");
			// FTP图片全路径
			destPath = SystemConfig.FTP_DEST_PATH + "/" + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag + "/"
					+ fileName;
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				LOG.error(e);
				destPath = "";
			}
		}
		return destPath;
	}

	/**
	 * 读取ftp上面的文件，下载到本地目录
	 * 
	 * @param ftpPath
	 * @param fileName
	 * @param localFile
	 */
	public static void down(String ftpPath, String fileName, String localFile) {

		LOG.info("开始读取绝对路径" + ftpPath + "文件!,文件名:" + fileName + ",目标文件夹:" + localFile);
		try {
			FTPClient ftpClient = getFTPClient(SystemConfig.FTP_HOST, SystemConfig.FTP_PASSWORD,
					SystemConfig.FTP_USERNAME, SystemConfig.FTP_PORT);
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			OutputStream is = new FileOutputStream(localFile);
			ftpClient.retrieveFile(fileName, is);
		} catch (Exception e) {
			LOG.error("没有找到" + ftpPath + "文件", e);
		}
	}

	/**
	 * 读取FTP指定路径下文件
	 * 
	 * @param ftpPath
	 * @param fileName
	 * @return
	 */
	public static List<String[]> readFile(String ftpPath, String fileName) {
		InputStream ins = null;
		FTPClient ftpClient = null ;
		BufferedReader bufferedReader = null ;
		List<String[]> list = new ArrayList<>();
		LOG.info("开始读取绝对路径" + ftpPath + "文件!,文件名:" + fileName);
		try {
		    ftpClient = getFTPClient(SystemConfig.FTP_HOST, SystemConfig.FTP_PASSWORD,
					SystemConfig.FTP_USERNAME, SystemConfig.FTP_PORT);
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			ins = ftpClient.retrieveFileStream(fileName);
//			retrieveFileStream(new String(dirPath[1].getBytes("UTF-8"), "ISO-8859-1"));
			
			/**
			 * agui  测试 本地文件 读取 对账文件
			 */
//			ins = new FileInputStream("F:\\502050002507_20170627.txt")  ;
			bufferedReader = new BufferedReader(new InputStreamReader(ins, "GBK"));
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// 去掉空的行
				if (StringUtil.isEmpty(lineTxt)) {
					continue;
				}
				String[] strings = lineTxt.split("\\|");
				list.add(strings);
			}
			bufferedReader.close(); 
		} catch (Exception e) {
			LOG.error("没有找到" + ftpPath + "文件", e);
		}finally{
			try {
				ins.close();
				ftpClient.completePendingCommand();
			} catch (Exception e) {
				LOG.error("关闭连接异常", e);
			}
		}

		return list;
	}

	/**
	 * 获取FTPClient对象
	 * 
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpPassword, String ftpUserName, int ftpPort) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				LOG.info("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			} else {
				LOG.info("FTP连接成功。");
			}
		} catch (IOException e) {
			LOG.error(e);
			LOG.info("FTP的IP地址或端口错误,请正确配置。");
		}
		return ftpClient;
	}
	
	
	
	public static void main(String[] args) {
		InputStream ins = null;
		FTPClient ftpClient = null ;
		BufferedReader bufferedReader = null ;
		try {
		    ftpClient = getFTPClient("192.168.1.203", "agui","agui", 21);
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory("");
			ins = ftpClient.retrieveFileStream("");
//			retrieveFileStream(new String(dirPath[1].getBytes("UTF-8"), "ISO-8859-1"));
			
			/**
			 * agui  测试 本地文件 读取 对账文件
			 */
//			ins = new FileInputStream("F:\\502050002507_20170627.txt")  ;
			bufferedReader = new BufferedReader(new InputStreamReader(ins, "GBK"));
		} catch (IOException e) {
			LOG.error(e);
			LOG.info("FTP的IP地址或端口错误,请正确配置。");
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
}
