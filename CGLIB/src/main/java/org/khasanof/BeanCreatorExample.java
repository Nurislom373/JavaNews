package org.khasanof;

import lombok.SneakyThrows;
import org.springframework.cglib.beans.BeanGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/16/2023 4:30 PM
 */
public class BeanCreatorExample {

    @SneakyThrows
    public static void main(String[] args)  {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        assertEquals("some string value set by a cglib", getter.invoke(myBean));
    }

}
