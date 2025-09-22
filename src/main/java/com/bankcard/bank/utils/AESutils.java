package com.bankcard.bank.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESutils {
    private static final String IV = "1234567890123456";

    /**
     * @param plainText text that need to be encrypted. Here it will be PAN
     * @param key AES secret key
     * @return encrypted text (base64)
     * @throws Exception
     */
    public static String encrypt(String plainText, String key) throws Exception {
//        AES encription Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        change key to AES Object
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
//        encription
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
//        return value by base64
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * @param cipherText encripted text (base64)
     * @param key AES secret key
     * @return decrypted text
     * @throws Exception
     */
    public static String decrypt(String cipherText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

//        decode base64 text to byte array
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
//        decription
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
//        return value
        return new String(decryptedBytes, "UTF-8");
    }
}
