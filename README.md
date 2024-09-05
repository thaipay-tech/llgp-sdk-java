# llgp-sdk-java
LianLian Global (Thailand) payment gateway java sdk. 

## Preparation
### Maven dependency
```xml
<dependency>
    <groupId>com.lianlian.global.payment.sdk</groupId>
    <artifactId>llgp-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
```


### Configuration

1. Apply for a merchant account from LianLian. You can contact LianLian support for more information.
2. LianLian will provide you with the following information:
   - Merchant ID
   - LianLian public key
   - LianLian SDK
   - LianLian API document
   - LianLian portal endpoint

3. Generate RSA pair key and provide the public key to LianLian. You can use the following command to generate RSA 
   key pair:
```java
import com.lianlian.global.payment.sdk.utils.SignUtils;
import java.util.Map;

import static com.lianlian.global.payment.sdk.utils.SignUtils.PRIVATE_KEY;
import static com.lianlian.global.payment.sdk.utils.SignUtils.PUBLIC_KEY;
    public class GenerateKeyPair {
        public static void main(String[] args) throws Exception {
            Map<String, String> keyPair = SignUtils.genKeyPair();
            System.out.println("public key= " + keyPair.get(PUBLIC_KEY));
            System.out.println("private key = " + keyPair.get(PRIVATE_KEY));
        }
    }
```
## Start payment
### Checkout
If you want to use LianLian checkout page to complete your payment, you can use the following code:
```java
public void checkout() {

        LLPayClient llPayClient = new DefaultLLPayClient(Profiles.UAT,
                merchantPrivateKey, lianPayPublicKey);

        CheckoutReq request = CheckoutReq.builder().version("v1")
                .service(Service.CHECKOUT)
                .merchant_id(merchantId)
                .merchant_order_id("llgp-sdk-checkout-test-20240824002")
                .order_amount("1.89")
                .order_currency(Currency.THB.name())
                .order_desc("checkout test")
                .notify_url("https://www.yourdomain.cc/callback/notify.php")
                .redirect_url("https://www.yourdomain.cc/callback/redirect.php")
                .customer(Customer.builder().merchant_user_id("user_id_001").full_name("Bill")
                        .address(Address.builder().country("CN").state("Zhejiang").city("hangzhou")
                                .district("binjiang").postal_code("310051").street_name("yueda")
                                .house_number("79").build()).build())
                .products(Arrays.asList(
                        Product.builder().name("cap").description("big size").unit_price("12.3")
                                .quantity("12").category("red").show_url("http://111.taobao.com/").build(),
                        Product.builder().name("hat").description("small size").unit_price("11.3")
                                .quantity("10").category("gray").show_url("http://222.taobao.com/").build()))
                .build();

        LLPayResult<CheckoutResp> response = llPayClient.execute(request);
        System.out.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));
    }
```
After you send the request, your will get response like this:
```json
{
  "code":200000,
  "data":{
    "create_time":"2024-09-05 09:08:28",
    "link_url":"https://sandbox-checkout.lianlianpay-inc.com?data=H4sIAAAAAAAAAJ2S3WrbMBiGb2Xo2Bn2YJDmTLG9RcSx3cRjhLoIRVJjJ7LkSXKXUHJfO9ut7FKmOODRhjDo6fs8fH/SC5BKUg4m4Meu2wl+YNWhIvrnngrggbYxjjy8lKAlx4ZLi1utWEdtCSYlCOEyWpXA+0cbbivFHHy40BI8OkpE7QTcis7gJ0G2jj8RYfjJu1H4fonzZbbIi5vFixlE+H75zvqLbIqSGE9hOkfp15tNYrhaY5jnGCZJL80XEEdxnCconQ9BmhXoCwphgbL0EubJt9Ubr49em++a/HsczmCBc7i+OXW0TuEChTjMovg/XR7dExurNMdKM65xzdxrC7FtR4btRzvyTEaWGzvy7/zPvv/J2RePdlpzSY/OLmbTqxibY7NRwtE/v34PlDSqk9aFwcfx3ZAybqjLaMXpXnX2w7mfg7biDcdUCaXBRHZCeIASzfBGE8nOf/K5NsRriLFcn4G3oxuvk7WSbtNhK0ma888+GH8cjIMgGIBQW/WqLtFbJ7qjb2ohMGFMc2P6i0doBadJ3J+7745ppZThV5A3pBZXaVsp+cY9gdNfrggoW3YDAAA=",
    "merchant_id":"142023081800601001",
    "merchant_order_id":"llgp-sdk-java-test-0905002",
    "order_amount":"1.89",
    "order_currency":"THB",
    "order_id":"122024090501440005",
    "order_status":"PI"
  },
  "message":"Success",
  "trace_id":"f71e2b5c8b1b4a38"
}
```    
your will get a checkout page url from response, you can redirect to this url <font color="#FF0000"><b>from your 
business domain</b></font> to complete the payment. 
registered 
from LianLian to complete the payment.

### Direct payment

#### Prompt Pay
If you want to use Prompt Pay to complete your payment, you can use the following code:
```java
public void promptPay() {

        LLPayClient llPayClient = new DefaultLLPayClient(Profiles.UAT,
        merchantPrivateKey, lianPayPublicKey);

        CheckoutReq request = CheckoutReq.builder().version("v1")
        .service(Service.QR_PROMPT)
        .merchant_id(merchantId)
        .merchant_order_id("llgp-sdk-promptpay-test-20240824002")
        .order_amount("1.89")
        .order_currency(Currency.THB.name())
        .order_desc("prompt pay test")
        .notify_url("https://www.yourdomain.cc/callback/notify.php")
        .redirect_url("https://www.yourdomain.cc/callback/redirect.php")
        .customer(Customer.builder().merchant_user_id("user_id_001").full_name("Bill")
            .address(Address.builder().country("CN").state("Zhejiang").city("hangzhou")
            .district("binjiang").postal_code("310051").street_name("yueda")
            .house_number("79").build()).build())
        .products(Arrays.asList(Product.builder().name("cap").description("big size").unit_price("12.3")
                .quantity("12").category("red").show_url("http://111.taobao.com/").build(),
                Product.builder().name("hat").description("small size").unit_price("11.3")
                .quantity("10").category("gray").show_url("http://222.taobao.com/").build()))
        .build();

        LLPayResult<CheckoutResp> response = llPayClient.execute(request);
        System.out.println(JSON.toJSONString(response, SerializerFeature.PrettyFormat));
        }
```
After you send the request, your will get response like this:
```json
{
  "code":200000,
  "data":{
    "create_time":"2024-09-05 09:19:59",
    "merchant_id":"142023081800601001",
    "merchant_order_id":"llgp-sdk-qr-test-082800111",
    "order_amount":"1.89",
    "order_currency":"THB",
    "order_id":"122024090501440006",
    "order_status":"WP",
    "qr_code":"iVBORw0KGgoAAAANSUhEUgAAAUoAAAFKCAYAAAB7KRYFAAAWx0lEQVR42u3dC5hVVb0A8AF8opmW5vX66OH96lr5WYqmaWWZpeX98prdzFIQHyiYoje9YYLk4yq+MF+AipaKioBYXXuYoqaWLwSxRPD9wlQCkde8/3evPecMMzADDIPMmXN+6/v+3wzDOWufvfZav7PW3nvtVVVVVRVixeiq1FX7US7lt7b3t1zy66p6UEahEEAJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJSgFKEEJStEJKMslVdr+lnoDcXzLG/wyqvcKBpSgVJ+VHyhBCUr1WfmBUkMCpYYOSlBqSKB0fEEJSlCC0vEFJShBCUpQghKUoAQlKEEJShULlKAEJSgVDChBCUrltw6gLPUGV2lT/yptSiSguye83aCeKhhQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKBQNKUIKSB6AEAnhN6QMlKEEJSlCCEpSgBCUoQQlKUIJSOYMSlKAEpXIGJShBCUrlDEpQghKUoAQlKBWMBgxKUPIAlBowKEEJSlCCEpSgBCUoyxJKi3wBQfsApYoASlBqH6BUEUAJSlCCEpSgBCUoQalgQAlKUPIAlKAEJShBCUoNBJTqAShBCUpQglL7AKWKAEpQah+gVBFACUrto1KhLPVULhWrXCp+qcNb6l9IlfYF14XHV8GADZSgBCUowQZKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlK+YESlKAEpfxACUpQKhhQghKUPOgElJUWoJSf/AQoVXz5yQ+UoASl/OQHSlCCUn7yAyUoQSk/+YESlKCUn/xACUpQyk9+oAQlKOUnP1CCEpQakvzkJxSAii8/+YFy5RFSp1KlNaRSL7+u+nyVtnhcxbVzRQBKUIJSAiUoQQlKCZSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSglEAJSlCCUgIlKEEptVMPTIHqjotUlcvUzkqbolouX/wV2H5BCUpQglL7BSUoQQlK9RSUoAQlKNVTUIISlKAEJShBCUpQghKUChqUoAQlKBW0cgElKEGpoFVAUIISlKAEJShBqf12AkpT67rn/oKjtMrFIn3dtvxACUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSgFKUIISlKAEJShBCUpQgrKNKPUK2FUFDazyrgflsl1f/Otou6AEJShBCUpQghKUoAQlKEEJSlCCEpSgBKXtqn+gBCUobReUoAQlKEEJSlCCEpSgBCUoQamiAsvxACUoQQksUCrnkoKy1A9cqcNb6g3OFxegu0P9AyUoQQlK9Q+UoAQlKNU/UIISlKAEJShBqaKCEpSgBKWKCkpQghKUKiooQQlKUIISlKAEJShBCUpQghKUoAQlKNW/zh7fyqqoldZAyuXzOb6mvHbxYmWgBCUoQQlKUIISlKAEJShBCUpQghKUoASl4wtKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIKyE1BWWoXW4Lpng6u0LzhTGNcZqKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUK4DKEu9ALvBVKmK2g8NWOoO9Q+UoAQlKEEJSlCujf3o0aNH9OrVq/nfO+64YwwcODBuGz8+npoxI+bMmRNz587tcDQ0NLQTjdHY2AhKUIISlN0Dyp49ezb/3qdPnxg3blzMmzc/6uob4v2mLIFZn7ZT4miCEpSgrGAoi0huvPHGMXLkyLy3V59FE2LZ7/X1Me/dhTH7pTnx7AuvdzhmvfjGCvFsFi+//nZUV9e2+ozF7YISlKAEZcnsRxHJ7bbbLh555JFoaGwaDiccX3rtrThr1KT43GFnxmb7DIj1dz9qrcYGe/SPrb42KL4+YESMveP+WLykurmHWYqdS1CCEpQVCGURyW222SZmz56d9x5ra2tjydKa+Mklt0SPXfvG5l85IY44c3RcO+m+mPLYM/HwU8+tlXho+nNx14PT48Ib/i++fvyIfFtb73di3PTbh5o/b6kNxUEJSlBWGJTFCzcbbLBBU08y68UlJF/KhsM7H/LTWH+3fnHxr34XCxYuXifH9vlX/hF9h46Jqk/9Vxw97Jqoq6vPoSwlLEEJSlBWGJTFq9sjRozIh9sJyVfeeCe2zXp1nzn49Hj+1X80vy9d0KmprYvqmixq24/0uvqGZVGbYbfS92T/l/JNSBfTnfc+Eb12+VEceuovojFd5GkonYs8oAQlKCuwR7nTTjtlPbe6DMm6WFpdE5///s/iU//xk6wXuSR/fYJuXVxcKV48Sr3IlB58clZU7XRYDLn89pK6wAPKMoey1CuCG7C7pqKOHTu2AFRdnD1mcvTc5Yh4rtCTTL3DhhY9uQcenxkX//KuGHr1pBg66o4sJrWIO2LIlRPi1Tlzm2Crb4Lt7r/MiNNG3hZnjZ68wuuHZT9Hjb8nZr7weqtzkrUFLEdPmJJj+fjfXlgBy/XWW68spoqWC9DlMrUYlKBcYX+33nrr/GbwlN7554LYeM+j46wMsCYk65uHu+mWoP2OPT967X5UVH3uiLbj80dG1acPi4eznmBKaUid0mmX3BJVH//PqNqtX7vv23Tv4+KkC27MerS1+ZXuhsbGZmh3/cHQ2P/4EYVe5zK0W97zCUpQghKU71s5H3744U29xqyndvVtf4qN9ugf78xbkANZV7j5O13Y+ei3TomqXfvGFl8dGB/66qAsij+XxYe/NigH79EZz+fbSecdU/p51mtcr0+/+MjXf7zCe4r5fPArJ0TVZw+P7512RQ5kQ779+vwm9/F/eCTH9MXX3mqFZfHUAShBCUpQvq/lPGrUqBymdL/kN064MPbPoohREaSEV+r5bbXfibHZl49vNxJ2G+55TDz6VGsoz7pqYv7+hGK7781iyyz/qp1/GNdPvr/53GhK8xcsik2+eGw2DL+3+XRAcT9aTrUEJShBCcr3pZynTJnSdO6vri62P2Bwfs4wDX2LyD33ypux2ZcGxGYZgitDsrNQpthi34Gx/hf6x5eOOje/0t3ytqB0s/vRw69rde4zff7VOU8JSlCCEpSdKudp06blf3tv4ZK8V1fstVXXNE0p/PV9U6PHbv3yIXdbuG2+7wmF4fPAfOi9SRtD7+FXZ0PvLI+tsqH3FoXXfrANeNPfemfv3/7AU/KpksXzpCkd+ONL4qAsWl7QASUoQQnKdQPl9On5396e+270zoa3106c0grKCX98tPnc5ApIZrCt/4Wjmy7i7Hpk/rqqz/wgHn5ydquLOadfemtU7fjd6JkuBKXXZpFAXB7L9O9N9jkutv7GSfFm9nlyKAvD72+fPDK+feLFoAQlKEHZhVD+c0GbUE68+7E2oUw9yTRM/nL/c/Pbe278zYPxqyxumPxAnldL0Kb+/cW4buJ92Wseyl93/nW/yXuNG2fba4llc4/ygMExd/57rXqU3z75UlCCEpSgLE0oJ7QBZepJbrjXMfHZQ8+IRYuXrtFnuP+xZ6J3lkdbUH7sWysOvfUoQQlKUHYrKNM5xjR8Pvbc65uH2OkCS10hlp9qmK6eF/8vXclOPxdnwH7ioP9u1assQvnRA0EJygqEEoClVVE7OvTu2adffqEm9SRTpN977tY3jj3n+sKj2FZ/amER0fkZhDtmUKZzkulqd8o3/dxknwFZj/LUdqFMs4cSlEOHDl3rUFpMrbTab5ftLyhB2VEo85u9d/5hDloabqdImKWbw/sOv7aA2ZpAuSi2/ebJ0XOPo/JeZco3/ey1R//YZv8fx7z5oAQlKEFZ8lA2XbG+64FpscMBg2PnQ8+ITx/y0zx2/t4ZsUOG3OmX3dY0FF4DKBe8tzj2PvLn8cnvnBaf+W5TvunnJ79zeuz1o+Hx7oJFoAQlKEFZ2lAWe4kJwaVLa6K6unWkv9UW7pNck5S8TMs/VLeRd3VhvnfL85GgBCUoQVmyUHZ1AiUoQQnKbgFlYzvRaQQbl01TbB2gBCUoQalH2ZwaVvHUclCCEpSgLN2LOYXzj3f/5en8Qs7e/c6JPY88O4+9jzon+9uQ5udWduTJ40UW5y5dHJNfnNncs1w1lJe2gnLYsGGgBCUoQdnFUBZuD7rt94/k87c32uuYfMpiio2y16UH9B457JoO9z6LJC6sqY5P3D46rnpmalMejQ0rhfKgwa17lEOGDAElKEEJytKAcuKfWtxwvu8JeWz5tRObbjg/dw1uOG8B5efuvCE2vPGSuGbmtHaxLEJ5QIZkSygHDRqUP+EclKAseSjLBaJKqwhtnSOcO39hh6cwHnfuDYXX1hUe/tvOFMb0tPIMwTTErksLiGW/L62rjV0zKD9y21XxgZsvi1Ht9CyLDw/+xsCL4uCTR0ZNbW0O5ciRI8sGjlLvSJRLe+uyKYygrEwoNy88oPfzhw2NmsLrOpqmvvNmbH3rlbHt7aNiuyw2uWlkXPX3FbEs9ii/fMz/xr5Hn5f/vvzStqAEJShBuc6g3KSjj1nbo39884QL4+a7Ho5J9z4Rd0x5Iibe83j8szBPu5j3zPlzY9LLs+K3rz4fv371ubj22en5sHvLW6/Iodx2/NXNWF759ydaYVmE8urx90TVv38/zrvmzrzHmrBc3WW+QQlKUIJy7UA5773ovVfHH9y7XoZl/jDe3fo2rbL42cPj4WmFB/fWFRYXm/pgVI29IDYf94vYNBtmJxC3Sr3J8aPiXzMkU7TE8vK/Pd6MZWOL4felN/0+fwDw2aPvaD43ujpYghKUoARlp7ZbPJ+4cPHSfCmIMcstBXHnlLQURN+VLgWR/i9f4mH5pSAK87QvfOqv+XnIj08cE9tnGG5/++hWSLaF5WVPP1a4dagJy+IFo8tv+WOOZVpeIgp/XxWWoAQlKEG5VqBMy9Vud8DgGD56cqvFxWa9NCc23ee4Nte4WeXiYgUoL5j+l+id4bdDBuTyOLaHZXr9yKcfXYZlYfnclK689U85lsOumtgCy0ZQghKUoHz/tlsc2u434II4YNCyh+MW//6dwSPz4fWWHV2udg2gbI3lpXHJjCKWja2wvCqds8ywHHrFhFViCUpQghKUnd5uEZ/Lbv5D9M6gm/fuomaUkj2pV7nN/idFjz794kNfHdS86uLy8eGVDL03Kwy9E5arijQ032HC6PjYhDH5+4o9y7wHHMsevTbq9ntzLH92+fiVYglKUIISlJ3vURZweWvuu7HhHv3jvOt+UwC0vvn/Zsx6NfY68uf5+cq0RndxNcVWsfwqjIWLOcOn/jmqxp4fm437Rd6zXN1I5yrTxZ/03iGP3x/V9XX552mJ5ZiJU3IshxSfjdmwIpY9evQAJShBCcrOb7d4G84Z2VB2/Qy8V+bMbe6lNbQ4j/m7P0/P53iffOHNMfiiLC5cFqdcNC5OPP/GeOn1t5ugLeR592svxCmP3BNnZmCe8cQDHYqfZZHed9Jf745n5r3dCvYiltdOui+q/u278T+X3tomlmn2zupgCUpQdgjKcqkwlbYYU2dSgiWdk1y8pDo+ffDpscuhQ/Lfc5Dq6jv04It1mYpYjp18f4bloXHaJbfk+1JdU5P/HD58eLc8HqV0rrXSEihBuVoXdWa//GZs+aUBsfthZ8Ybb81rhVK6Gp6eLFSzksh7oQ3LIp3rTOcrOxv1Da3zTZH+VnzS0XV3ZFh+4pA4ZcRNrbAEJShBCcq1jGVTz3Hmi2/Ejt86JT6w17ExZsKUfHmG7pAmpId47PzDZizTFMvVxRKUoAQlKDt8vvLd9xbHgLPHRtUuR8S/7H9SHH/eL/NVGR99+vmY9sxL8WQW09ZmzOxEZO9//OkXYlYG/E+y4XfVTofFqRfd3IxkV8zgASUoQVnGULYchqf0zPOvx6kXj4tPHnx6bLTn0dGrT79Yb/ej1m5kefbYtW+nI12R3/SLx0bvvY/L54YfM/y6WLK0Jt+fRlCCEpSgfH8u8LS+iPPm2/Pj6edei+mzX12rMXXmy/HQk7PWSvz5iWfjwamz8jnnf3hoRry3cMlq9SpBCUpQgrJTvcv6+obybyCgBCUoQdn5HmYBzcLV5mIsXLRotWL5960Q9Q2rfs0abLcRlKAEJSgrtSdWaXCAEpSgBCU47G95QqnBlXd+5fKF1FXlUmlAl80US1CCEpRAUC6gBCUogaBcQAlKUAJBuYASlKAEAihBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBqVza2a5FjMp5USn7a0qfL1ZQghKUoAQlKFUYUIJSvQclKEEJSlCCEpSgBCUoQQlKcIASlKAEJTjsLyhBCUpw2F9QghKUoLS/oAQlKEEJSlCCsrtAWeoHpNQrqobePeGoNGAqreMESlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoARl56As9QKstCl9lQZRucBWaV8MZVT/QAlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgXAdQVlpFrbTFsSptal2lQWSxN1CCEpSgVJ9BCUpQglJ9BiUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQaligRKUoASligVKUKrPXQmlRZEsptaVDU75lXd0YfmBEpQauvIDJShBqaErP1CCEpQaOihBCUpQauigFKAEJShBCUpQghKUoAQlKEEJSlAqP1CCEpSgVH6gBCUoNXTlB8o2IiQVuowWNSuX49ZVX9Tl0jEBpQYHSlCCEpQaHCgdN1CCEpSgdNxACUpQgtJxAyUoJVA6bqAEpQRKUIISlKAEJShBCUpQanCgBCUoQanBgRKUoFxXYVpUd1yErNQbEhDAW071AJSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUApSgBCUoOwdluaRyWQysXAAsl89nkbnuWS5dtgojKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBqV5pb6AEJShBCUpQghKUoAQlKEEJSlCCEpQOHChBCUpQghKUoAQlKEtqRyrtwJXLF5LFu8q7nMuonYMSlKAEJShBCUoNGJSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKDU4UIISlKAEpXIGJShBCUpQghKUoAQlKB03UFbcFMZKA7rUAQR591zMD5SgBCUoQQlKUIISlMoZlKAEJShBCUpQghKUoAQlKEGpAYMSlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQnK7lBRLY5lEa2u/Hylfjy6cLugBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCuAyhLPanQ5b0oV6Vtt1zqKShBCUpQghKUoAQlsLQjUIISlKAEJSgdYFCCEpSgBCUoQQlKUIISlKAEJShBCUpQ2i4oQQlKUIISlKAEJShBCcryhrLSotQrTKnDC8Du+cVvajEoQQlKUIISlKAEJShBCUpQghKUoAQlKEEJSlCCEpSgBCUoQQlKUIISlKAEJShBCUpQClCCEpSgBCUoQQlKUHYgv/8HTsSn75V7dssAAAAASUVORK5CYII=",
    "qr_code_expire_sec":"480"
  },
  "message":"Success",
  "trace_id":"cff3c9767d5e3fe3"
}
```
your will get a QR code image from response, you can display this image at your business 
page, just like:
```html
<img src="data:image/png;base64,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">
```
