package com.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ireneusz Zagan on 13.04.18, 15:12
 * Contact: sefrys@gmail.com
 */
public class PasswordHash {

    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("sha-256");
        byte[] result  = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte element : result) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
