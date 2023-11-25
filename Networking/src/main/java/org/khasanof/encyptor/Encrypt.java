package org.khasanof.encyptor;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.cert.Certificate;

/**
 * @author Nurislom
 * @see org.khasanof.encyptor
 * @since 11/25/2023 11:15 AM
 */
public class Encrypt {

    @SneakyThrows
    public byte[] encryptMessage(byte[] message, byte[] keyBytes) {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }

    @SneakyThrows
    public byte[] encryptMessage(byte[] message, Certificate certificate) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, certificate);
        return cipher.doFinal(message);
    }

}
