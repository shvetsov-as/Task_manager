/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.password;

import java.util.Random;

/**
 *
 * @author User
 */
public final class SaltGen {

    private static final int SALT_LEN = 10;
    private static final int UPPER_CASE_BOUND_MAX = 90;//UTF-8
    private static final int UPPER_CASE_BOUND_MIN = 65;
    private static final int LOWER_CASE_BOUND_MAX = 122;
    private static final int LOWER_CASE_BOUND_MIN = 97;

    private static Random random = new Random();
    private static StringBuilder saltBuilder = new StringBuilder();

    private static char ch;
    private static String strSalt;

    public static String saltGenerated() {

        for (int i = 0; i < SALT_LEN; i++) {
            do {
                ch = (char) random.nextInt(123);
            } while ((ch >= UPPER_CASE_BOUND_MIN) ^ (ch <= UPPER_CASE_BOUND_MAX) && (ch >= LOWER_CASE_BOUND_MIN) ^ (ch <= LOWER_CASE_BOUND_MAX));
            
            saltBuilder.append(ch);
        }

        //System.out.println(saltBuilder.toString());
        strSalt = saltBuilder.toString();
        saltBuilder.delete(0, strSalt.length());
        return strSalt;
    }
}

