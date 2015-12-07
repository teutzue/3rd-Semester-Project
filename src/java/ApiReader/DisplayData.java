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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public String call() throws Exception {
        return resultString(url);
    }

    private String resultString(String url) throws MalformedURLException, IOException {

//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            return jsonText;
//        } finally {
//            is.close();
//        }
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
        }

//       response = response + "]";
        return response;
    }
}

public class DisplayData {

    private List<String> urls = new ArrayList<String>();

    public void addUrls(String from, String to, String date, int passengernumber) {
        String varPath = "http://angularairline-plaul.rhcloud.com/api/flightinfo/" + from + "/" + to + "/" + date + "/" + passengernumber;
        urls.add(varPath);
        String ourAirline = "http://sargardon-001-site1.atempurl.com/api/flightinfo/" + from + "/" + to + "/" + date + "/" + passengernumber;
        urls.add(ourAirline);
    }

    public List<JSONObject> returnJsonStringAirlineInfo(int threadcount) throws InterruptedException, ExecutionException, JSONException {
        List<JSONObject> listJSON = new ArrayList<JSONObject>();

//        urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-15T00:00:00.000Z/3");
//        urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/BCN/CPH/2016-01-16T00:00:00.000Z/2");
        List<Future<String>> listwithFutures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadcount);

        for (int i = 0; i < urls.size(); i++) {

            Callable<String> task = new GetAirlineInfo(urls.get(i));
            listwithFutures.add(executor.submit(task));
        }

        executor.shutdown();
        String stringconcat = "";

        for (Future<String> list1 : listwithFutures) {

            org.json.JSONObject json = new org.json.JSONObject(list1.get());
            if (!json.has("httpError")) {
                listJSON.add(json);
            }
            //listJSON.add(json);
//            stringconcat += list1.get();
        }
//         for (int i = 0; i < listwithFutures.size(); i++) {
//           stringconcat="[";
//           if(i>0)
//           {
//               stringconcat+=",";
//           }
//             if(listwithFutures.get(i)!=null)
//             {
//                 stringconcat+=listwithFutures.get(i);
////                 stringconcat+=",";
//             }
//         }
//       // stringconcat='['+stringconcat+']';
//        stringconcat+="]";

//       System.out.println(stringconcat);
        return listJSON;
    }

    public DisplayData(String... args) {

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, JSONException {

//       for (int i = 0; i <20; i++) {
        List<JSONObject> obj = new DisplayData().returnJsonStringAirlineInfo(10);
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i).toString());
        }
//   }

//        System.out.println("Duration: " + duration / 1_000_000 + " milliseconds");
    }
}
