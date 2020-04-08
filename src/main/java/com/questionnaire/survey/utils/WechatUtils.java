package com.questionnaire.survey.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

import static com.questionnaire.survey.config.Wechat.USER_INFO_URL;


/**
 * 微信小程序工具类
 *
 * @version 2020-02-07 10:12
 */
@Slf4j
public class WechatUtils {

    /**
     * 获取access_token URL
     */
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";


//    static {
//        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
//        Security.addProvider(new BouncyCastleProvider());
//    }

    /**
     * AES解密
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return
     */
    public static String decrypt(String data, String key, String iv, String encodingFormat) {
        //        initialize();

        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data.getBytes());
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key.getBytes());
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv.getBytes());

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, encodingFormat);
            }
            return null;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidParameterSpecException | InvalidKeyException |
                IllegalBlockSizeException | InvalidAlgorithmParameterException |
                BadPaddingException | UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 从腾讯获取获取accessToken
     * String accessToken = demoJson.getString("access_token");
     * String expiresIn = demoJson.getString("expires_in");
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static JSONObject getAccessToken(String appId, String appSecret) {
        String param = "grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String result = UrlUtils.sendGet(ACCESS_TOKEN, param);
        JSONObject demoJson = JSONObject.parseObject(result);
        log.info("##从腾讯获取获取accessToken: " + demoJson);
        return demoJson;
    }

    /**
     * 调用微信接口获取用户信息
     *
     * @param appId
     * @param appSecret
     * @param jsCode
     * @return
     */
    public static JSONObject getUserInfo(String appId, String appSecret, String jsCode) {
        String param = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String result = UrlUtils.sendGet(USER_INFO_URL, param);
        JSONObject demoJson = JSONObject.parseObject(result);
        log.info("##从腾讯获取获取用户信息: " + demoJson);
        return demoJson;
    }

}
