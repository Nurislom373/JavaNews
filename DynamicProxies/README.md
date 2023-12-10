# Dynamic Proxies

Oddiygina qilib aytganda, proxylar o'z qurilmalari orqali (odatda real usullarga) funksiya chaqiruvini o'tkazadigan
frontlar yoki o'ramlaridir(wrapper).

Dynamic proxylar bitta usul bilan bitta classga ixtiyoriy sonli usullar bilan ixtiyoriy classlarga bir nechta usul
chaqiruvlariga xizmat ko'rsatish imkonini beradi. Dynamic proxyni o'ziga xos _Facade_ deb hisoblash mumkin, lekin u
o'zini har qanday interfacei amalga oshirishga o'xshatishi mumkin. Tagida u barcha usul chaqiruvlarini bitta handler
yani ishlov beruvchiga yo'naltiradi.

Bu kundalik dasturlash vazifalari uchun mo'ljallangan vosita bo'lmasada, dinamik proxylar framework yozuvchilar
uchun juda foydali bo'lishi mumkin. Bu xususiyat standard JDK ichiga o'rnatilgan, shuning uchun qo'shimcha bog'liqliklar
talab qilinmaydi.

```java
public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(
      DynamicInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) 
      throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());

        return 42;
    }
}
```

## Source Link

[xperti.io](https://xperti.io/blogs/java-dynamic-proxies-introduction/#:~:text=What%20is%20Java%20Dynamic%20Proxy,instead%20of%20the%20original%20one) </br>
[baeldung](https://www.baeldung.com/java-dynamic-proxies)
[medium](https://medium.com/@spac.valentin/java-dynamic-proxy-mechanism-and-how-spring-is-using-it-93756fc707d5)
