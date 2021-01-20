package com.yigou.alipay.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yigou.alipay.config.AliPayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/pay")
    @ResponseBody
    //参数 out_trade_no=1.订单号 subject=2.订单名称 total_amount=3.付款金额 body=4. 商品描述
    //out_trade_no:商户订单号，商户网站订单系统中唯一订单号，必填
    //subject:订单名称，必填
    //total_amount:付款金额，必填
    //body:商品描述，可空
    public void pay(String out_trade_no,
                    String subject,
                    String total_amount,
                    String body,
                    HttpServletResponse response) throws Exception {

//        AliPayConfig aliPayConfig = new AliPayConfig();

        //1）封装RSA签名
        // 参数 1.网关 2.AppID 3.私钥 4.返回数据的格式 5.编码 6.公钥 7.签名
        DefaultAlipayClient client = new DefaultAlipayClient(
                AliPayConfig.gatewayUrl,
                AliPayConfig.app_id,
                AliPayConfig.merchant_private_key,
                "json",
                AliPayConfig.charset,
                AliPayConfig.alipay_public_key,
                AliPayConfig.sign_type
        );

        //2)创建request请求
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        //3)封装对象
        AlipayTradeWapPayModel payModel = new AlipayTradeWapPayModel();
        //参数 1.订单号 2.订单名称 3.付款金额 4. 商品描述
        // out_trade_no   subject  total_amount  body
        payModel.setOutTradeNo(out_trade_no);
        payModel.setSubject(subject);
        payModel.setTotalAmount(total_amount);
        payModel.setBody(body);
        //商品编码必须要,不然报错
        payModel.setProductCode("FAST_INSTANT_TRADE_PAY");

        //设置请求参数
        payRequest.setBizModel(payModel);

        //异步回调的地址，成功页
        payRequest.setNotifyUrl(AliPayConfig.notify_url);
        //同步回调的地址，扫码支付页
        payRequest.setReturnUrl(AliPayConfig.return_url);

        // 生成表单
        String head = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head>";
        String result = client.pageExecute(payRequest).getBody();
        String end = "<body> </body></html>";
        System.out.println(result);
        //输出
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(head + result + end);
    }

    //////////////////////////////////
    ////////////////////
    /*这里是回调方法，
     *return和notify代码是一样的只是请求不一样，我这里用的是post也就是notify
     */
    @RequestMapping(value = "/alipay/return", method = RequestMethod.GET)
    @ResponseBody
    public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.err.println("===return==========支付成功==========");
        //获取支付宝get过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//          valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {//验证成功
            System.err.println(JSONObject.toJSONString(requestParams));
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
//            if (trade_status.equals("TRADE_FINISHED")) {
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序

            //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
            //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//            } else if (trade_status.equals("TRADE_SUCCESS")) {
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序

            //注意：
            //付款完成后，支付宝系统发送该交易状态通知

            // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//            }
            System.err.println("====return========支付成功====");
        } else {//验证失败

        }
        return "success";
    }

    @RequestMapping(value = "/alipay/notice", method = RequestMethod.POST)
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        //获取支付宝POST过来反馈信息
        System.err.println("=========notice=============");

        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
          valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.err.println(JSONObject.toJSONString(requestParams));
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
//        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                System.out.println("业务流程");
                System.out.println(out_trade_no);
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//            }
            System.out.println("支付成功");
            System.err.println("=========notice==========");
//        } else {//验证失败
            System.err.println("=================================");
        }
        return "success";
    }
}
