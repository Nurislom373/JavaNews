package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/1/26
 */
public class HexASCIIConverter {

    public static String toHex(String text) {
        byte[] bytes = text.getBytes();
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }

    public static String fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return new String(bytes);
    }
}
