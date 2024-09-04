package com.lianlian.global.payment.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.support.LLPayException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @author thaipay
 * @since 1.0
 */
public final class SignUtils {

    private static final String ALGORITHM = "SHA1withRSA";
    private static final String RSA = "RSA";
    private static final String CHARSET_UTF8 = "UTF-8";

    public static final String PUBLIC_KEY = "PUBLIC_KEY";
    public static final String PRIVATE_KEY = "PRIVATE_KEY";

    public static String sign(String text, String privateKey) throws Exception {

        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(privateK);
        signature.update(getContentBytes(text, CHARSET_UTF8));
        byte[] result = signature.sign();
        return Base64.encodeBase64String(result);
    }

    public static boolean verify(String text, String sign, String publicKey)
            throws Exception {

        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicK = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initVerify(publicK);
        signature.update(getContentBytes(text, CHARSET_UTF8));
        return signature.verify(Base64.decodeBase64(sign));
    }


    public static Map<String, String> genKeyPair() throws Exception {

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, String> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
        return keyMap;

    }

    private static byte[] getContentBytes(String content, String charset) {

        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("unsupported encoding:" + charset);
        }
    }

    public static String jsonArgs2String(String jsonArgs) throws LLPayException {

        if (StringUtils.isEmpty(jsonArgs)) {
            throw new NullPointerException("please check your parameter!The JSON string is empty.");
        } else {
            Map<String, Object> payment = JSON.parseObject(jsonArgs);
            StringBuffer content = new StringBuffer();
            append(content, payment);
            return content.toString();
        }
    }

    private static void append(StringBuffer content, Map<String, Object> sourceObj) {

        if (sourceObj != null) {
            Map<String, Object> obj = sourceObj;
            if (sourceObj.keySet().size() != 0) {
                List<String> keyList = new ArrayList(sourceObj.keySet().size());
                Iterator var4 = sourceObj.keySet().iterator();

                String key;
                while (var4.hasNext()) {
                    key = (String) var4.next();
                    keyList.add(key);
                }

                Collections.sort(keyList);
                var4 = keyList.iterator();

                while (true) {
                    while (var4.hasNext()) {
                        key = (String) var4.next();
                        Object value = obj.get(key);
                        if (value instanceof List) {
                            for (int i = 0; i < ((List) value).size(); ++i) {
                                Object item = ((List) value).get(i);
                                if (item instanceof Map) {
                                    append(content, (Map) item);
                                }
                            }
                        } else if (value instanceof Map) {
                            append(content, (Map) value);
                        } else if (value instanceof String || value instanceof Float || value instanceof Double
                                || value instanceof Integer || value instanceof Long || value instanceof BigDecimal) {
                            if (content.length() > 0) {
                                content.append("&");
                            }

                            content.append(key);
                            content.append("=");
                            content.append(value);
                        }
                    }

                    return;
                }
            }
        }
    }
}
