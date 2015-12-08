/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yoana
 */
class GetAirlineInfo implements Callable<String> {

    String url;

    public GetAirlineInfo(String url) {
        this.url = url;
    }


    @Override
    public String call() throws Exception {
        return resultString(url);
    }

    private String resultString(String url) throws MalformedURLException, IOException {

        String response = "";
        boolean isFirst = true;
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty("Content-Type", "application/json;");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Method", "GET");
        try {
            int HttpResult = con.getResponseCode();
            if (HttpResult == 200) {
                Scanner responseReader = new Scanner(new InputStreamReader(con.getInputStream(), "utf-8"));
                String res = "";
                while (responseReader.hasNext()) {
                    res += responseReader.nextLine() + System.getProperty("line.separator");
                }
                if (isFirst) {
                    response += res;
                    isFirst = false;
                } else {
                    response += "," + res;
                }
            } //If you wan't to do something with the error response
            else if (HttpResult >= 400) {
                Scanner errorResponseReader = new Scanner(new InputStreamReader(con.getErrorStream(), "utf-8"));
                String res = "";
                while (errorResponseReader.hasNext()) {

                    response += errorResponseReader.nextLine();
                }

            }

        } catch (UnknownHostException e) {
            
            //Figure our how to report this
        }catch (IOException  e) {
            
            //Figure our how to report this
        }

//       response = response + "]";
        System.out.println("the response is "+response);
        return response;
    }
}

public class DisplayData {

        public List<String> urls = new ArrayList<String>();
    

    public void addUrls(List<String> url) 
    {
        urls=url;
    }

    public List<JSONObject> returnJsonStringAirlineInfo(int threadcount) throws InterruptedException, ExecutionException, JSONException {
        List<JSONObject> listJSON = new ArrayList<JSONObject>();

//       urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-15T00:00:00.000Z/3");
//        urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/STN/STN/2016-01-22T00:00:00.000Z/2");
//        urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/BCN/CPH/2016-01-16T00:00:00.000Z/2");
        List<Future<String>> listwithFutures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadcount);

        for (int i = 0; i < urls.size(); i++) {
            System.out.println("urls size "+urls.size());
            Callable<String> task = new GetAirlineInfo(urls.get(i));
            listwithFutures.add(executor.submit(task));
        }

        executor.shutdown();


        for (Future<String> list1 : listwithFutures) {

            org.json.JSONObject json;
            try {
                json = new org.json.JSONObject(list1.get(10, TimeUnit.SECONDS));
                System.out.println("the json is "+json.toString());
                  if (!json.has("httpError")) {
                listJSON.add(json);
            }
                
            } catch (TimeoutException ex) {
                Logger.getLogger(DisplayData.class.getName()).log(Level.SEVERE, null, ex);
            }
//            JSONObject json = new JSONObject(list1.get());
//                  if (!json.has("httpError")) {
//                listJSON.add(json);
//            }
//          
            //listJSON.add(json);
//            stringconcat += list1.get();
        }

        return listJSON;
    }

    public DisplayData(String... args) {

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, JSONException {

//       for (int i = 0; i <20; i++) {
        DisplayData data = new DisplayData();
       // data.addUrls("CPH", "STN", "2016-01-14T19:00:00.000Z", 3);
        List<JSONObject> obj = data.returnJsonStringAirlineInfo(10);
        System.out.println("the size of the arraylist is "+obj.size());
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i).toString());
            
        }
//   }

//        System.out.println("Duration: " + duration / 1_000_000 + " milliseconds");
    }
}
