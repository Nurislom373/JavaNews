package org.khasanof;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/10/2023 2:26 PM
 */
public class TimingDynamicInvocationHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();
    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;
        for (Method declaredMethod : target.getClass().getDeclaredMethods()) {
            this.methods.put(declaredMethod.getName(), declaredMethod);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;
        System.out.printf("Executing %s finished in %s ns \n", method.getName(), elapsed);
        return result;
    }
}
