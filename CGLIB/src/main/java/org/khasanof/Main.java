package org.khasanof;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        MethodInterceptor methodInterceptor = new UserServiceMethodInterceptor(userService);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userService.getClass());
        enhancer.setCallback(methodInterceptor);

        UserService service = (UserService) enhancer.create();
        String nurislom = service.hello("Nurislom");
        System.out.println("nurislom = " + nurislom);
    }

}
