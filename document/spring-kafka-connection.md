# config SASL_SSL

```
spring:
  kafka:
    bootstrap-servers: broker1:1234,broker2:1234,broker3:1234
    properties:
      security.protocol: SASL_SSL
      sasl.mechanism: SCRAM-SHA-512
      sasl.jaas.config: org.apache.kafka.common.security.scram.ScramLoginModule required username="username" password="password";
```

# config SSL

```
spring:
  kafka:
    bootstrap-servers: broker1:1234,broker2:1234,broker3:1234
    security:
      protocol: SSL
    ssl:
      key-store-type: PEM
      key-store-key: file:/cert/private-key.pem
      key-password: your-privatekey-password
      key-store-certificate-chain: file:/cert/client-cert.pem
      trust-store-type: PEM
      trust-store-certificates: file:/cert/server-cert.pem
```

spring-kafka 从2.7.x版本支持了.pem格式的证书，以前只支持JKS或PKCS12格式。

双向验证比较容易让人懵逼。

可以使用

```
spring:
  kafka:
  properties:
    xxx1:
    xxx2: 

```

org.apache.kafka.common.config.SslConfigs 获取各种key

ssl:
key-store-type: PEM

client的key store使用PEM类型，例如JKS,PKCS12,PEM。

key-store-key: file:/cert/private-key.pem

client的private key 文件

key-password: your-privatekey-password

client的private key的密码，如果你的private key产生时候没有设置密码，这块不不需要填

key-store-certificate-chain: file:/cert/client-cert.pem

client的证书文件

如果单向验证上面的配置就足够了。上面的配置支持服务器验证客户端的合法性。

下面的配置为，客户端验证服务端。顾名思义，trust-store-xxx, 即客户端用来信任外来连接的凭证
trust-store-type: PEM

client的store使用PEM类型，

trust-store-certificates: file:/cert/server-cert.pem
key-store-type: PEM
key-store-certificate-chain 填客户端.pem证书文件