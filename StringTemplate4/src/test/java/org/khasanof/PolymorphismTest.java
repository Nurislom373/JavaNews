package org.khasanof;

import org.junit.jupiter.api.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/30/2024 9:30 AM
 */
public class PolymorphismTest {

    @Test
    void firstTest() {
        STGroup group = new STGroupFile("src/main/resources/tmp/dbg.stg");
        ST st = group.getInstanceOf("method");
        st.add("name", "print");
        st.add("code", "System.out.println(\"Hello\");");

        String render = st.render();
        System.out.println("render = " + render);
    }
}
