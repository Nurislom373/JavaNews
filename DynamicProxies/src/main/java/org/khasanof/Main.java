package org.khasanof;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        exampleThree();
    }

    private static void exampleTwo() {
        Map proxyInstance2 = (Map) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Map.class}, ((proxy, method, args1) -> {
            if (method.getName().equals("get")) {
                return 42;
            } else {
                throw new UnsupportedOperationException("Unsupported method: " + method.getName());
            }
        }));
        Object object = proxyInstance2.get("32");
        System.out.println("object = " + object);

        proxyInstance2.put("42", "42");
    }

    private static void exampleOne() {
        Map mapProxy = (Map) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Map.class},
                new DynamicInvocationHandler());
        mapProxy.put(1, 1);
    }

    private static void exampleThree() {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[] { Map.class },
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();
    }

}
