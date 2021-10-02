/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glassBox;

import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version version 1.0
 * @author User
 */
public class RandomTest {

    public static void main(String[] args) {

        System.out.println("--------   hashGen  --------");
        // ====================   hashGen
        try {
            //String s = SaltGen.saltGen();
            HashMap<String, String> salt_hash = new HashMap<>();
            salt_hash = HashGen.hashGen("Admin001");
            //System.out.println(mapResult.get("hash"));
            System.out.println(HashCheck.hashCheck(salt_hash, "Admin001"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RandomTest.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println("---------------------------------------\n\n");
//        System.out.println("--------   Password Test  --------");
//        
//        System.out.println(PasswdCheck.passwdCheck("Asssssss5sssssa"));
//        
//        user_login 'Admin'  pwd  "Admin001"  pwdhash '423A89387AE2B8C81395E70F4488A62B698A967DF2DFA5430B62E1CEDF8112C649714F1A935A8AFE54B5EA6670C92F4EA06A7DA208BCF8CC6860851E083E1493'
//          salt 'wpJOantWKn'
//        System.out.println("---------------------------------------\n\n");
    }

}
