/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author User
 */
public class Task_status {

    public static boolean getStatusCodeByName(String statusName) {
        if (statusName != null && !statusName.trim().isEmpty()) {
            return statusName.trim().compareToIgnoreCase("Выполнено") == 0;
        }
        return false;
    }

    public static String getStatusNameByCode(boolean statusCode) {
        if (statusCode) {
            return "Выполнено";
        }
        return "Не выполнено";
    }
    
    public static String getStatusNameByCodeTABLE(boolean statusCode) {
        if (statusCode) {
            return "<tr> <td align=\"center\"> Выполнено </td></tr>";
        }
        return "<tr> <td align=\"center\"> Не выполнено </td></tr>";
    }
    
    public static String getStatusNameByCodeBUTTON(boolean statusCode) {
        if (statusCode) {
            return "<option> Выполнено </option>"; 
        }
        return "<option> Не выполнено </option>";
    }

}
