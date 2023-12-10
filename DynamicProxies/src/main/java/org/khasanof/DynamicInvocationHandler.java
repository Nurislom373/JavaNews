package org.khasanof;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/10/2023 2:07 PM
 */
public class DynamicInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Invoked method : " + method.getName());
        return 42;
    }

}
