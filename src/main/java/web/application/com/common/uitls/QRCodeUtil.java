package web.application.com.common.uitls;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import web.application.com.common.config.SystemConfig;

/**
 * 类功能说明：二维码工具类
 */
public class QRCodeUtil {

	// 字符编码
	//public static final String QRCODE_CHARSET = SystemConfig.QRCODE_CHARSET.trim();  
	// 二维码图片格式
	//public static final String QRCODE_FORMAT = SystemConfig.QRCODE_FORMAT.trim();  
    // 二维码尺寸  
	//public static final int QRCODE_SIZE = Integer.valueOf(SystemConfig.QRCODE_SIZE.trim()).intValue();  
    // LOGO宽度  
	//public static final int QRCODE_LOGO_WIDTH = Integer.valueOf(SystemConfig.QRCODE_LOGO_WIDTH.trim()).intValue();  
    // LOGO高度  
	//public static final int QRCODE_LOGO_HEIGHT = Integer.valueOf(SystemConfig.QRCODE_LOGO_HEIGHT.trim()).intValue();  
  
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, SystemConfig.QRCODE_CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, SystemConfig.QRCODE_SIZE, SystemConfig.QRCODE_SIZE,  
                hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  
            }  
        }  
        if (logoPath == null || "".equals(logoPath)) {  
            return image;  
        }  
        // 插入图片  
        QRCodeUtil.insertImage(image, logoPath, needCompress);  
        return image;  
    }  
  
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param logoPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {  
        File file = new File(logoPath);  
        if (!file.exists()) {  
            throw new Exception("logo file not found.");  
        }
        Image src = ImageIO.read(new File(logoPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > SystemConfig.QRCODE_LOGO_WIDTH) {  
                width = SystemConfig.QRCODE_LOGO_WIDTH;  
            }  
            if (height > SystemConfig.QRCODE_LOGO_HEIGHT) {  
                height = SystemConfig.QRCODE_LOGO_HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (SystemConfig.QRCODE_SIZE - width) / 2;  
        int y = (SystemConfig.QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     * 二维码文件名随机，文件名可能会有重复 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        mkdirs(destPath);  
        String fileName = new Random().nextInt(99999999) + "." + SystemConfig.QRCODE_FORMAT.toLowerCase();  
        ImageIO.write(image, SystemConfig.QRCODE_FORMAT, new File(destPath + "/" + fileName));  
        return fileName;  
    }  
    
    /** 
     * 生成二维码(内嵌LOGO) 
     * 调用者指定二维码文件名 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param fileName 
     *            二维码文件名 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath, String fileName, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        mkdirs(destPath);  
        fileName = fileName.substring(0, fileName.indexOf(".")>0?fileName.indexOf("."):fileName.length())   
                + "." + SystemConfig.QRCODE_FORMAT.toLowerCase();  
        ImageIO.write(image, SystemConfig.QRCODE_FORMAT, new File(destPath + "/" + fileName));  
        return fileName;  
    }  
  
    /** 
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir． 
     * (mkdir如果父目录不存在则会抛出异常) 
     * @param destPath 
     *            存放目录 
     */  
    public static void mkdirs(String destPath) {  
        File file = new File(destPath);  
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath) throws Exception {  
        return QRCodeUtil.encode(content, logoPath, destPath, false);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String destPath, boolean needCompress) throws Exception {  
        return QRCodeUtil.encode(content, null, destPath, needCompress);  
    }  
    
    /**
     * 函数功能说明 ：获取二维码输入流
     */
    public static InputStream getInputStream(String content, String logoPath, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        ByteArrayOutputStream bs =new ByteArrayOutputStream();
        ImageOutputStream output =ImageIO.createImageOutputStream(bs);
        ImageIO.write(image, SystemConfig.QRCODE_FORMAT, output); 
        InputStream inputStream =new ByteArrayInputStream(bs.toByteArray());
        return inputStream;  
    }
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static String encode(String content, String destPath) throws Exception {  
        return QRCodeUtil.encode(content, null, destPath, false);  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String logoPath, OutputStream output, boolean needCompress)  
            throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        ImageIO.write(image, SystemConfig.QRCODE_FORMAT, output);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */  
    public static void encode(String content, OutputStream output) throws Exception {  
        QRCodeUtil.encode(content, null, output, false);  
    }  
  
    /** 
     * 解析二维码 
     *  
     * @param file 
     *            二维码图片 
     * @return 
     * @throws Exception 
     */  
    public static String decode(File file) throws Exception {  
        BufferedImage image;  
        image = ImageIO.read(file);  
        if (image == null) {  
            return null;  
        }  
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        Result result;  
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, SystemConfig.QRCODE_CHARSET);  
        result = new MultiFormatReader().decode(bitmap, hints);  
        String resultStr = result.getText();  
        return resultStr;  
    }  
  
    /** 
     * 解析二维码 
     *  
     * @param path 
     *            二维码图片地址 
     * @return 
     * @throws Exception 
     */  
    public static String decode(String path) throws Exception {  
        return QRCodeUtil.decode(new File(path));  
    }  
  
    public static void main(String[] args) throws Exception {  
        String text = "http://www.xxx.com";  
        //不含Logo  
        //QRCodeUtil.encode(text, null, "e:\\", true);  
        //含Logo，不指定二维码图片名  
        //QRCodeUtil.encode(text, "e:\\csdn.jpg", "e:\\", true);  
        //含Logo，指定二维码图片名  
        QRCodeUtil.encode(text, "e:\\bigpay_icon.png", "e:\\", "qrcode", true);
    }  

}
