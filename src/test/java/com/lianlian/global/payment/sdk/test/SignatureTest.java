package com.lianlian.global.payment.sdk.test;

import com.lianlian.global.payment.sdk.utils.SignUtils;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class SignatureTest {

    @Test
    public void sign() throws Exception {

        String privateKey = "";
        String requestBody = "";

        System.out.println(SignUtils.sign(requestBody, privateKey));
    }

    @Test
    public void verify() throws Exception {

        String publicKey = "";
        String respBody = "";
        String signature = "";

        System.out.println(SignUtils.verify(respBody, signature, publicKey));
    }


}
