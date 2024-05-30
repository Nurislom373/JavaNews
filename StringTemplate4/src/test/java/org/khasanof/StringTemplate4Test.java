package org.khasanof;

import org.junit.jupiter.api.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/30/2024 6:45 PM
 */
public class StringTemplate4Test {

    @Test
    void firstTestRenderStringTemplate4ShouldSuccess() {
        ST hello = new ST("Hello <name>");
        hello.add("name", "World");
        assertEquals("Hello World", hello.render());
    }

    @Test
    void secondTestRenderStringTemplateGroupDirShouldSuccess() {
        STGroup group = new STGroupDir("src/main/resources/tmp");
        ST st = group.getInstanceOf("decl");
        st.add("type", "int");
        st.add("name", "x");
        st.add("value", 0);
        String result = st.render(); // yields "int x = 0;"
        System.out.println("result = " + result);
        assertEquals("int x = 0;", result);
    }
}
