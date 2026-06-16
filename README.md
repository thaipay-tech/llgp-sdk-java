# LianLian Global Payment SDK for Java (Thailand)

A Java SDK for integrating with the LianLian Global Payment Gateway (Thailand), supporting online/offline acquiring, refund, payout and order query.

## Requirements

- Java 1.8+
- Maven 3.x

## Installation

### Maven

```xml
<dependency>
    <groupId>com.lianlian.global.payment.sdk</groupId>
    <artifactId>llgp-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

### 1. Get Credentials

Contact LianLian support to obtain:
- Merchant ID
- LianLian RSA Public Key
- API Documentation & Portal access

### 2. Generate Your RSA Key Pair

Generate a 2048-bit RSA key pair and share your **public key** with LianLian:

```java
import com.lianlian.global.payment.sdk.utils.SignUtils;
import java.util.Map;

public class GenerateKeyPair {
    public static void main(String[] args) throws Exception {
        Map<String, String> keyPair = SignUtils.genKeyPair();
        System.out.println("Public Key:  " + keyPair.get(SignUtils.PUBLIC_KEY));
        System.out.println("Private Key: " + keyPair.get(SignUtils.PRIVATE_KEY));
    }
}
```

### 3. Initialize the Client

```java
import com.lianlian.global.payment.sdk.DefaultLLPayClient;
import com.lianlian.global.payment.sdk.LLPayClient;
import com.lianlian.global.payment.sdk.support.Profiles;

// Sandbox
LLPayClient client = new DefaultLLPayClient(Profiles.UAT, merchantPrivateKey, lianPayPublicKey);

// Production
LLPayClient client = new DefaultLLPayClient(Profiles.PROD, merchantPrivateKey, lianPayPublicKey);
```

## Environments

| Environment | Profile       | Gateway URL                                      |
|-------------|---------------|--------------------------------------------------|
| Sandbox     | `Profiles.UAT`  | `https://sandbox-th.lianlianpay-inc.com/gateway` |
| Production  | `Profiles.PROD` | `https://api.lianlianpay.co.th/gateway`          |

## Supported Payment Methods

| Payment Method       | Service Enum           | Description                    |
|----------------------|------------------------|--------------------------------|
| Checkout (Hosted)    | `Service.CHECKOUT`     | LianLian hosted checkout page  |
| QR PromptPay         | `Service.QR_PROMPT`    | Thai QR PromptPay              |
| Mobile Banking       | `Service.MOBILE_BANKING` | Thai bank mobile app redirect |
| Bank Card            | `Service.BANKCARD`     | Credit/Debit card payment      |
| WeChat Pay           | `Service.WECHAT_PAY`   | H5 / INAPP / MERCHANT_SCAN    |
| Alipay (Online)      | `Service.ALIPAY`       | Alipay online payment          |
| Alipay (Store)       | `Service.ALIPAY_STORE` | Alipay offline store payment   |
| TrueMoney            | `Service.TRUE_MONEY`   | TrueMoney Wallet               |
| ShopeePay            | `Service.SHOPEE_PAY`   | ShopeePay Wallet               |
| LINE Pay             | `Service.LINE_PAY`     | LINE Pay Wallet                |
| Counter Pay          | `Service.COUNTER_PAY`  | Counter/convenience store pay  |
| PaoTang Pay          | `Service.PAO_TANG_PAY` | PaoTang Wallet                 |
| Direct Debit         | `Service.DIRECT_DEBIT` | Bank direct debit              |

## Usage Examples

### Checkout (Hosted Page)

Redirect your customer to LianLian's checkout page to complete payment:

```java
CheckoutReq request = CheckoutReq.builder()
        .version("v1")
        .service(Service.CHECKOUT)
        .merchant_id(merchantId)
        .merchant_order_id("ORDER-20240824-001")
        .order_amount("100.00")
        .order_currency(Currency.THB.name())
        .order_desc("Test order")
        .notify_url("https://yourdomain.com/callback/notify")
        .redirect_url("https://yourdomain.com/callback/redirect")
        .customer(Customer.builder()
                .merchant_user_id("user_001")
                .full_name("John Doe")
                .build())
        .products(Arrays.asList(
                Product.builder().name("Product A").description("desc")
                        .unit_price("50.00").quantity("2").category("general")
                        .show_url("https://yourdomain.com/product/a").build()))
        .build();

LLPayResult<CheckoutResp> response = client.execute(request);

// Redirect customer to response.getData().getLink_url()
```

**Response** contains `link_url` — redirect the customer to this URL **from your business domain** to complete payment.

### QR PromptPay

Generate a Thai QR PromptPay code for customer scanning:

```java
QRPromptReq request = QRPromptReq.builder()
        .version("v1")
        .service(Service.QR_PROMPT)
        .merchant_id(merchantId)
        .merchant_order_id("QR-20240824-001")
        .order_amount("50.00")
        .order_currency(Currency.THB.name())
        .order_desc("QR payment")
        .notify_url("https://yourdomain.com/callback/notify")
        .redirect_url("https://yourdomain.com/callback/redirect")
        .customer(Customer.builder().merchant_user_id("user_001").full_name("John Doe").build())
        .build();

LLPayResult<QRPromptResp> response = client.execute(request);

// Display the QR code image (Base64-encoded PNG)
String qrCodeBase64 = response.getData().getQr_code();
```

Display the QR code in your frontend:

```html
<img src="data:image/png;base64,${qrCodeBase64}">
```

### WeChat Pay

Supports three modes: `H5`, `INAPP_PAYMENT`, `MERCHANT_SCAN`

```java
WechatPayReq request = WechatPayReq.builder()
        .version("v1")
        .service(Service.WECHAT_PAY)
        .merchant_id(merchantId)
        .merchant_order_id("WX-20240824-001")
        .order_amount("15.00")
        .order_currency(Currency.THB.name())
        .order_desc("WeChat H5 payment")
        .payment_method("H5")  // H5 | INAPP_PAYMENT | MERCHANT_SCAN
        .notify_url("https://yourdomain.com/callback/notify")
        .customer(Customer.builder().merchant_user_id("user_001").full_name("Test User").build())
        .build();

LLPayResult<WechatPayResp> response = client.execute(request);
```

### Refund

```java
RefundReq request = RefundReq.builder()
        .version("v1")
        .service(Service.REFUND)
        .merchant_id(merchantId)
        .merchant_order_id("ORDER-20240824-001")     // Original order ID
        .merchant_refund_id("REFUND-20240824-001")   // Unique refund ID
        .refund_amount("50.00")
        .refund_currency(Currency.THB.name())
        .refund_reason("Customer request")
        .notify_url("https://yourdomain.com/callback/notify")
        .build();

LLPayResult<RefundResp> response = client.execute(request);
```

### Payout (Disbursement)

Transfer funds to a bank account:

```java
PayoutApplyReq request = PayoutApplyReq.builder()
        .version("v1")
        .service(Service.PAYOUT)
        .merchant_id(merchantId)
        .merchant_order_id("PAYOUT-20240824-001")
        .order_amount("200.00")
        .order_currency(Currency.THB.name())
        .order_info("Payout to supplier")
        .payee_bankcard_account("0830443596")
        .payee_bankcard_account_name("Recipient Name")
        .payee_bank_code("014")
        .notify_url("https://yourdomain.com/callback/notify")
        .build();

LLPayResult<PayoutApplyResp> response = client.execute(request);

// After receiving OTP, confirm:
PayoutAckReq ackReq = PayoutAckReq.builder()
        .version("v1")
        .service(Service.PAYOUT_ACK)
        .merchant_id(merchantId)
        .merchant_order_id("PAYOUT-20240824-001")
        .confirm_code("284707")
        .notify_url("https://yourdomain.com/callback/notify")
        .build();

LLPayResult<PayoutAckResp> ackResult = client.execute(ackReq);
```

### Order Query

```java
// Query payment order
PaymentQueryReq payQuery = PaymentQueryReq.builder()
        .version("v1")
        .service(Service.PAYMENT_QUERY)
        .merchant_id(merchantId)
        .merchant_order_id("ORDER-20240824-001")
        .build();
LLPayResult<PaymentQueryResp> payResult = client.execute(payQuery);

// Query refund order
RefundQueryReq refundQuery = RefundQueryReq.builder()
        .version("v1")
        .service(Service.REFUND_QUERY)
        .merchant_id(merchantId)
        .merchant_refund_id("REFUND-20240824-001")
        .build();
LLPayResult<RefundQueryResp> refundResult = client.execute(refundQuery);

// Query payout order
PayoutQueryReq payoutQuery = PayoutQueryReq.builder()
        .version("v1")
        .service(Service.PAYOUT_QUERY)
        .merchant_id(merchantId)
        .merchant_order_id("PAYOUT-20240824-001")
        .build();
LLPayResult<PayoutQueryResp> payoutResult = client.execute(payoutQuery);

// Query payout account balance
AccountQueryReq accountQuery = AccountQueryReq.builder()
        .version("v1")
        .service(Service.ACCOUNT_QUERY)
        .merchant_id(merchantId)
        .build();
LLPayResult<AccountQueryResp> accountResult = client.execute(accountQuery);
```

### Direct Debit (Account Binding + Payment)

#### Step 1: Bind account

```java
AccountBindReq bindReq = AccountBindReq.builder()
        .version("v1")
        .service(Service.ACCOUNT_BIND)
        .merchant_id(merchantId)
        .store_id(storeId)
        .request_id("BIND-" + System.currentTimeMillis())
        .merchant_order_id("BIND-ORDER-001")
        .payment_method("DIRECT_DEBIT_SCB")  // SCB / KTB / KBANK
        .account_no("1234567890")
        .notify_url("https://yourdomain.com/callback/notify")
        .redirect_url("https://yourdomain.com/callback/redirect")
        .customer(Customer.builder().merchant_user_id("user_001").full_name("Somchai Test").build())
        .build();

LLPayResult<AccountBindResp> bindResult = client.execute(bindReq);
```

#### Step 2: Pay with bound account

```java
DirectDebitPayReq payReq = DirectDebitPayReq.builder()
        .version("v1")
        .service(Service.DIRECT_DEBIT)
        .merchant_id(merchantId)
        .merchant_order_id("DD-ORDER-001")
        .order_amount("100.00")
        .order_currency(Currency.THB.name())
        .binding_id("binding_id_from_step1")
        .notify_url("https://yourdomain.com/callback/notify")
        .customer(Customer.builder().merchant_user_id("user_001").full_name("Somchai Test").build())
        .build();

LLPayResult<DirectDebitPayResp> payResult = client.execute(payReq);
```

## Signature Verification

The SDK automatically handles request signing and response verification. For manual webhook/callback verification:

```java
import com.lianlian.global.payment.sdk.utils.SignUtils;

// Verify callback signature
String callbackBody = "...";   // Raw JSON body from callback
String signature = "...";       // "sign" header value
boolean valid = SignUtils.verify(
        SignUtils.jsonArgs2String(callbackBody),
        signature,
        lianPayPublicKey
);
```

## Response Structure

All API calls return `LLPayResult<T>`:

```java
LLPayResult<T> result = client.execute(request);

result.getCode();       // 200000 = success
result.getMessage();    // "Success" or error description
result.getTrace_id();   // Trace ID for debugging
result.getData();       // Response payload (type T)
result.success();       // true if code == 200000
result.fail();          // true if code != 200000
```

## Error Handling

```java
LLPayResult<CheckoutResp> result = client.execute(request);
if (result.fail()) {
    System.err.println("Error: " + result.getCode() + " - " + result.getMessage());
    System.err.println("Trace ID: " + result.getTrace_id());
    return;
}
// Process result.getData()
```

## Project Structure

```
src/main/java/com/lianlian/global/payment/sdk/
├── LLPayClient.java              # Client interface
├── DefaultLLPayClient.java       # Client implementation
├── request/                      # Request models for each API
├── response/                     # Response models for each API
├── dto/                          # Shared DTOs (Customer, Product, Address, etc.)
├── support/                      # Enums (Service, Currency, Profiles) & utilities
└── utils/                        # SignUtils, HttpClientUtils, StringUtils
```

## License

Proprietary - LianLian Global
