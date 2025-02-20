package org.khasanof;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.HOTPGenerator;
import com.bastiaanjansen.otp.TOTPGenerator;
import org.khasanof.domain.LimitedTotp;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class Main {

    public static final String SECRET_KEY = "47356473543543";
    public static final byte[] SECRET = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

    private static final HOTPGenerator hotp = new HOTPGenerator.Builder(SECRET)
            .withPasswordLength(8)
            .withAlgorithm(HMACAlgorithm.SHA256)
            .build();

    private static final TOTPGenerator totp = new TOTPGenerator.Builder(SECRET)
            .withHOTPGenerator(builder -> {
                builder.withPasswordLength(6);
                builder.withAlgorithm(HMACAlgorithm.SHA1); // SHA256 and SHA512 are also supported
            })
            .withPeriod(Duration.ofSeconds(3))
            .build();

    public static void main(String[] args) throws InterruptedException {
        LimitedTotpGenerator totpGenerator = new LimitedTotpGenerator(SECRET);

        String code1 = TotpTokenGenerator.generateTOTP(SECRET_KEY);
        System.out.println("code = " + code1);
        LimitedTotp generate1 = totpGenerator.generate(2);
        System.out.println("generate = " + generate1);

        Thread.sleep(1000);

        String code = TotpTokenGenerator.generateTOTP(SECRET_KEY);
        System.out.println("code = " + code);
        LimitedTotp generate = totpGenerator.generate(2);
        System.out.println("generate = " + generate);
    }

    private static void test2() throws InterruptedException {
        String code = totp.now();
        System.out.println("code = " + code);

        // To verify a token:
        boolean isValid = totp.verify(code);
        System.out.println("isValid = " + isValid);

        Thread.sleep(3000);

        isValid = totp.verify(code);
        System.out.println("isValid = " + isValid);
    }

    private static void хотп() {
        int counter = 1;
        String code = hotp.generate(counter);
        System.out.println("code = " + code);

        // To verify a token:
        System.out.println("counter = " + counter);
        boolean isValid1 = hotp.verify(code, counter);
        System.out.println("isValid1 = " + isValid1);

        System.out.println("counter = " + counter);
        boolean isValid2 = hotp.verify(code, counter);
        System.out.println("isValid2 = " + isValid2);

        // Or verify with a delay window
        System.out.println("counter = " + counter);
        boolean isValid = hotp.verify(code, counter, 2);
        System.out.println("isValid = " + isValid);
    }
}