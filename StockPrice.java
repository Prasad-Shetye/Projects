package Portfoliomanager;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StockPrice {
public static String price(String ticker) {
String price = "not found";
try {
    URL url = new URL("https://www.google.com/finance/quote/" + ticker + ":NYSE");
    URLConnection urlConn = url.openConnection();
    InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
    BufferedReader buff = new BufferedReader(inStream);
    String line = buff.readLine();
   
    boolean x = true;
    while (line != null) {
        if(line.contains("[\"" + ticker + "\",")){
        int target = line.indexOf("[\"" + ticker + "\",");
        int deci = line.indexOf(".", target);
        int start = deci;
        while(line.charAt(start) != '\"') {
        start--;
        }
        price = line.substring(start +3, deci+3);
        }
        line = buff.readLine();
           }
   
}
catch(Exception e) {
e.printStackTrace();
}
return price;
}
  }