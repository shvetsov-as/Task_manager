/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.password;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class HashCheck {

    private static String passwordPassed;
    private static String salt;

    public static boolean hashCheck(HashMap<String, String> salt_hash, String password) throws NoSuchAlgorithmException {

        salt = salt_hash.get("salt");
        passwordPassed = HashCore.getHash(salt, password);
        
        System.out.println(salt);
        System.out.println(passwordPassed);
        
        return passwordPassed.equals(salt_hash.get("hash"));//hash 128 for SHA-512 (2M - 1 min 40 sec); hash 64 for SHA-256 (2M - 52 sec)
    }
}
