package org.khasanof;

import org.junit.jupiter.api.Test;
import org.khasanof.encyptor.Decrypt;
import org.khasanof.encyptor.Encrypt;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/25/2023 11:24 AM
 */
public class EncryptAndDecryptTest {

    private final Encrypt encrypt = new Encrypt();
    private final Decrypt decrypt = new Decrypt();

    @Test
    void firstTest() {
        String encryptionKeyString =  "thisisa128bitkey";
        String originalMessage = "This is a secret message";
        byte[] encryptionKeyBytes = encryptionKeyString.getBytes();

        byte[] encryptMessage = encrypt.encryptMessage(originalMessage.getBytes(), encryptionKeyBytes);
        System.out.println("encryptMessage = " + Arrays.toString(encryptMessage));
        String strEncryptMessage = new String(encryptMessage);
        System.out.println("strEncryptMessage = " + strEncryptMessage);

        byte[] decryptMessage = decrypt.decryptMessage(encryptMessage, encryptionKeyBytes);
        String strDecryptMessage = new String(decryptMessage);
        System.out.println("strDecryptMessage = " + strDecryptMessage);
        assertAll(() -> {
            assertEquals(originalMessage, strDecryptMessage);
        });
    }

}
