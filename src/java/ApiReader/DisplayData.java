package ApiReader;


import java.util.concurrent.Callable;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Yoana
 */
 class GetAirlineInfo  implements Callable<String>
{
    String url;

    public GetAirlineInfo(String url) {
        this.url = url;
    }

    @Override
    public synchronized String call() throws Exception {
            return resultString(url);
    }
    
           private  String resultString(String url) {
       
		String output="";
               	  try {
		URL url4e = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url4e.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
                String outputline="";
		System.out.println("Output from Server .... \n");
		while ((outputline = br.readLine()) != null) {
			System.out.println(outputline);
                        output = outputline+'\n';
		}
		conn.disconnect();
	  } catch (MalformedURLException e) {
		e.printStackTrace();
	  } catch (IOException e) {
		e.printStackTrace();
	  }
        return output;
	}
 }


         public class DisplayData{
     public String returnJsonStringAirlineInfo(int threadcount) throws InterruptedException, ExecutionException{
        List<String> urls = new ArrayList<String>();
        urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3");
          urls.add("http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-04T23:00:00.000Z/2");

          
          
          
        List<Future<String>> listwithFutures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadcount);

        for (int i = 0; i < urls.size(); i++) {
 
            Callable<String> task = new GetAirlineInfo(urls.get(i));  
            listwithFutures.add(executor.submit(task));
        }

        executor.shutdown();
     String stringconcat="";
        
        for (Future<String> list1 : listwithFutures) {
   
            stringconcat += list1.get();
        }
        
        
//        System.out.println("Number of primes: " + stringconcat);
        return stringconcat;
    }

    public DisplayData(String...args) {
        
    }

   
   public static void main(String[] args) throws InterruptedException, ExecutionException {
      
        
        
       for (int i = 0; i <20; i++) {
           
        new DisplayData().returnJsonStringAirlineInfo(2);
       }

//        System.out.println("Duration: " + duration / 1_000_000 + " milliseconds");

    }
 }