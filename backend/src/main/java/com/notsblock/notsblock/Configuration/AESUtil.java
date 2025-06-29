package com.notsblock.notsblock.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AESUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH = 16;

    private final AESConfig aesConfig;
    private SecretKeySpec keySpec;

    // Inyección de AESConfig para obtener la clave desde application.yml
    public AESUtil(AESConfig aesConfig) {
        this.aesConfig = aesConfig;
    }

    // Inicialización del SecretKeySpec después de que se cargue la clave
    @PostConstruct
    public void init() {
        String key = aesConfig.getKey();
        if (key == null || key.length() != 16) {
            throw new IllegalArgumentException("La clave AES debe tener exactamente 16 caracteres.");
        }
        this.keySpec = new SecretKeySpec(key.getBytes(), "AES");
    }

    public String encrypt(String data) throws Exception {
        // Generar IV aleatorio
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Inicializar cifrador
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Cifrar datos
        byte[] encrypted = cipher.doFinal(data.getBytes());

        // Combinar IV + datos cifrados
        byte[] ivAndEncrypted = new byte[IV_LENGTH + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, IV_LENGTH);
        System.arraycopy(encrypted, 0, ivAndEncrypted, IV_LENGTH, encrypted.length);

        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    public String decrypt(String encryptedData) throws Exception {
        // Decodificar Base64
        byte[] ivAndEncrypted = Base64.getDecoder().decode(encryptedData);

        // Separar IV y datos cifrados
        byte[] iv = Arrays.copyOfRange(ivAndEncrypted, 0, IV_LENGTH);
        byte[] encrypted = Arrays.copyOfRange(ivAndEncrypted, IV_LENGTH, ivAndEncrypted.length);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Inicializar cifrador
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Descifrar datos
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted);
    }
}
