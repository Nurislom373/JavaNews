package org.khasanof;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        CSVParser.parseObjectList("src/main/resources/MOCK_DATA.csv", Message.class);
    }
}
