package org.khasanof;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;
import org.khasanof.domain.LimitedTotp;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/25/2024 5:52 PM
 */
public class LimitedTotpGenerator {

    private final Map<String, LimitedTotp> totpMap = new HashMap<>();

    private final TOTPGenerator totpGenerator;

    public LimitedTotpGenerator(byte[] secretKey) {
        this(buildDefaultTOTPGenerator(secretKey));
    }

    public LimitedTotpGenerator(TOTPGenerator totpGenerator) {
        this.totpGenerator = totpGenerator;
    }

    /**
     *
     * @return
     */
    public LimitedTotp generate() {
        return createLimitedTotp(totpGenerator.now(), 1);
    }

    /**
     *
     * @param limit
     * @return
     */
    public LimitedTotp generate(int limit) {
        return createLimitedTotp(totpGenerator.now(), limit);
    }

    private LimitedTotp createLimitedTotp(String code, int limit) {
        return new LimitedTotp(code, limit);
    }

    /**
     *
     * @param code
     * @return
     */
    public boolean verify(String code) {
        LimitedTotp limitedTotp = totpMap.get(code);
        if (limitedTotp == null) {
            return false;
        }
        return verify(limitedTotp);
    }

    /**
     *
     * @param limitedTotp
     * @return
     */
    public boolean verify(LimitedTotp limitedTotp) {
        if (limitedTotp.getLimit().longValue() < 1) {
            return false;
        }
        if (totpGenerator.verify(limitedTotp.getCode())) {
            limitedTotp.getLimit().decrementAndGet();
            return true;
        }
        return false;
    }

    private static TOTPGenerator buildDefaultTOTPGenerator(byte[] secretKey) {
        return new TOTPGenerator.Builder(secretKey)
                .withHOTPGenerator(builder -> {
                    builder.withPasswordLength(6);
                    builder.withAlgorithm(HMACAlgorithm.SHA256); // SHA256 and SHA512 are also supported
                })
                .withPeriod(Duration.ofSeconds(1))
                .build();
    }
}
