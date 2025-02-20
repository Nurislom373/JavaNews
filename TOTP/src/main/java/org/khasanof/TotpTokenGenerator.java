package org.khasanof;

import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/25/2024 5:26 PM
 */
public class TotpTokenGenerator {

    private static final int TIME_STEP = 5; // Token is valid for 30 seconds
    private static final int TOKEN_DIGITS = 6; // 6-digit token
    private static final String HMAC_ALGO = "HmacSHA256";
    private static final int ALLOWED_DRIFT = 0; // Allowed time steps before/after the current one

    public static String generateTOTP(String secretKey) {
        long timeIndex = getTimeIndex();

        byte[] decodedKey = decodeBase32(secretKey);

        byte[] data = longToBytes(timeIndex);

        byte[] hmac = hmacSha1(decodedKey, data);

        int otp = truncate(hmac) % (int) Math.pow(10, TOKEN_DIGITS);

        return String.format("%0" + TOKEN_DIGITS + "d", otp);
    }

    // Get the current time step based on the system time
    private static long getTimeIndex() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        return currentTimeMillis / TIME_STEP;
    }

    // Decode base32 secret key
    private static byte[] decodeBase32(String secretKey) {
        Base32 base32 = new Base32();
        return base32.decode(secretKey);
    }

    // Generate HMAC-SHA1 signature
    private static byte[] hmacSha1(byte[] key, byte[] data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, HMAC_ALGO);
            Mac mac = Mac.getInstance(HMAC_ALGO);
            mac.init(secretKeySpec);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate HMAC-SHA1", e);
        }
    }

    // Convert long to byte array
    private static byte[] longToBytes(long value) {
        byte[] data = new byte[8];
        for (int i = 7; i >= 0; i--) {
            data[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return data;
    }

    // Truncate to generate the final OTP value
    private static int truncate(byte[] hmac) {
        int offset = hmac[hmac.length - 1] & 0xF;
        return ((hmac[offset] & 0x7F) << 24) |
                ((hmac[offset + 1] & 0xFF) << 16) |
                ((hmac[offset + 2] & 0xFF) << 8) |
                (hmac[offset + 3] & 0xFF);
    }

    // Verify the provided TOTP with the secret key
    public static boolean verifyTOTP(String secretKey, String providedTotp) {
        long currentTimeIndex = getTimeIndex();

        byte[] decodedKey = decodeBase32(secretKey);

        // Check the current, previous, and next time steps (to account for time drift)
        for (int i = -ALLOWED_DRIFT; i <= ALLOWED_DRIFT; i++) {
            long timeIndex = currentTimeIndex + i;
            byte[] data = longToBytes(timeIndex);

            byte[] hmac = hmacSha1(decodedKey, data);

            int otp = truncate(hmac) % (int) Math.pow(10, TOKEN_DIGITS);

            String generatedTotp = String.format("%0" + TOKEN_DIGITS + "d", otp);
            if (generatedTotp.equals(providedTotp)) {
                return true; // TOTP is valid
            }
        }

        return false; // TOTP is invalid
    }

    public static void main(String[] args) throws InterruptedException {
        // Example usage
        String secretKey = "JBSWY3DPEHPK3PXP"; // This should be securely stored and unique for each user

        // Generate TOTP
        String generatedTotp = generateTOTP(secretKey);
        System.out.println("Generated TOTP: " + generatedTotp);

        // Verify TOTP (for example, the user provides this value)
        boolean isValid = verifyTOTP(secretKey, generatedTotp);
        System.out.println("Is the provided TOTP valid? " + isValid);

        Thread.sleep(2000);

        // Verify TOTP (for example, the user provides this value)
        boolean isValid2 = verifyTOTP(secretKey, generatedTotp);
        System.out.println("Is the provided TOTP valid? " + isValid2);
    }

}
