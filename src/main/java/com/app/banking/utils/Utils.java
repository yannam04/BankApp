package com.app.banking.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {
    private static void getMonthNumber(String monthName) {

        List<String> st = new ArrayList<>();
        Map<String,String> str = new HashMap<>();
        //str.values().stream().sorted(Comparator.comparing(t->t).re)
        try {
            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int monthNumber = cal.get(Calendar.MONTH);
            System.out.println(monthNumber);
        }catch(Exception exe){
            exe.printStackTrace();
        }

    }
    //returning sql date
    public static java.sql.Date convertDate(String dob) {
        if (StringUtils.hasText(dob)) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy"); // New Pattern E: 01-Feb-2013
            java.util.Date date; // Returns a Date format object with the pattern
            try {
                date = sdf1.parse(dob);
                java.sql.Date sqlDOb = new java.sql.Date(date.getTime());
                return sqlDOb;
            } catch (ParseException e) {
                System.err.println("Exception while converting dob.." + e.getMessage());
            }
        }
        return null;
    }
}
