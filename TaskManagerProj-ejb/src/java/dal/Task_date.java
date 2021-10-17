/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author User
 */
public class Task_date {
    
    private static LocalDate date;
    private static String strDate;
    
    public static String getDateDDMonthYYYY(Date dbDate){
        date = dbDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        strDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return strDate;
    }
}
