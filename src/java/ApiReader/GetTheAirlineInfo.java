/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiReader;

import java.util.concurrent.ExecutionException;

/**
 *
 * @author Yoana
 */
public class GetTheAirlineInfo
{
   private DisplayData data;
   String returnedInfo="";
   public String returnedtheBitchInfo(DisplayData data) throws InterruptedException, ExecutionException
   {
       returnedInfo=data.returnJsonStringAirlineInfo(2);
       return returnedInfo;
   }
   
}
