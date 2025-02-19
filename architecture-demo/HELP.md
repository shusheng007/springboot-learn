# Getting Started

## How to use

1. create order
```aidl
    curl --location 'localhost:8080/orders/' \
    --header 'Content-Type: application/json' \
    --data '{
        "products": [
            {
                "count": 2,
                "product": {
                    "productId": "123",
                    "type": "phone",
                    "price": 3000
                }
            }
        ]
    }'
```
2. complete order

```aidl
    curl --location --request PATCH 'localhost:8080/orders/1' \
    --header 'Content-Type: application/json' \
    --data '{
        "payType": "ALIPAY"
    }'
```



