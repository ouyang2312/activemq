package com.ouyang.activemq.test;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author oy
 * @description
 * @date 2019/11/6
 */
public class PictureEmailTest {

    private static String mailFrom = null;// 指明邮件的发件人
    private static String password_mailFrom = null;// 指明邮件的发件人登陆密码

    private static String mailTo = null; // 指明邮件的收件人

    private static String mailTittle = null;// 邮件的标题

    private static String mailText = null; // 邮件的文本内容

    private static String mail_host = null; // 邮件的服务器域名

    private static String photoSrc = null; // 发送图片的路径

    public static void main(String[] args) throws Exception {

        mailFrom = "1262014533@qq.com";
        password_mailFrom="qfdxzacucomvibah";
        mailTo = "907722435@qq.com";
        mailTittle="欧阳测试";
        mailText = "这是一个简单的邮件，代表你出bug了";
        mail_host="smtp.qq.com";
        photoSrc = "d://test/default.jpg";

        Properties prop = new Properties();
        prop.setProperty("mail.host", mail_host);// 需要修改
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
        ts.connect(mail_host, mailFrom, password_mailFrom);// 需要修改
        // 4、创建邮件
        Message message = createImageMail(session);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createImageMail
     * @Description: 生成一封邮件正文带图片的邮件
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createImageMail(Session session) throws Exception {
        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息

        message.setFrom(new InternetAddress(mailFrom)); // 发件人

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo)); // 收件人
        // 邮件标题
        message.setSubject(mailTittle);

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(mailText + "<img src='cid:xxx.jpg'>", "text/html;charset=UTF-8");// 需要修改
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(photoSrc));// 需要修改
        image.setDataHandler(dh);
        image.setContentID("xxx.jpg");// 需要修改
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();
        // 将创建好的邮件写入到F盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("D://test/ImageMail.eml"));// 需要修改
        // 返回创建好的邮件
        return message;
    }

}
