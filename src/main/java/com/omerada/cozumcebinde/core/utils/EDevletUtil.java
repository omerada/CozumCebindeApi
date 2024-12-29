package com.omerada.cozumcebinde.core.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class EDevletUtil {

    public static byte[] getSHA(String input)
    {
        MessageDigest md;
        try {
             md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    public static String createCodeChallenge(String codeVerifier)
    {
        String codeChallenge = Base64.getEncoder().encodeToString(getSHA(codeVerifier));

        String codeChallenge2 = codeChallenge.substring(0, codeChallenge.length() - 1);
        String codeChallenge3 = codeChallenge2.replaceAll("\\+", "-");
        String codeChallenge4 = codeChallenge3.replaceAll("/", "_");
        return codeChallenge4;
    }

    public static String  createCodeVerifier()
    {
        String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        do {
            int index = random.nextInt(50);
            sb.append(allowedChars.charAt(index));

        } while (sb.length() != 50);

        return sb.toString();
    }
}
