package web.application.com.common.uitls;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 类功能说明：下载工具类
 */
public class DownloadUtil {

	private DownloadUtil() {
		
	}
	
	/**
	 * 函数功能说明 ：打包ZIP下载文件
	 * 参数：@param response
	 * 参数：@param agentNo
	 * 参数：@param imgUrls
	 * 参数：@return <br/>
	 * return：String <br/>
	 * @throws Exception 
	 */
	public static void zipDownload(HttpServletResponse response, String agentNo, List<String> imgUrls) throws Exception {
		// 本地保存设置
		response.addHeader("Content-Disposition", "attachment; filename="
		        + URLEncoder.encode(agentNo, "UTF-8")+".zip");
		response.setContentType("application/x-zip-compressed");
		// 向本地写文件
		ServletOutputStream sos=response.getOutputStream();
		ZipOutputStream zipOut  = new ZipOutputStream(new BufferedOutputStream(sos)); 
		for(int i=0; i < imgUrls.size(); i++){
			String imgUrl = imgUrls.get(i);
		    ZipEntry entry = new ZipEntry(imgUrl.substring(imgUrl.lastIndexOf("/")+1)); 
		    zipOut.putNextEntry(entry);   
		    URL url = new URL(imgUrl);   
		    URLConnection conn = url.openConnection();  
		    InputStream bis = conn.getInputStream();  
		    if(bis!=null){
		        int readLen = -1;
		        byte[] buf = new byte[1024];
		        while ((readLen = bis.read(buf, 0, 1024)) != -1) {
		            zipOut.write(buf, 0, readLen);
		        }
		        bis.close();   
		    }
		}
		zipOut.flush();
		zipOut.close();
	}
}
