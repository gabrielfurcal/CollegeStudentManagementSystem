/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 *
 * @author Gabriel_Liberato
 */
public class Utility
{
    public static String stringToSHA256(String text)
    {
        MessageDigest md = null;
        
        try
        {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
            return null;
        }
        
        byte[] hash = md.digest(text.getBytes());
        
        StringBuffer sb = new StringBuffer();
        
        for (byte b : hash)
        {
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    }
    
    public static boolean isNullOrEmpty(String text)
    {
        if(text == null || text.equals(""))
            return true;
        else
            return false;
    }
    
    public static boolean isNullOrWhiteSpace(String text)
    {
        if(text == null || text.equals("") || text.equals(" "))
            return true;
        else
            return false;
    }
    
    public static String toFullNameDayOfWeek(String abbreviation)
    {
        if(isNullOrWhiteSpace(abbreviation))
            return null;
        
        switch(abbreviation.toUpperCase())
        {
            case "MON":
                return "Monday";
            case "TUE":
                return "Tuesday";
            case "WED":
                return "Wednesday";
            case "THU":
                return "Thursday";
            case "FRI":
                return "Friday";
            case "SAT":
                return "Saturday";
            case "SUN":
                return "Sunday";
            default:
                return "Day abbreviation does not exist";
        }
    }
    
    public static int[] getCurrentYearAndQuarter()
    {
        int currentPeriod = 0;
        
        Calendar calendar = Calendar.getInstance();
        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        
        if(month >= 1 && month <=4)
            currentPeriod = 1;
        else if(month >= 5 && month <= 8)
            currentPeriod = 2;
        else if(month >= 9 && month <= 12)
            currentPeriod = 3;
        
        int[] currentYearAndQuarter = { year, currentPeriod };
        
        return currentYearAndQuarter;
    }
}