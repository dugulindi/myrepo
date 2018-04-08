package com.miaoqian.bigdata.common;
import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.web.utils.HttpClientUnits;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2016/12/10.
 */
public class HMACSHA1 {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static String base64(String data){
        return base64(data.getBytes());
    }

    public static String base64(byte data[]){
        return (new BASE64Encoder()).encodeBuffer(data);
    }
    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1(String encryptText, String encryptKey) throws Exception
    {
        byte[] data=encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }

    public static void main(String[] args)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        String datastr = sdf.format(DateUtils.addHours(new Date(),-8));
        String signString = "GET" + "\n"
                + "\n"
                + "\n"
                + datastr + "\n"
                + "x-log-apiversion:0.6.0\nx-log-signaturemethod:hmac-sha1" + "\n"
                + "/logstores?logstoreName=&offset=0&size=1000";

        String Signature = base64(HmacSHA1(new String(signString.getBytes(ENCODING)),"n93DCjfe0MFagduWZP0vs9nziwSCHc"));
        HttpGet httpGet = new HttpGet("http://miaoqian-log-test.cn-qingdao.log.aliyuncs.com/logstores");
        httpGet.setHeader("Date",datastr);
        httpGet.setHeader("Host","miaoqian-log-test.cn-qingdao.log.aliyuncs.com");
//        httpGet.setHeader("x-log-apiversion","0.6.0");
//        httpGet.setHeader("x-log-signaturemethod","hmac-sha1");
        httpGet.setHeader("Authorization","LOG LTAI4Rxu73H5CFdA:"+Signature);
        HttpResponse res = null;
        String result = "";
        res = HttpClients.createDefault().execute(httpGet);
        result = EntityUtils.toString(res.getEntity());
        System.out.println(result);
    }
}
