package org.khasanof.cglib;

import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.core.GeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.assertj.core.api.Assertions;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/14/2023 9:23 PM
 */
public class CglibTest {

    public static void main(String[] args) {
        String val = "Hello Abdulloh!";

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setUseCache(true);
        enhancer.setCallback((FixedValue) () -> "Hello Abdulloh!");
        PersonService proxyService = (PersonService) enhancer.create();

        String sayHello = proxyService.sayHello(null);
        Assertions.assertThat(sayHello).isEqualTo(val);
    }

}
