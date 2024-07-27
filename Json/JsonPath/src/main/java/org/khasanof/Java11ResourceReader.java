package org.khasanof;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/6/2024 12:33 AM
 */
public abstract class Java11ResourceReader {

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    public static String getResourceFileAsString(String fileName) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(Java11ResourceReader.class.getClassLoader().getResource(fileName).toURI()));
    }
}
