/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CosticaTeodor
 */
public class DateFromIsoString {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //Java Date from ISO-8601 string
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date1 = sdf1.parse("01-01-2016 06:00:00");
        System.out.println(date1);

        DateFormat sdfISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //Date date2 = sdfISO.parse("2001-07-04T12:08:56.235-0700");
        Date date3 = sdfISO.parse("2015-12-23T23:00:00.000Z");
        System.out.println(date3);

    }

}
