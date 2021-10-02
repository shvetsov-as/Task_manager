/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glassBox;

/**
 *
 * @author User
 */
public final class PasswdCheck {

    private static final int PWD_LEN = 8;

    private static char ch;
    private static boolean flagUpCase = false;
    private static boolean flagLwCase = false;
    private static boolean flagDigit = false;

    private static void reset() {
        ch = ' ';
        flagUpCase = false;
        flagLwCase = false;
        flagDigit = false;
    }

    public static boolean passwdCheck(String password) {

        if (password.trim().equals("") || password.isEmpty() || password.trim().length() < PWD_LEN) {
            return false;
        }

        password = password.trim();

        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isSpaceChar(ch)) {
                reset();
                return false;
            } else if (Character.isLowerCase(ch)) {
                flagLwCase = true;
            } else if (Character.isUpperCase(ch)) {
                flagUpCase = true;
            } else if (Character.isDigit(ch)) {
                flagDigit = true;
            }
        }

        if (flagLwCase && flagUpCase && flagDigit) {
            reset();
            return true;
        }
        return false;
    }
}
