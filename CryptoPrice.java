package Portfoliomanager;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CryptoPrice {
private static String g = "";
private static String price = "";
private static String zero = "";
public static String getCPrice(String ticker) {
ticker += "-USD";
price = "not found";
try {
    URL url = new URL("https://www.google.com/finance/quote/" + ticker);
    URLConnection urlConn = url.openConnection();
    InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
    BufferedReader buff = new BufferedReader(inStream);
    String line = buff.readLine();
   
    boolean x = true;
    while (line != null) {
    g = "data:[[[[\"/g/11";
    if(line.contains(g)){
          int target = line.indexOf(g);
          int deci = line.indexOf(".", target);
          int start = deci;
          while(line.charAt(start) != '[') {
          start--;
          }
          price = line.substring(start +1, deci+3);
         }
        line = buff.readLine();
           }    
}
catch(Exception e) {
e.printStackTrace();
}
if(!(Character.isDigit(price.charAt(price.length()-1)))) {
zero = price.substring(price.length()-2) + "0";
price = zero;
}
return price;
}
}