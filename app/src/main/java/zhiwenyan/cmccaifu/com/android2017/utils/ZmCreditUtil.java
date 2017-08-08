package zhiwenyan.cmccaifu.com.android2017.utils;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertificationCertifyRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertificationInitializeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertificationQueryRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationInitializeResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationQueryResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhiwenyan on 2017/7/31.
 */

public class ZmCreditUtil {

    //芝麻开放平台地址
    private static final String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id

    private static final String appId = "1004086";
    //商户 RSA 私钥

    private static final String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM4MKOT+w9Y2QM+9" +
            "BlCy9d42TUhuK70u6Q+Xugbyiqq30wAP3TOLqAORWVnEGNNtLYC16Ib6+wzoy3AT" +
            "obJH9KE5x+OSLIx7pT3V/FNA39cm4ExEjpWnETPI93wMXXMMjvnl2ZnhkFAmhdcd" +
            "kIoRKU6uL6u4DDCjLn4d2hylBFavAgMBAAECgYA8BMq8uYEuGquSV4tJYne//K1S" +
            "VdUjzFBTHG0xrOC7jcRGllhmtV2BfIs8XKSmoXzJqzNKbj1Gq2DnCrDlD5SwA64q" +
            "/7CphiaIN0sruce71M34412jrR7tccPfwljto2HeSVBANhfta5HiAMp3JXePb3Ra" +
            "2lG3hfv0//CM9jpH8QJBAPoEF3pz5g06x3mGPyGAps4HZaJF/q/yc1egkt6nJkor" +
            "yqJSoNbAzhv9r8R9l89nNqf3+467DeBO2dkp5po/cG0CQQDS+qmah0+u+L0/5+Pw" +
            "w+5jf2D4ZYPOLyh/Dcc4l4mA0YBFqmip7wdDsf/HaBMqk3DyZm6MwlHVvDnEJQBk" +
            "F0oLAkEAqu39N0ThtY3ILvdvVfJlGFrKS4VbOOOjo/cuMndIoFEMOqoPOIOc5los" +
            "v0O2dJ/2KCRMoiqUtBR0sBoLhM12iQJBAMYt8U0Xl5EbU+IIuEO8OFy8FWGfMe1m" +
            "vNqEHcQaDG613JEUHpap0iATvtkX+REASJNaBrQwAGEOGxEVVMWwXrsCQQDdPGpp" +
            "XvCrdQ9DsBWEuF6yWG370zHuTObjtzucj9SLJI5qrN2RmdKo1Wi9igJyUMCWJUaN" +
            "07bXwk6QOgo/yG9M";
    //芝麻 RSA 公钥
    private static final String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfvhlsE+FHqhfoIn8IUnXWp4oeB9d4KfAdd/79Jze" +
            "9X670VA7G8fUtCSN+OWkDkzeIbXRT9AW6t8SBT3l375T/JiGC4YMv73TcAGY4JH2nJ8JWDVzoGsUxJPngCYfR2WAoaePyDRO9xrvEO3q1K/fr3Ua0jD+hgcAj8/uGzAS1SQIDAQAB";

    //OpenId
    private String mOpenId = "";
    //BizNo
    private String BizNo = "";


    //获取授权连接

    /**
     * @param +姓名
     * @author ZHONG.zx
     * @Description:(获取H5网页授权链接)
     */
    public String zhimaAuthInfoAuthorize(String name, String carId) {
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("2");
        req.setIdentityParam("{\"name\":\"" + name + "\",\"certType\":\"IDENTITY_CARD\",\"certNo\":\"" + carId + "\"}");
        req.setBizParams("{\"auth_code\":\"M_H5\",\"channelType\":\"app\",\"state\":\"8585\"}");
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            //  zhimaAuthInfoAuthquery(name,carId);
            System.out.println("generatePageRedirectInvokeUrl" + url);
            return url;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param +姓名
     * @author ZHONG.zx
     * @Description:(链接授权查询)
     */
    public boolean zhimaAuthInfoAuthquery(String name, String carId) {
        ZhimaAuthInfoAuthqueryRequest req = new ZhimaAuthInfoAuthqueryRequest();
        req.setIdentityType("2");
        req.setIdentityParam("{\"certNo\":\"" + carId + "\",\"certType\":\"IDENTITY_CARD\",\"name\":\"" + name + "\"}");
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaAuthInfoAuthqueryResponse response = client.execute(req);
            String openId = response.getOpenId();
            System.out.println(openId);
            Boolean authorized = response.getAuthorized();
            if (authorized) {
                if (response.getOpenId() != null) {
                    mOpenId = response.getOpenId();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param
     * @author ZHONG.zx
     * @Description:(芝麻积分查询)
     */
    public boolean zhimaCreditScoreGet() {
        if (mOpenId == null) {
            return false;
        }
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setPlatform("zmop");
        req.setTransactionId(getTransactionId());

        req.setProductCode("w1010100100000000001");
        req.setOpenId(mOpenId);
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
                zhimaPublicKey);
        try {
            ZhimaCreditScoreGetResponse response = (ZhimaCreditScoreGetResponse) client.execute(req);
            System.out.println(response.getBizNo());
            System.out.println(response.getZmScore());
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            return true;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 认证初始化
     *
     * @param name
     * @param cardNumber
     */
    public void zhimaCustomerCertificationInitialize(final String name, final String cardNumber) {
        ZhimaCustomerCertificationInitializeRequest req = new ZhimaCustomerCertificationInitializeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        req.setTransactionId("ZGYD" + transactionId + "000001234");// 必要参数
        req.setProductCode("w1010100000000002978");// 必要参数
        req.setBizCode("FACE");// 必要参数
        req.setIdentityParam("{\"identity_type\":\"CERT_INFO\",\"cert_type\":\"IDENTITY_CARD\",\"cert_name\":\"" + name + "\",\"cert_no\":\"" + cardNumber + "\"}");// 必要参数
        req.setMerchantConfig("{\"need_user_authorization\":\"false\"}");//
        req.setExtBizParam("{}");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCustomerCertificationInitializeResponse response = (ZhimaCustomerCertificationInitializeResponse) client.execute(req);
            System.out.println(response.isSuccess());
            if (response.isSuccess()) {
                BizNo = response.getBizNo();
                System.out.println("BizNo=" + BizNo);
            }
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 芝麻认证url
     *
     * @return
     */
    public String zhimaCertificationUrl() {
        ZhimaCustomerCertificationCertifyRequest request = new ZhimaCustomerCertificationCertifyRequest();
        request.setPlatform("zmop");
        request.setBizNo(BizNo);// 必要参数
        //设置回调地址,必填. 如果需要直接在支付宝APP里面打开回调地址使用alipay协议
        //alipay://www.taobao.com 或者 alipays://www.taobao.com,分别对应http和https请求
        request.setReturnUrl("http://www.taobao.com");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(request);
            System.out.println("generateCertifyUrl url:" + url);
            return url;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 认证查询
     */
    public void ZhimaCustomerCertificationQuery() {
        ZhimaCustomerCertificationQueryRequest req = new ZhimaCustomerCertificationQueryRequest();
        req.setPlatform("zmop");
        req.setBizNo(BizNo);// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCustomerCertificationQueryResponse response = (ZhimaCustomerCertificationQueryResponse) client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getPassed());
            System.out.println(response.getIdentityInfo());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     */
    public String getBizNo() {
        if (!BizNo.equals(""))
            return BizNo;
        return "身份认证失败";
    }

    /**
     * @return
     */
    public String getOpenId() {
        if (!mOpenId.equals(""))
            return mOpenId;
        return "芝麻信用授权失败";

    }

    /**
     * @return
     */
    public String getTransactionId() {
        String ss = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + UUID.randomUUID().toString();
        return ss;
    }

    public static void main(String[] args) {
        ZmCreditUtil result = new ZmCreditUtil();
        //result.zhimaCustomerCertificationInitialize("孙星星", "320682198770215773");
        result.zhimaAuthInfoAuthorize("孙星星", "320682198710245773");
        result.zhimaAuthInfoAuthquery("孙星星", "320682198710245773");
        result.zhimaCreditScoreGet();
        result.zhimaCustomerCertificationInitialize("孙星星", "320682198710245773");

        result.zhimaCertificationUrl();
        result.ZhimaCustomerCertificationQuery();
//        result.testZhimaCreditScoreGet();
//        // result.testZhimaAuthEngineSmsauth("夏菁", "310108198709112014", "13472746781");
//       result.zhimaAuthInfoAuthquery("孙星星","320682198710245773");
    }
}
