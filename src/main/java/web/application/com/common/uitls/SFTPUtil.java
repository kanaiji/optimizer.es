//package web.application.com.common.uitls;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.Vector;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.ChannelSftp.LsEntry;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpATTRS;
//import com.jcraft.jsch.SftpException;
//
//import web.application.com.common.config.SystemConfig;
//  
///** 
// * @author Agui 
// */  
//public class SFTPUtil {  
//	
//  private static final Log LOG = LogFactory.getLog(FtpUtil.class);
//  
//  private static   String host = "120.236.150.202";  
//  private static   int port = 8022;  
//  private static   String username = "wanjiashangyeguanli";  
//  private static   String password = "latch_insist!cobalt";  
//  private static   String directory = "reckon";  
//    
////  private static final  String downloadFile ="502050002716_" + new SimpleDateFormat("yyyy-mm-dd").format(new Date())+".txt" ;  
//  private static final  String downloadFile ="502050002716_20170830"+".txt" ;  
//  
//  private static final  String saveFile = "c:"+downloadFile;  
//  
//  private static final  String uploadFile = "d:/cc.jpg";  
//  private static final  String deleteFile = "cc.jpg";  
//  
//  
//  public   SFTPUtil() {}
//  public   SFTPUtil(String host ,int port ,String username , String password ) {
//	 this.host = host ;
//	 this.port = port ;
//	 this.username = username ;
//	 this.password = password ;
//  }
//  
//  
//    /** 
//     * 连接sftp服务器 
//     * @param host 主机 
//     * @param port 端口 
//     * @param username 用户名 
//     * @param password 密码 
//     * @return 
//     */  
//    public static ChannelSftp connect(String host, int port, String username,  
//            String password) {  
//        ChannelSftp sftp = null;  
//        try {  
//            JSch jsch = new JSch();  
//            jsch.getSession(username, host, port);  
//            Session sshSession = jsch.getSession(username, host, port);  
//            System.out.println("Session created.");  
//            sshSession.setPassword(password);  
//            Properties sshConfig = new Properties();  
//            sshConfig.put("StrictHostKeyChecking", "no");  
//            sshSession.setConfig(sshConfig);  
//            sshSession.connect();  
//            System.out.println("Session connected.");  
//            System.out.println("Opening Channel.");  
//            Channel channel = sshSession.openChannel("sftp");  
//            channel.connect();  
//            sftp = (ChannelSftp) channel;
//        	Class cl = ChannelSftp.class;
//			Field f =cl.getDeclaredField("server_version");
//			f.setAccessible(true);
//			f.set(sftp, 2);
//            System.out.println("Connected to " + host + ".");  
//        } catch (Exception e) {  
//            e.printStackTrace() ;  
//        }  
//        return sftp;  
//    }  
//  
//    /** 
//     * 上传文件 
//     * @param directory 上传的目录 
//     * @param uploadFile 要上传的文件 
//     * @param sftp 
//     */  
//    public void upload(String directory, String uploadFile, ChannelSftp sftp) {  
//        try {  
//            sftp.cd(directory);  
//            File file=new File(uploadFile);  
//            sftp.put(new FileInputStream(file), file.getName());  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//    /** 
//     * 下载文件 
//     * @param directory 下载目录 
//     * @param downloadFile 下载的文件 
//     * @param saveFile 存在本地的路径 
//     * @param sftp 
//     */  
//    public File download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {  
//        
//    	File file=new File(saveFile);  
//    	try {  
//            sftp.cd(directory);  
//            sftp.get(downloadFile, new FileOutputStream(file));  
//        } catch (Exception e) {  
//        	LOG.error("下载队长文件并保存到本地异常 ：" , e   );
//        	throw new RuntimeException("下载对账文件异常...........") ;
//        }  
//        return file ;
//    }  
//  
//    /** 
//     * 删除文件 
//     * @param directory 要删除文件所在目录 
//     * @param deleteFile 要删除的文件 
//     * @param sftp 
//     */  
//    public void delete(String directory, String deleteFile, ChannelSftp sftp) {  
//        try {  
//            sftp.cd(directory);  
//            sftp.rm(deleteFile);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//    /** 
//     * 列出目录下的文件 
//     * @param directory 要列出的目录 
//     * @param sftp 
//     * @return 
//     * @throws SftpException 
//     */    
//    @SuppressWarnings("unchecked")  
//    public Vector<LsEntry> listFiles(String directory, ChannelSftp sftp) throws SftpException{  
//        return sftp.ls(directory);  
//    }  
//  
//	public static void main(String[] args) throws Exception {
//		SFTPUtil sf = new SFTPUtil();
//		ChannelSftp sftp = sf.connect(host, port, username, password); // 获取连接
//
////		sf.upload(directory, uploadFile, sftp); // 上传文件
//		
////		sf.upload(SystemConfig.FTP_PROJECT, file.getInputStream(), "licence_"+001+"."+zzz, "busi", "img");
//
////		sf.download(directory, downloadFile, saveFile, sftp); //下载文件
//
////		sf.delete(directory, deleteFile, sftp); // 删除文件
//
//		Vector<LsEntry> files = sf.listFiles(directory, sftp); // 查看文件列表
//		for (LsEntry file : files) {
//			System.out.println(file.getFilename());
//		}
//
//	} 
//	
//	/**
//	 * 读取FTP指定路径下文件
//	 * 
//	 * @param ftpPath
//	 * @param fileName
//	 * @return
//	 */
//	@SuppressWarnings("static-access")
//	public  List<String[]> readFile(String directory , String fileName , String saveFile ,SFTPUtil  sftp ) {
//		
//		InputStream ins = null;
//		ChannelSftp sftpClient = null ; 
//		BufferedReader bufferedReader = null ;
//		List<String[]> list = new ArrayList<>();
//		LOG.info("开始读取绝对路径" + directory + "文件!,文件名:" + fileName + "  保存到 ：" + saveFile );
//		try {
//		    sftpClient = sftp.connect(host, port, username, password); // 获取连接
//		    		
//		    File file = sftp.download(directory, fileName , saveFile, sftpClient ); //下载文件	
//		    
//		    ins = new FileInputStream(file) ;
//		    
////		    ins = new FileInputStream("/agui/yilian/502050002716_20171213.txt") ;
//		    
//			bufferedReader = new BufferedReader(new InputStreamReader(ins, "GBK"));
//			String lineTxt = null;
//			while ((lineTxt = bufferedReader.readLine()) != null) {
//				// 去掉空的行
//				if (StringUtil.isEmpty(lineTxt)) {
//					continue;
//				}
//				String[] strings = lineTxt.split("\\|");
//				list.add(strings);
//			}
//			bufferedReader.close(); 
//		} catch (Exception e) {
//			LOG.error("没有找到" + directory + "文件", e);
//		}finally{
//			try {
//				ins.close();
//				sftpClient.exit();
//			} catch (Exception e) {
//				LOG.error("关闭连接异常", e);
//			}
//		}
//
//		return list;
//	}
//	
//	
//	
//   /** 
//    * 上传文件  
//    * @param tag 标签  tq-台签
//    * @param inputStream 二维码生成的流
//    * @param name 文件名
//    * @param sftp
//    */
//    public String uploadFile(String tag, InputStream inputStream, String name) { 
//    	StringBuffer bf=new StringBuffer();
//        try { 
//    		ChannelSftp sftp = connect(host, port, username, password); // 获取连接
//    		if("tq".equals(tag)){
//    			sftp.cd("/tq");
//    			bf.append("/tq");
//    		}else{
//    			sftp.cd("/img");
//    			bf.append("/tq");
//    			
//    		}
//            sftp.put(inputStream, name);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        bf.append(name);
//        return bf.toString();
//    } 
//    
//    
////    public static void main(String[] args) {
////		InputStream ins = null;
////		FTPClient ftpClient = null ;
////		BufferedReader bufferedReader = null ;
////		try {
////		    ftpClient = getFTPClient("192.168.1.203", "agui","agui", 21);
////			ftpClient.setControlEncoding("UTF-8"); // 中文支持
////			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
////			ftpClient.enterLocalPassiveMode();
////			ftpClient.changeWorkingDirectory("");
////			ins = ftpClient.retrieveFileStream("");
//////			retrieveFileStream(new String(dirPath[1].getBytes("UTF-8"), "ISO-8859-1"));
////			
////			/**
////			 * agui  测试 本地文件 读取 对账文件
////			 */
//////			ins = new FileInputStream("F:\\502050002507_20170627.txt")  ;
////			bufferedReader = new BufferedReader(new InputStreamReader(ins, "GBK"));
////		} catch (IOException e) {
////			LOG.error(e);
////			LOG.info("FTP的IP地址或端口错误,请正确配置。");
////		}
////			
////	}
//    
//    
//    /**
//	 * 函数功能说明 ： 上传文件到SFTP服务器 修改者名字： Toreal-lrx 修改日期： 2015-10-29 修改内容： @参数：@param
//	 * projectName 所属项目 @参数：@param file 上传原文件 @参数：@param model
//	 * 图片所属模块:adv、goods、notice、product、shop、merchant @参数：@param flag
//	 * 图片类型：swf、img、media、zip @参数：@return @参数：@throws
//	 * IOException @return：String @throws
//	 */
//	public static String upload(String projectName, InputStream inputStream, String fileName, String model, String flag)
//			 {
//		LOG.info("开始上传文件到SFTP!");
//		ChannelSftp sftp = null;
//		// 存放路径
//		String destPath = "";
//		try {
//			// 连接SFTP服务器
//			sftp = connect(SystemConfig.SFTP_HOST, SystemConfig.SFTP_PORT,SystemConfig.SFTP_USERNAME,SystemConfig.SFTP_PASSWORD); // 获取连接
//			System.out.println("==host=="+SystemConfig.SFTP_HOST+"==port=="+SystemConfig.SFTP_PORT+"==password=="+SystemConfig.SFTP_PASSWORD+"==username=="+SystemConfig.SFTP_USERNAME);
//			// 中文支持
//			sftp.setFilenameEncoding(SystemConfig.SFTP_CHARSET);
//			// 设置PassiveMode传输
////			ftpClient.enterLocalPassiveMode();
//			// 设置以二进制流的方式传输
////			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//			// ftpClient不能一次创建多层目录,只能进入目录后再创建下一级目录.
//			// 创建目录文件夹.
//			System.out.println(projectName + model);
//			if(!isDirExist(projectName + model,sftp)){
//				System.out.println(1);
//				sftp.mkdir(projectName + model);
//			}
//			// 进入指定目录.
//			sftp.cd(projectName + model);
//			
//			if(!isDirExist(projectName + model + DateUtils.getFormatedDate("/yyyy"),sftp)){
//				System.out.println(2);
//				sftp.mkdir(projectName + model + DateUtils.getFormatedDate("/yyyy"));
//			}
//		
//			sftp.cd(projectName + model + DateUtils.getFormatedDate("/yyyy"));
//			
//			if(!isDirExist(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd"),sftp)){
//				System.out.println(3);
//				sftp.mkdir(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd"));
//			}
//			
//			sftp.cd(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd"));
//			
//			if(!isDirExist(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag,sftp)){
//				System.out.println(4);
//				sftp.mkdir(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag);
//			}
//			
//			sftp.cd(projectName + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag);
//			sftp.put(inputStream, fileName);
//			inputStream.close();
//			LOG.info("上传文件" + fileName + "到SFTP成功!");
//			// FTP图片全路径
//			destPath = SystemConfig.SFTP_DEST_PATH + "/" + model + DateUtils.getFormatedDate("/yyyy/MM_dd/") + flag + "/"
//					+ fileName;
//		} catch (SftpException e) {
//			// TODO Auto-generated catch block
//			System.out.println("sftp:"+e.toString());
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("io:"+e.toString());
//			e.printStackTrace();
//		} finally {
//			try {
//				sftp.disconnect();
//			} catch (Exception e) {
//				LOG.error(e);
//				destPath = "";
//			}
//		}
//		return destPath;
//	}
//	
//	/** 
//	  * 判断目录是否存在 
//	  */  
//	 public static boolean isDirExist(String directory,	ChannelSftp sftp) {  
//	  boolean isDirExistFlag = false;  
//	  try {  
//	   SftpATTRS sftpATTRS = sftp.lstat(directory);  
//	   isDirExistFlag = true;  
//	   return sftpATTRS.isDir();  
//	  } catch (Exception e) {  
//	   if (e.getMessage().toLowerCase().equals("no such file")) {  
//	    isDirExistFlag = false;  
//	   }  
//	  }  
//	  return isDirExistFlag;  
//	 } 
//	
//	
//	
//}  