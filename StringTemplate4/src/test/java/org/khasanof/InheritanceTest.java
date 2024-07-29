package org.khasanof;

import org.junit.jupiter.api.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/30/2024 9:16 AM
 */
public class InheritanceTest {

    @Test
    void firstTest() {
        STGroup java1_4 = new STGroupFile("src/main/resources/tmp/Java1_4.stg");
        STGroup java1_5 = new STGroupFile("src/main/resources/tmp/Java1_5.stg");
        System.out.println( getCode(java1_4) );
        System.out.println( getCode(java1_5) );
    }

    public String getCode(STGroup java) {
        ST cl = java.getInstanceOf("class"); // create class
        cl.add("name", "T");
        ST consts = java.getInstanceOf("constants");
        consts.add("typename", "MyEnum");
        consts.add("names", new String[] {"A","B"});
        cl.add("members", consts); // add constants as a member
        return cl.render();
    }
}
