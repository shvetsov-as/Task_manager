/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author User
 */
public final class BrutCheck {

    private static Integer randomResult = null;//zamenit random

    public static void setRandomResult(Integer randomResult) {
        BrutCheck.randomResult = randomResult;
    }

    public static boolean check(Integer sum) {
        if (sum.equals(randomResult)) {
            randomResult = null;
            return true;
        }
        return false;
    }

}
