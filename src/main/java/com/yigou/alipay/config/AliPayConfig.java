package com.yigou.alipay.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class AliPayConfig {

    // [沙箱环境]应用ID,您的APPID，收款账号既是你的APPID对应支付宝账号
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016110200787588";

    // [沙箱环境]商户私钥，你的PKCS8格式RSA2私钥
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQTHPt//bzK60OumLHHDl+q/nWQGTPe4mCphoXBCw9l9dYBUuJfX7PuX/V75p/ZS0PlKKKFjPOUq91eeUtNkhlmQV8Txfvs14DwP1TWiYGFZ5Wi/29lPjph3EBIrMyrjSsAImv2hDXW0N/VvZn0HuK9Cq1AkDVRBNXYvnBd6Cd8Y05lvh8pAe5nap4l73h5JF/pUAtleWAQ9Vb68vakQiXA38RQD7V5Hnnbz7xlmd/xKo86rcT3N4mZya57GZW3yB27pPmUxqJwuADhR/vxW3uMJLeJqxW/THyaMt9Nt0iKETYjsD3Y8amB9dWEoigM5Wr3nzcKwxWGZDqm95y033VAgMBAAECggEADUo9UypEAFmBct3Pwy7iTLxiIk2D+2gRXisWNBB6UqHtf3ndXyhjyZX4JCgRPrJ+1/SEk9s/oj0TU+qKJQPe3xyc2LogJj+K5SWdL1QaNG1BTA9Qq2lRE5EZ41Ti1EQfLchO5hdbfbo2GUBVKXKWLlIWrN0sQ3gln+LJxfvSmzlD3VI4orhhrlYwKUyBkS54Lj3bx1aTRmGaueoq6mYJcojj0KXeKIXFd6Nv64zJQ+vRJh8n38MJf6A9gsxaCIhzJlQVW17wiXuBqDYjkTxbc3wVwbv2lePArCJC0rFDm90hy0li2ac/C7J7x3wAHP6HGT1ye2ocxtX/hsojWwlvgQKBgQDu+M/NdM5PW4W86SsFQtCADyMMeQOhgfB8PhxLI3nYWcWg4zj40SxkaJuZhBpKam1lNSZPxVebSBETUPiTgwwidtOhRr5HPl9hXQPr2oE3hqE85gf+4t2dcEIfvSTdnIjWIdm4rcy1mVGVabhRldBBrTR6ELkCsNJgphvjzVBgrQKBgQCalKpqGZrVxWpFdceIB8AIwdqA2ER0pF/a6wwzq68H1qy38Y/kMwlC5sh31cnypCOmqnKMVggBx7L+wOvQlaCetal3T9V3KET+k9ob2Y2kLIqz4KKmls1ch3SVfGByqme3pRk0RGlmxGBv+/gs0chvUW33j7QxTZ8i1PhWt0quyQKBgQCL1pFTOvzXN0lFSHRiObzkkvcBf3b+dZkSOw43ceUFE1MwWIaoI08E2qni5Yi07x5g0P9AQsyHE17anNE5EMBzEnG4uDr0Wa1aviFjTJO8uE11423eW0cvr9ch137RJ55zH4/WH+3gfn1dH2Y6Z55cMEy6+ToqNuqEn+9GGIjmaQKBgAwA2U7N2DEbWEo2gBz3BRlo/C+fG4m3Ld5Q/CyS+09vgw/xiQveTqzMQOrVRg46Yq5Rc/2jvJFu0V5o2qbix4srbhJet6O+E8V0863P8ei6sLaRDfBDq4Sw3AOzmcWtp8oh3tgpK8oYG6j2dun8DEVgnFpp5UMi1OWehf4t03XJAoGAOeToI52jvv6/ObSGIkdFNfqp202iwd2DuquZ0Qr0taEXu2ZT4E22Tt5+XCJXeAWZb3iHBvcajpF5OZkafIGMWBMG3TSOJ/3hH8HhiUg2Z5/+FA27Gve4W9YcwbuAaJgqgewqYiBbQ58J4k3riimlxTy+vykL8Imu1aKtrDqWHu8=";

    // [沙箱环境]支付宝公钥
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    // [沙箱环境]服务器异步通知页面路径
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://3t7qtk.natappfree.cc/index/alipay/notice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://3t7qtk.natappfree.cc/index/alipay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志地址目录
    public static String log_path = "D:\\logs";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
