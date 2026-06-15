package com.lianlian.sdk.test;

import com.lianlian.global.payment.sdk.utils.SignUtils;
import org.junit.Test;

/**
 * @author thaipay
 * @since 1.0
 */
public class SignatureTest {

    @Test
    public void sign() throws Exception {

        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNBP8fjNk+fZBjfICA2IBBqK/lontRt6/h47TvMHMA08zz/yu5eD+MLMqzqn7//BELXQhYgsWnpF9J6Z5bUmPTZZvC8es3s8OOKKk5AgXduziRtQbGKfzPS1BmQs57dPTAs/swKc7QHN5KpCKRK1u3IIeYVX5AMEbv44FHrd3PGwqe6vleNmDsLBITHaiOPxY7HhHucht6TLiLufZ+sT5B5AjQJqRsBuWeKNNtLrg+bziG2Xz+jWRASA3ZAnjCe9gKMKk32uN+MA8QhnmN2hjEm2/nPuVeC/YmtjM8rbdJRW4QmLfEXj0PF27kOapSqn9pung+t77u9ysBuG4rT4cRAgMBAAECggEAE9PB4Ik8kUCPcC+gNdW6QZ9Qs4KNLo/3IH9nBiLHXHMfRRGOZvdsHeBPEiUQ9yyncVPj9Ltg5h+NX5aKTAahxNX0xFulBdQKu3rPMsYxTwpGJEE/kHaLDnkYI2uekG31JZXCWxwP9ix0iv9ooGVzDzjEJEVQWmDQ225DGG2GH/svKxeFmKz39OIKgw3zy/1SbUWAqvxaRkam3gtxkr4wHIUHA02YFiQjfGw+HkY7U18neFLEivfEZlE6gIW8VYSbuUk965lwa5tBNVc/IBhcbmKMzCF00Mk+icMhQZvMIHQeMqQFXUTTRHOFX1XKv7XqczBIKXdBzSEdPQ3vpvjUaQKBgQDtMIEELM3GQTJrsnU2RSi/qbTJCUaSzj5IkWXqgmw3d9zDzdmitErKYXqxRA7+Gd9UhjS8LzVkIykieIUmmjlBzJqpU8rWHVHwG1STVffUaFTwUxwKBj1rGpOHbZTcNP7L14/IGoEIaAfhctNbMDaVBBsOBnTBkwf8C2++xAeuQwKBgQDdR143bT7lExqiNqHeX/v8eLHUd7/MvxVrWFi/ZWCeocCvOYgVW8uNCXmaWCARAAdqNBer+ozG6pDe3Zy19DK0DblAua1/KKf2tDozeRicyYjal9eHexs7raIa2u34jgFgb2SLHi7vnNmteqSCIOQduuZJmAMzd8RvQhdhFqPiGwKBgQCOvl8WtSQNyVTfzaGOzKZOWoKrbkKjvS1snNxZ+q3H4AHjcPImYhyUmX+uSQhA0i8+fhokmsCmCCcMUL3JUo1c1RbLCtuKalDSkJAgr18b3MIpc8rvGXxDBdZ9bWxl7hrdcFhek9zNw1Xizyj1uxlOKw9hLG99w8/NVtCkXPNGYwKBgEXartqOiUlls4W7+3OpF3KHY5QwTYNEW22s0S9ywIKzi5GFdvlPJj4a3KGl6lGTkcOsOEDZcrGlASDqEvu54HvIzU+qjiPTYNrX4Hu5YdS1JpfjUW36pgk78i3KKU/K/JJF+BaW67hG6s0CJ4bQ/VKJWwOmknQf/golch37OLc7AoGBANtjM0VVWzbEXg0T5yPtnesdwdJUONsq1FtgbfoKX3yeBKVXZ2ViwDiUQqXv8FsA6FIgoqKY4vIoiuF1scQYqvN7PvIxp/Hjysc43SokJmsyi6L4pTFlkdvvOd/gylyCxHOD2Mls4P1ErUN02596Nh35MO2dSYLr0mVKNrLHsVyR";
        String requestBody = "{\"code\":200000,\"data\":{\"merchant_id\":\"142026030900988001\",\"merchant_order_id\":\"DEWEC26031839444150663249920\",\"order_id\":\"122026031801996437\",\"order_status\":\"WP\",\"order_amount\":\"5.00\",\"order_currency\":\"THB\",\"create_time\":\"2026-03-18 16:38:42\",\"pay_params\":{\"timeStamp\":\"1773826734\",\"package\":\"prepay_id=wx1817385404214348357efaf05abc8e0001\",\"paySign\":\"5aabb6029a625f56adbf253f2b0d710e82896cbf0ebc4c6551a266857c393a87\",\"appId\":\"wxe1b4f0321e71a021\",\"signType\":\"HMAC-SHA256\",\"nonceStr\":\"20260318093854048\"}},\"message\":\"Success\",\"trace_id\":\"8893c7492f991429\"}";

        System.out.println(SignUtils.sign(requestBody, privateKey));
    }

    @Test
    public void verify() throws Exception {

        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzQT/H4zZPn2QY3yAgNiAQaiv5aJ7Ubev4eO07zBzANPM8/8ruXg/jCzKs6p+//wRC10IWILFp6RfSemeW1Jj02WbwvHrN7PDjiipOQIF3bs4kbUGxin8z0tQZkLOe3T0wLP7MCnO0BzeSqQikStbtyCHmFV+QDBG7+OBR63dzxsKnur5XjZg7CwSEx2ojj8WOx4R7nIbeky4i7n2frE+QeQI0CakbAblnijTbS64Pm84htl8/o1kQEgN2QJ4wnvYCjCpN9rjfjAPEIZ5jdoYxJtv5z7lXgv2JrYzPK23SUVuEJi3xF49Dxdu5DmqUqp/abp4Pre+7vcrAbhuK0+HEQIDAQAB";
        String respBody = "{\"code\":200000,\"data\":{\"merchant_id\":\"142026030900988001\",\"merchant_order_id\":\"DEWEC26031839444150663249920\",\"order_id\":\"122026031801996437\",\"order_status\":\"WP\",\"order_amount\":\"5.00\",\"order_currency\":\"THB\",\"create_time\":\"2026-03-18 16:38:42\",\"pay_params\":{\"timeStamp\":\"1773826734\",\"package\":\"prepay_id=wx1817385404214348357efaf05abc8e0001\",\"paySign\":\"5aabb6029a625f56adbf253f2b0d710e82896cbf0ebc4c6551a266857c393a87\",\"appId\":\"wxe1b4f0321e71a021\",\"signType\":\"HMAC-SHA256\",\"nonceStr\":\"20260318093854048\"}},\"message\":\"Success\",\"trace_id\":\"8893c7492f991429\"}";
        String signature = "oQhFjeXp+gVJR1mhxOnQr21tn0ZQLCBCU5/01qw4OjU/stVeF5JbXN8KO0RHa3mMC4yVQpODDPDP2MpgxcJDCxOFb5ACn2y7lop2NXBbb4tNWI4sd+XGuR2N5I54SBssyqXk3qd5Y76lioHfVHPPlbRdYUz9MUae/i1e1EdkvKIFaPrBpRHv4dzlObnfUWiMWp8N0hkR6tr3wgyy/xUahzmYe45uukMBaBcmxao7l0R5qfyRKKCO1t0K20pebFmvmUnClBCzeLcPydl9hcyEeGY5kgPCGEwxvoiDAaL+p6kiVwtAqwPV5/zxBw/lF7q8vuekzDRytv6rxUYHERw/WQ==";

        System.out.println(SignUtils.verify(respBody, signature, publicKey));
    }


}
