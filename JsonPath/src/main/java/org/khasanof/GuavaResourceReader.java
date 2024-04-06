package org.khasanof;


import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/6/2024 12:26 AM
 */
public abstract class GuavaResourceReader {

    /**
     * Reads given resource file as a string.
     *
     * @param fileName to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    public static String getResourceFileAsString(String fileName) throws IOException {
        return Resources.toString(Resources.getResource(fileName), StandardCharsets.UTF_8);
    }
}
