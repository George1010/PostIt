package com.backend_training.app.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private Cipher encryptCipher;
    private Cipher decryptCipher;

    @Value("${secret.key}")
    private String secretKeyStr;

    @PostConstruct
    public void init() throws Exception {
        SecretKey secretKey = new SecretKeySpec(secretKeyStr.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        this.encryptCipher = Cipher.getInstance(TRANSFORMATION);
        this.encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        this.decryptCipher = Cipher.getInstance(TRANSFORMATION);
        this.decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    public String encrypt(String data) throws Exception {
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());
        return Base64.getUrlEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getUrlDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
