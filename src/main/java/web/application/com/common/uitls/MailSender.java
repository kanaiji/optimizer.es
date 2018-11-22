package web.application.com.common.uitls;   
  
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import web.application.com.common.constans.CommonConst;


  
/**   
* 简单邮件（不带附件的邮件）发送器   

*/    
public class MailSender extends Authenticator {    
/**   
  * 以文本格式发送邮件   
  * @param mailInfo 待发送的邮件的信息   
  */    
	
	
	
    String userName=null;   
    String password=null;   
        
    public MailSender(){   //覆盖Authenticator
    }   
    
    public MailSender(String username, String password) {    //重写Authenticator
        this.userName = username;    
        this.password = password;    
    }    
    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    } 
	
	             
	
	//发送邮件
	public static final void sendMail(String toAddress,String subject,String contents) throws Exception{
		
		
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost(CommonConst.Mail_Server_Host);    
		mailInfo.setMailServerPort(CommonConst.Mail_Server_Port);    
		mailInfo.setValidate(true);    
		mailInfo.setUserName(CommonConst.Mail_UserName);    
		mailInfo.setPassword(CommonConst.Mail_Password);//您的邮箱密码    
		mailInfo.setFromAddress(CommonConst.Mail_FromAddress);    
		mailInfo.setToAddress(toAddress);    
		mailInfo.setSubject(subject); //邮件标题 
		mailInfo.setContent(contents); //邮件内容

		MailSender mailSender =new MailSender();
	    
		mailSender.sendHtmlMail(mailInfo);

	}
	

	
	
    public  boolean sendTextMail(MailSenderInfo mailInfo) {    
      // 判断是否需要身份认证    
      MailSender authenticator = null;
   
      Properties pro = mailInfo.getProperties();   
      if (mailInfo.isValidate()) {    
      // 如果需要身份认证，则创建一个密码验证器    
        authenticator = new MailSender(mailInfo.getUserName(), mailInfo.getPassword());    
      }   
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      // 创建邮件发送者地址    
      Address from = new InternetAddress(mailInfo.getFromAddress());    
      // 设置邮件消息的发送者    
      mailMessage.setFrom(from);    
      // 创建邮件的接收者地址，并设置到邮件消息中    
      Address to = new InternetAddress(mailInfo.getToAddress());    
      mailMessage.setRecipient(Message.RecipientType.TO,to);    
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());    
      // 设置邮件消息的主要内容    
      String mailContent = mailInfo.getContent();    
      mailMessage.setText(mailContent);    
      // 发送邮件    
      Transport.send(mailMessage);   
      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();    
      }    
      return false;    
    }    
       
    /**   
      * 以HTML格式发送邮件   
      * @param mailInfo 待发送的邮件信息   
      */    
    public  boolean sendHtmlMail(MailSenderInfo mailInfo){    
      // 判断是否需要身份认证    
    	MailSender authenticator = null;   
      Properties pro = mailInfo.getProperties();   
      //如果需要身份认证，则创建一个密码验证器     
      if (mailInfo.isValidate()) {    
        authenticator = new MailSender(mailInfo.getUserName(), mailInfo.getPassword());   
      }    
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      // 创建邮件发送者地址    
      Address from = new InternetAddress(mailInfo.getFromAddress());    
      // 设置邮件消息的发送者    
      mailMessage.setFrom(from);    
      // 创建邮件的接收者地址，并设置到邮件消息中    
      Address to = new InternetAddress(mailInfo.getToAddress());    
      // Message.RecipientType.TO属性表示接收者的类型为TO    
      mailMessage.setRecipient(Message.RecipientType.TO,to);    
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());    
      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
      Multipart mainPart = new MimeMultipart();    
      // 创建一个包含HTML内容的MimeBodyPart    
      BodyPart html = new MimeBodyPart();    
      // 设置HTML内容    
      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
      mainPart.addBodyPart(html);    
      // 将MiniMultipart对象设置为邮件内容    
      mailMessage.setContent(mainPart);    
      // 发送邮件    
      Transport.send(mailMessage);    
      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();    
      }    
      return false;    
    }    
    
    
    
    
    public static void main(String[] args) {
		
    	
    	String address = "937334241@qq.com";
    	String subject = "测试";
    	String contents = "测试邮件";
    	try {
			sendMail(address, subject, contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	}
    
    
    
    
    
}   