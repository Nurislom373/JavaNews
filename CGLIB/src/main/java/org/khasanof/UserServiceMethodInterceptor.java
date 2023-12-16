package org.khasanof;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/16/2023 4:23 PM
 */
public class UserServiceMethodInterceptor implements MethodInterceptor {

    private final Object realObj;

    public UserServiceMethodInterceptor(Object realObj) {
        this.realObj = realObj;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        if (args != null && args.length > 0 && args[0] instanceof String) {
            if (Objects.equals("Nurislom", args[0])) {
                return "Jeck Pot!";
            }
            result = method.invoke(realObj, args);
        }
        return result;
    }
}
