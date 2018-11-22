package com.cwca.customer.common.utils.emailCheck;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import java.io.IOException;
import java.util.logging.Logger;

/**
 *  *
 *  * 校验邮箱：1、格式是否正确 2、是否真实有效的邮箱地址
 *  * 步骤：
 *  * 1、从dns缓存服务器上查询邮箱域名对应的SMTP服务器地址
 *  * 2、尝试建立Socket连接
 *  * 3、尝试发送一条消息给SMTP服务器
 *  * 4、设置邮件发送者
 *  * 5、设置邮件接收者
 *  * 6、检查响应码是否为250(为250则说明这个邮箱地址是真实有效的)
 *  * @author Michael Ran
 *  *
 *  
 */
public class CheckEmailValidityUtil {
    private static final Logger logger = Logger.getLogger("CheckEmailValidityUtil");

    /**
     *      * @param email 待校验的邮箱地址
     *      * @return
     *     
     */

    public static boolean isEmailValid(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            logger.config("邮箱（" + email + "）校验未通过，格式不对!");
            return false;
        }
        String host = "";
        String hostName = email.split("@")[1];
        //Record: A generic DNS resource record. The specific record types
        //extend this class. A record contains a name, type, class, ttl, and rdata.
        Record[] result = null;
        SMTPClient client = new SMTPClient();
        try {
        // 查找DNS缓存服务器上为MX类型的缓存域名信息
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {//查找失败
                logger.config("邮箱（" + email + "）校验未通过，未找到对应的MX记录!");
                return false;
            } else {//查找成功
                result = lookup.getAnswers();
            }
            //尝试和SMTP邮箱服务器建立Socket连接
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
               // logger.info("SMTPClient try connect to host:" + host);

            //此connect()方法来自SMTPClient的父类:org.apache.commons.net.SocketClient
            //继承关系结构：org.apache.commons.net.smtp.SMTPClient-->org.apache.commons.net.smtp.SMTP-->org.apache.commons.net.SocketClient
            //Opens a Socket connected to a remote host at the current default port and
            //originating from the current host at a system assigned port. Before returning,
            //_connectAction_() is called to perform connection initialization actions.
            //尝试Socket连接到SMTP服务器
                client.connect(host);
                //Determine if a reply code is a positive completion response（查看响应码是否正常）.
                //All codes beginning with a 2 are positive completion responses（所有以2开头的响应码都是正常的响应）.
            //The SMTP server will send a positive completion response on the final successful completion of a command.
                System.out.println(client.getReplyCode());
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
            //断开socket连接
                    client.disconnect();
                    continue;
                } else {
                    logger.info("找到MX记录:" + hostName);
                    logger.info("建立链接成功：" + hostName);
                    break;
                }
            }
           // logger.info("SMTPClient ReplyString:" + client.getReplyString());
            String emailSuffix = "163.com";
            String emailPrefix = "qwefdf";
            String fromEmail = emailPrefix + "@" + emailSuffix;
            //Login to the SMTP server by sending the HELO command with the given hostname as an argument.
            //Before performing any mail commands, you must first login.
            //尝试和SMTP服务器建立连接,发送一条消息给SMTP服务器
            client.login(emailPrefix);
           // logger.info("SMTPClient login:" + emailPrefix + "...");
           // logger.info("SMTPClient ReplyString:" + client.getReplyString());

            //Set the sender of a message using the SMTP MAIL command,
            //specifying a reverse relay path.
            //The sender must be set first before any recipients may be specified,
            //otherwise the mail server will reject your commands.
            //设置发送者，在设置接受者之前必须要先设置发送者
            client.setSender(fromEmail);
            //logger.info("设置发送者 :" + fromEmail);
            //logger.info("SMTPClient ReplyString:" + client.getReplyString());

            //Add a recipient for a message using the SMTP RCPT command,
            //specifying a forward relay path. The sender must be set first before any recipients may be specified,
            //otherwise the mail server will reject your commands.
            //设置接收者,在设置接受者必须先设置发送者，否则SMTP服务器会拒绝你的命令
            client.addRecipient(email);
           // logger.info("设置接收者:" + email);
           // logger.info("SMTPClient ReplyString:" + client.getReplyString());
           // logger.info("SMTPClient ReplyCode：" + client.getReplyCode() + "(250表示正常)");
            if (250 == client.getReplyCode()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
            }
        }
        return false;
    }


    public static void main(String[] args) {

       /*String emails =
               "邮箱\n" +
                       "275086840@qq.com\n" +
                       "593954642@qq.com\n" +
                       "519024142@qq.com\n" +
                       "43854305@qq.com\n" +
                       "95025343@qq.com\n" +
                       "380392527@qq.com\n" +
                       "865302569@qq.com\n" +
                       "82480037@qq.com\n" +
                       "664806794@qq.com\n" +
                       "18027928@qq.com\n" +
                       "280209806@qq.com\n" +
                       "921790470@qq.com\n" +
                       "912575980@qq.com\n" +
                       "373974931@qq.com\n" +
                       "645524279@qq.com\n" +
                       "294967598@qq.com\n" +
                       "413876636@qq.com\n" +
                       "231351050@qq.com\n" +
                       "756574725@qq.com\n" +
                       "511401640@qq.com\n" +
                       "416000596@qq.com \n" +
                       "809558078@qq.com \n" +
                       "154315183@qq.com\n" +
                       "250429268@qq.com\n" +
                       "344200393@qq.com\n" +
                       "493177901@qq.com\n" +
                       "635235602@qq.com\n" +
                       "820401261@qq.com\n" +
                       "2991089477@qq.com\n" +
                       "9193381@qq.com\n" +
                       "2402415225@qq.com\n" +
                       "5565268@qq.com\n" +
                       "277170851@qq.com\n" +
                       "466687988@qq.com\n" +
                       "409656149@qq.com\n" +
                       "805058728@qq.com\n" +
                       "398467427@qq.com\n" +
                       "369532027@qq.com\n" +
                       "liyang2@ufida.com\n" +
                       "1421387361@qq.com\n" +
                       "1049463047@qq.com\n" +
                       "1063032995@qq.com\n" +
                       "652681374@qq.com\n" +
                       "449041172@qq.com\n" +
                       "994591217@qq.com\n" +
                       "405373480@qq.com\n" +
                       "244545561@qq.com\n" +
                       "632531251@qq.com\n" +
                       "554805622@qq.com\n" +
                       "12419340@qq.com \n" +
                       "306331562@qq.com\n" +
                       "790939648@qq.com\n" +
                       "1136985484@qq.com\n" +
                       "9666256@qq.com\n" +
                       "303439394@qq.com\n" +
                       "1016135036@qq.com \n" +
                       "634539574@qq.com\n" +
                       "1169763235@qq.com\n" +
                       "250710521@qq.com\n" +
                       "398185136@qq.com\n" +
                       "422088758@qq.com\n" +
                       "641144545@qq.com\n" +
                       "46653427@qq.com\n" +
                       "4693823@qq. com \n" +
                       "1317065482@qq.com\n" +
                       "328899850@qq.com\n" +
                       "271487089@qq.com\n" +
                       "282319588@qq.com\n" +
                       "249465744@qq.com \n" +
                       "1977377598@qq.com\n" +
                       "979778087@qq.com\n" +
                       "66829800@qq.com\n" +
                       "1062646933@qq.com \n" +
                       "187396943@qq.com\n" +
                       "1015771822@qq.com\n" +
                       "1453387472@qq.com\n" +
                       "56943553@qq.com\n" +
                       "969055892@qq.com\n" +
                       "40693287@qq.com\n" +
                       "791057173@qq.com  \n" +
                       "1416544200@qq.com\n" +
                       "790907011@qq.com\n" +
                       "1787044106@qq.com\n" +
                       "1158220545@qq.com\n" +
                       "540203017@qq.com\n" +
                       "582201017@qq.com\n" +
                       "417775617@qq.com\n" +
                       "1548992389@qq.com\n" +
                       "492196300@qq.com\n" +
                       "423442498@qq.com\n" +
                       "3416882143@qq.com\n" +
                       "392811273@qq.com\n" +
                       "1025031922@qq.com\n" +
                       "171908810@qq.com\n" +
                       "370306999@qq.com\n" +
                       "412180562@qq.com\n" +
                       "315368953@qq.com\n" +
                       "350255798@qq.com \n" +
                       "765134902@qq.com\n" +
                       "975653520@qq.com\n" +
                       "3541226940@qq.com\n" +
                       "104384998@qq.com\n" +
                       "123306475@qq.com\n" +
                       "317575893@qq.com\n" +
                       "121832356@qq.com\n" +
                       "984337250@qq.com\n" +
                       "188151757@qq.com\n" +
                       "164040104@qq.com\n" +
                       "2650697772@qq.com\n" +
                       "1546664069@qq.com\n" +
                       "32283461@qq.com\n" +
                       "553723104@qq.com\n" +
                       "37003707@qq.com\n" +
                       "455988879@qq.com\n" +
                       "262450635@qq.com\n" +
                       "58842598@qq.com\n" +
                       "572186165@qq.com\n" +
                       "481702114@qq.com\n" +
                       "834071066@qq.com\n" +
                       "396544703@qq.com\n" +
                       "849859180@qq.com\n" +
                       "87185492@qq.com\n" +
                       "zjw8428@163.com\n" +
                       "zhaoleishan@foxmail.com\n" +
                       "232498707@qq.com\n" +
                       "285907158@qq.com\n" +
                       "365193358@qq.com\n" +
                       "405728031@qq.com\n" +
                       "531371117@qq.com\n" +
                       " 515346862@qq.com\n" +
                       "1040882333@qq.com\n" +
                       "106048551@qq.com\n" +
                       "675613865@qq.com\n";
        String[] split = emails.split("\\n");
        System.out.println(split);
        for (String e:
                split) {
            if(isEmailValid(e.trim())){
                System.out.println(e);
            }
        }*///liyang2@ufida.com  1079684518@qq.com  369532027@qq.com
        System.out.println(isEmailValid("369532027@qq.com"));
        System.out.println(StringUtils.deleteWhitespace("369532027@qq.com"));
    }

//1.邮箱地址由于复制粘贴的格式原因，存在无法去除空白，空格的情况
    //2.地址有效，但个人设置造成
    //3.地址有效，但无法通过邮件服务器验证的，建议更换地址
}