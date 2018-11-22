//package web.application.com.common.uitls;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.SocketException;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//
//import web.application.com.common.config.SystemConfig;
//
//
///**
// * <b>功能说明: 文件及文件夹工具类
// */
//public class FileUtil {
//	private static final Log log = LogFactory.getLog(FileUtil.class);
//
//	/**
//	 * 判断是否是目录
//	 * 
//	 * @param dirPath
//	 * @return
//	 */
//	public static boolean isDir(String dirPath) {
//		if (StringUtils.isBlank(dirPath)) {
//			return false;
//		}
//		File file = new File(dirPath);
//		if (!file.exists() || !file.isDirectory()) {
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 创建文件夹,只能创建最底层一级的文件夹,如果上级文件夹不存在,则创建失败
//	 * 
//	 * @param dirPath
//	 */
//	public static boolean mkDir(String dirPath) {
//		if (StringUtils.isBlank(dirPath)) {
//			return false;
//		}
//
//		File file = new File(dirPath);
//		if (file.exists()) {
//			return true;
//		} else {
//			return file.mkdir();
//		}
//	}
//
//	/**
//	 * 创建文件夹,可以创建多级文件夹,如果最底层一级之上的文件夹也不存在,则同时生成上一级文件夹
//	 * 
//	 * @param dirPath
//	 * @return
//	 */
//	public static boolean mkDirAndSubDir(String dirPath) {
//		if (StringUtils.isBlank(dirPath)) {
//			return false;
//		}
//
//		File file = new File(dirPath);
//		if (file.exists()) {
//			return true;
//		} else {
//			return file.mkdirs();
//		}
//	}
//
//	// 创建单个文件
//	public static boolean createFile(String filePath) {
//		File file = new File(filePath);
//		if (file.exists()) {// 判断文件是否存在
//			System.out.println("目标文件已存在" + filePath);
//			return false;
//		}
//		if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
//			System.out.println("目标文件不能为目录！");
//			return false;
//		}
//		if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
//			// 如果目标文件所在的文件夹不存在，则创建父文件夹
//			System.out.println("目标文件所在目录不存在，准备创建它！");
//			if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
//				System.out.println("创建目标文件所在的目录失败！");
//				return false;
//			}
//		}
//		try {
//			if (file.createNewFile()) {// 创建目标文件
//				System.out.println("创建文件成功:" + filePath);
//				return true;
//			} else {
//				System.out.println("创建文件失败！");
//				return false;
//			}
//		} catch (IOException e) {// 捕获异常
//			e.printStackTrace();
//			System.out.println("创建文件失败！" + e.getMessage());
//			return false;
//		}
//	}
//
//	// 创建目录
//	public static boolean createDir(String destDirName) {
//		File dir = new File(destDirName);
//		if (dir.exists()) {// 判断目录是否存在
//			System.out.println("创建目录失败，目标目录已存在！");
//			return false;
//		}
//		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
//			destDirName = destDirName + File.separator;
//		}
//		if (dir.mkdirs()) {// 创建目标目录
//			System.out.println("创建目录成功！" + destDirName);
//			return true;
//		} else {
//			System.out.println("创建目录失败！");
//			return false;
//		}
//	}
//
//	/**
//	 * 写文件
//	 * 
//	 * @param newStr
//	 *            新内容
//	 * @throws IOException
//	 */
//	public static boolean writeTxtFile(String fileName, String newStr) throws IOException {
//		// 先读取原有文件内容，然后进行写入操作
//		boolean flag = false;
//		String filein = newStr;
//		String temp = "";
//
//		FileInputStream fis = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//
//		FileOutputStream fos = null;
//		PrintWriter pw = null;
//		try {
//			// 文件路径
//			File file = new File(fileName);
//			// 将文件读入输入流
//			fis = new FileInputStream(file);
//			isr = new InputStreamReader(fis);
//			br = new BufferedReader(isr);
//			StringBuffer buf = new StringBuffer();
//
//			// 保存该文件原有的内容
//			for (int j = 1; (temp = br.readLine()) != null; j++) {
//				buf = buf.append(temp);
//			}
//			buf.append(filein);
//
//			fos = new FileOutputStream(file);
//			pw = new PrintWriter(fos);
//			pw.write(buf.toString().toCharArray());
//			pw.flush();
//			flag = true;
//		} catch (IOException e1) {
//			// TODO 自动生成 catch 块
//			throw e1;
//		} finally {
//			if (pw != null) {
//				pw.close();
//			}
//			if (fos != null) {
//				fos.close();
//			}
//			if (br != null) {
//				br.close();
//			}
//			if (isr != null) {
//				isr.close();
//			}
//			if (fis != null) {
//				fis.close();
//			}
//		}
//		return flag;
//	}
//
//	public static void main(String[] args) {
//		// System.out.println("" +
//		// mkDirAndSubDir("/Users/peter/Desktop/redis/test/test/fast/test.txt"));
//		String fileName = "F:/aa/bb/1.txt";
//		createFile("F:/aa/bb/1.txt");
//		try {
//			writeTxtFile(fileName, "2017-02-01 22:10:15|payment001|10");
//			writeTxtFile(fileName, "2017-02-01 22:10:15|payment001|11");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * @param file
//	 * @param basePath
//	 * @param fileName
//	 * @param bankWayCode
//	 * @return
//	 */
//	public static boolean fleUpload(InputStream input, String basePath, String fileName,
//			String bankChannelCode) {// 创建ftp客户端
//		FTPClient ftpClient = new FTPClient();
//		ftpClient.setControlEncoding("UTF-8");
//		String hostname = SystemConfig.FTP_HOST;
//		int port = SystemConfig.FTP_PORT;
//		String username = SystemConfig.FTP_USERNAME;
//		String password = SystemConfig.FTP_PASSWORD;
//		try {
//			// 链接ftp服务器
//			ftpClient.connect(hostname, port);
//			// 登录ftp
//			ftpClient.login(username, password);
//			int reply = ftpClient.getReplyCode();
//			// 如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限下面有详细的解释。
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftpClient.disconnect();
//				return false;
//			}
//			// 设置PassiveMode传输
//			ftpClient.enterLocalPassiveMode();
//			// 设置以二进制流的方式传输
//			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//			File file = new File(basePath + bankChannelCode);
//			// 如果不存在就创建
//			if (!file.exists()) {
//				System.out.println("=================");
//				System.out.println("=================basePath"+basePath);
//				// ftpClient不能一次创建多层目录,只能进入目录后再创建下一级目录.
//				// 进入目录
//				ftpClient.changeWorkingDirectory(basePath);
//				// 创建
//				ftpClient.makeDirectory(bankChannelCode);
//				ftpClient.changeWorkingDirectory(bankChannelCode);
//			} else {
//				ftpClient.changeWorkingDirectory(basePath + bankChannelCode);
//			}
//			// 保存文件
//			ftpClient.storeFile(fileName, input);
//			input.close();
//			ftpClient.logout();
//			return true;
//		} catch (SocketException e) {
//			log.error(e, e);
//			e.printStackTrace();
//			return false;
//		} catch (IOException e) {
//			log.error(e, e);
//			e.printStackTrace();
//			return false;
//		} finally {
//			if (ftpClient.isConnected()) {
//				try {
//					ftpClient.disconnect();
//				} catch (IOException ioe) {
//					ioe.printStackTrace();
//				}
//				return true;
//			}
//
//		}
//	}
//}
