package org.khasanof.cglib;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/14/2023 9:17 PM
 */
public class PersonService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }

}
