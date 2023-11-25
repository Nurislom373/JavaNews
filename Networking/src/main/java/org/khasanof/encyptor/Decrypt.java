package org.khasanof.encyptor;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.cert.Certificate;

/**
 * @author Nurislom
 * @see org.khasanof.encyptor
 * @since 11/25/2023 11:22 AM
 */
public class Decrypt {

    @SneakyThrows
    public byte[] decryptMessage(byte[] encryptedMessage, byte[] keyBytes) {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedMessage);
    }

    @SneakyThrows
    public byte[] decryptMessage(byte[] encryptedMessage, Certificate certificate) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, certificate);
        return cipher.doFinal(encryptedMessage);
    }

}
