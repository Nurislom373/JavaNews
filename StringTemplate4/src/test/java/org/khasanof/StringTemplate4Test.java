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

    @Test
    void thirdTestRenderStringTemplate4ShouldSuccess() {
        ST st = new ST("<b>$u.id$</b>: $u.name$", '$', '$');
        st.add("u", new User(999, "parrt"));
        String result = st.render(); // "<b>999</b>: parrt"

        assertEquals("<b>999</b>: parrt", result);
    }

    @Test
    void fourthTestRenderStringTemplateInjectingDataAggregateAttributes() {
        ST st = new ST("<items:{it|<it.id>: <it.lastName>, <it.firstName>\n}>");
        st.addAggr("items.{ firstName ,lastName, id }", "Ter", "Parr", 99); // add() uses varargs
        st.addAggr("items.{firstName, lastName ,id}", "Tom", "Burns", 34);
        String expecting = "99: Parr, Ter\n" + "34: Burns, Tom\n";

        assertNotNull(st.render());
    }

    @Test
    void fifthTestRenderStringTemplateFormatShouldSuccess() {
        int[] num = new int[] {3,9,20,2,1,4,6,32,5,6,77,888,2,1,6,32,5,6,77,
                        4,9,20,2,1,4,63,9,20,2,1,4,6,32,5,6,77,6,32,5,6,77,
                        3,9,20,2,1,4,6,32,5,6,77,888,1,6,32,5};
        String t =
                ST.format(30, "int <%1>[] = { <%2; wrap, anchor, separator=\", \"> };", "a", num);
        System.out.println(t);
    }
}
