package fr.educ.devoirsfaits.service;


import org.springframework.security.crypto.codec.Hex;

import javax.enterprise.inject.Produces;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypter {

    public Crypter() {
    }

    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Produces
    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        byte[] hash = digester.digest(
                str.getBytes(StandardCharsets.UTF_8));
        String MD5Cryter = new String(Hex.encode(hash));

        return MD5Cryter;
    }



}
