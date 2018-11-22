//package web.application.com.common.uitls;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSException;
//import com.aliyun.oss.model.BucketInfo;
//import com.aliyun.oss.model.GetObjectRequest;
//import com.aliyun.oss.model.OSSObjectSummary;
//import com.aliyun.oss.model.ObjectListing;
//import com.aliyun.oss.model.ObjectMetadata;
//
//public class OSSFile {
//	
//	private static Logger logger = LoggerFactory.getLogger(OSSFile.class);
//
//	// endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
//	// 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
//	// 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
//	// endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
//	// 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
//	//外网地址
//	private static String endpoint = "oss-cn-beijing.aliyuncs.com";
//	//内网地址
////	private static String endpoint = "oss-cn-beijing.aliyuncs.com-internal";
//
//	// accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
//	// 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
//	// 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
//	private static String accessKeyId = "LTAIR4cBkHQmyyyL";
//	private static String accessKeySecret = "TldITfe1Jx0bb7CiWgKZ85Ih5bc3MU";
//
//	// Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
//	// Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
//	private static String bucketName = "zhwj";
//
//	// Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 >
//	// OSS基本概念介绍”。
//	// Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
//	private static String firstKey = "my-first-key";
//
//	public static void openBucketAndUpFile(InputStream inputStream, String fileName) {
//
//		// 日志配置，OSS Java
//		// SDK使用log4j记录错误信息。示例程序会在工程目录下生成“oss-demo.log”日志文件，默认日志级别是INFO。
//		// 日志的配置文件是“conf/log4j.properties”，如果您不需要日志，可以没有日志配置文件和下面的日志配置。
//		// PropertyConfigurator.configure("conf/log4j.properties");
//
//		logger.info("Started");
//		// System.out.println("........ ........." + fileName+"....."+filepath);
//		// 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
//		// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
//
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		logger.info("连接阿里的oss--Start");
//
//		try {
//			// 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
//			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
//			BucketInfo info = ossClient.getBucketInfo(bucketName);
//			System.out.println("Bucket " + bucketName + "的信息如下：");
//			System.out.println("\t数据中心：" + info.getBucket().getLocation());
//			System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
//			System.out.println("\t用户标志：" + info.getBucket().getOwner());
//
//			// 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
//			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
//			// 创建上传Object的Metadata
//			ObjectMetadata meta = new ObjectMetadata();
//
//			meta.setCacheControl("no-cache");
//
//			// InputStream inputStream = new FileInputStream(filepath);
//			ossClient.putObject(bucketName, fileName, inputStream, meta);
//			System.out.println("Object：" + fileName + "存入OSS成功。");
//
//			// 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
//			// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
//			ObjectListing objectListing = ossClient.listObjects(bucketName);
//			List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
//
//		} catch (OSSException oe) {
//			oe.printStackTrace();
//		} catch (ClientException ce) {
//			ce.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			ossClient.shutdown();
//		}
//
//		logger.info("Completed");
//
//	}
//
//	// 下载文件
//	public static void DownFile(String filepath, String locationPath) throws Exception {
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		try {
//			// 下载object到文件
//			ossClient.getObject(new GetObjectRequest(bucketName, filepath), new File(locationPath));
//			System.out.println("下载成功");
//
//		} catch (OSSException oe) {
//			throw new RuntimeException();
//		} catch (ClientException ce) {
//			throw new RuntimeException();
//		} catch (Exception e) {
//			throw new RuntimeException();
//		} finally {
//			ossClient.shutdown();
//		}
//
//		logger.info("Completed");
//	}
//
//	// 验证文件是否存在
//	public static boolean checkFile(String filePath) {
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//		// Object是否存在
//		boolean found = ossClient.doesObjectExist(bucketName, filePath);
//
//		// 关闭client
//		ossClient.shutdown();
//
//		logger.info("Completed");
//
//		return found;
//	}
//
//	// 删除文件
//	public static void deleteFile(String filePath) {
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//		// Object是否存在
//		boolean found = ossClient.doesObjectExist(bucketName, filePath);
//		if (found) {
//			logger.info("----OSS该文件存在---");
//			ossClient.deleteObject(bucketName, filePath);
//		}
//		logger.info("OSS删除该文件完毕");
//		// 关闭client
//		ossClient.shutdown();
//		logger.info("Completed");
//	}
//
//	public static void main(String[] args) {
//
//		String path = "C:\\Users\\Agui\\Desktop\\tu.png";
//
//		String fileName = "AguiTest/agui.png";
//
//		/*try {
//			openBucketAndUpFile(new FileInputStream(path), fileName);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}*/
//
//		 deleteFile(fileName) ;
//
//		 
//		 try {
//		  
//		  DownFile("resources/pdf/00s110000e62633d897994e420171024706.pdf" ,
//		  "d:data/resources/110000/110101/WjH110101201807291015/dtb/Wj00S110101201807291001.dtb") ;
//		  
//		 } catch (Exception e) { System.out.println("  没有该文件 "); }
//		 
//
//		// System.out.println(checkFile("resources/pdf/00s1200001624b1cb78ac4d120171957907.pdf"));
//
//	}
//
//}
