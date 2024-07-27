package org.khasanof;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/6/2024 12:37 AM
 */
public abstract class ApacheIOResourceReader {

    /**
     * Reads given resource file as a string.
     *
     * @param fileName to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    public static String getResourceFileAsString(String fileName) throws IOException {
        return IOUtils.toString(Objects.requireNonNull(ApacheIOResourceReader.class.getResourceAsStream(fileName)), StandardCharsets.UTF_8);
    }
}
