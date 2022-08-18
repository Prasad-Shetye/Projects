package Portfoliomanager;

import java.util.ArrayList;

import javax.swing.JOptionPane;
public class Stocks{
  private String ticker;
  private double buyPrice;
  private double numStocks;
  private int numLots;
  private double totalStocks;
  private double totalPaid;
  private static ArrayList <Stocks> portfolio = new ArrayList<Stocks>();
  public static double cash;
  public boolean crypto;
  public Stocks(String tick, double buyPrice, double numberOfStocks, boolean crypto){
    this.crypto = crypto;
    boolean existing = false;
    int j = 0;
    for(int i = 0; i <portfolio.size(); i++){
      if(portfolio.get(i).getTicker().equals(tick)){
        existing = true;
        j=i;
        break;
      }
    }
    if(existing){
      portfolio.get(j).addStocks(numberOfStocks);
      portfolio.get(j).setTotalPaid(numberOfStocks, buyPrice);
      portfolio.get(j).addNumLots();
    }
    else{
      ticker = tick;
      this.buyPrice = buyPrice;
      numStocks = numberOfStocks;
     
      numLots++;
      totalStocks += numberOfStocks;
      totalPaid += buyPrice * numberOfStocks;
      portfolio.add(this);
     
    }
  }
  public static double format(double in) {
      in *= 100;
      int y = (int)(Math.round(in));
      double z = y/100.0;
      return z;
  }
  public static String addZeros(double x) {
 String y = "" + x;
      while((y.length() - y.indexOf('.')) != 3){
          y = y + "0";
      }
      return y;
  }
  public static int numStocks() {
 return portfolio.size();
  }
  public static void addCash(double add) {
 cash += add;
  }
  public static void removeCash(double remove) {
 cash -= remove;
  }
  public static String getCash() {
 return addZeros(cash);
  }
  public void addNumLots(){
    numLots++;
  }
  public void sellStocks(int sell) {
 numStocks -= sell;
 
  }
 
  public String getTicker(){
    return ticker;
  }
  public void setTotalPaid(double numberOfStocks, double price){
    totalPaid += numberOfStocks*price;
  }
  public double getAvgPrice(){
    return totalPaid/numStocks;
  }
 
  public double getTotalPaid() {
 return format(totalPaid);
  }
 
  public double getBuyPrice(){
    return format(buyPrice);
  }
  public double getNumStocks(){
    return totalStocks;
  }
  public void addStocks(double newStocks){
    numStocks += newStocks;
  }
  public int getNumLots(){
    return numLots;
  }
 
  public double getTotalStocks(){
    return totalStocks;
  }
 
  public static double getPrice(String ticker) {
 String price = StockPrice.price(ticker);
 return Double.parseDouble(price);
  }

 
  public static double getCryptoPrice(String cost) {
 String coster = CryptoPrice.getCPrice(cost);
 return Double.parseDouble(coster);
  }
 
  public double getNumGL() {
 double currentPrice;
 if(crypto) {
 currentPrice = getCryptoPrice(ticker);
 }
 else {
 currentPrice = getPrice(ticker);
 }
 return ((currentPrice - getAvgPrice()) * getNumStocks());
  }
  public double getValue() {
 double currentPrice;
 if(crypto) {
 currentPrice = getCryptoPrice(ticker);
 }
 else {
 currentPrice = getPrice(ticker);
 }  return (totalStocks * currentPrice);
  }
  public double getPercGL() {
 double currentPrice;
 if(crypto) {
 currentPrice = getCryptoPrice(ticker);
 }
 else {
 currentPrice = getPrice(ticker);
 }  return format((((currentPrice - getAvgPrice())*numStocks)/totalPaid)*100);
  }
 
  public static Stocks topLoserPerc() {
 
 
 double min = 100;
 int index = 0;
 for(int i = 0; i < portfolio.size(); i++) {
 if(portfolio.get(i).getPercGL() < min) {
 min = portfolio.get(i).getPercGL();
 index = i;
 }
 }
 return portfolio.get(index);
 
  }
 
  public static Stocks topWinnerPerc() {
 
 
 double max = -100;
 int index = 0;
  for(int i = 0; i < portfolio.size(); i++) {
 if(portfolio.get(i).getPercGL() > max) {
 max = portfolio.get(i).getPercGL();
 index = i;
 }
  }
  return portfolio.get(index);
 
}
 
  public static Stocks topLoser() {
 
 
 double min = 999999999;
 int index = 0;
  for(int i = 0; i < portfolio.size(); i++) {
 if(portfolio.get(i).getPercGL() < min) {
 min = portfolio.get(i).getNumGL();
 index = i;
 }
  }
  return portfolio.get(index);
}
 
public static Stocks topWinner() {
 
 
 double max = -999999999;
 int index = 0;
  for(int i = 0; i < portfolio.size(); i++) {
 if(portfolio.get(i).getNumGL() > max) {
 max = portfolio.get(i).getNumGL();
 index = i;
 }
  }
  return portfolio.get(index);
}

public static double totalGLNum() {
double total = 0;
for(Stocks x : portfolio) {
total += x.getNumGL();
}
return format(total);
}

public static double getTotalInvested() {
double totalInvested = 0;

for(Stocks x : portfolio) {
totalInvested += x.getTotalPaid();
}
return format(totalInvested);
}

public static double totalGLPerc() {

return format(totalGLNum()/getTotalInvested() * 100);
}
public double getTotalValue() {
double currentPrice;
 if(crypto) {
 currentPrice = getCryptoPrice(ticker);
 }
 else {
 currentPrice = getPrice(ticker);
 }
return format(currentPrice * numStocks);
}
public static double getPortfolioValue() {
double stockValue = 0;
for(Stocks x : portfolio) {
stockValue += x.getValue();
}
return format(stockValue + cash);
}
public static Stocks getBiggestHolding() {
double holding = 0;
int index = 0;
for(int i = 0; i < portfolio.size(); i++) {
if(portfolio.get(i).getTotalValue() > holding) {
holding = portfolio.get(i).getTotalValue();
index = i;
}
}
return portfolio.get(index);
}
public static Stocks getSmallestHolding() {
double holding = 0;
int index = 0;
for(int i = 0; i < portfolio.size(); i++) {
if(portfolio.get(i).getTotalValue() < holding) {
holding = portfolio.get(i).getTotalValue();
index = i;
}
}
return portfolio.get(index);
}
public static Stocks getStock(String ticker) {
int index = 0;
for(int i = 0; i < portfolio.size(); i++) {
if(portfolio.get(i).ticker.equals(ticker)) {
index = i;
break;
}
}
return portfolio.get(index);
}

public static String listAllTickers() {
String list = "";
for(Stocks stock : portfolio) {
list += stock.getTicker() + "\n";
}
return list;
}

  public static void printPortfolioStocks() {
 String port = "";
 port += "Stocks currently in portfolio:\n";
 for(Stocks x: portfolio) {
 port += x + "\n";
 }
 port += "------------------------\n";
 port += "Cash Balance: $" + cash;
 port += "------------------------\n";
 JOptionPane.showMessageDialog(null, port);
  }
  public static void printSignificantHoldings() {
 String port = "";
 port += "\nTop Winner (Percentage Based):\n";
 port += topWinnerPerc() + "\n";
 
 port += "Top Winner (Dollar Based):\n";
 port += topWinner() + "\n";
 port += "------------------------\n";

 port += "Top Loser (Percentage Based): \n";
 port += topLoserPerc()+ "\n";
 
 port += "Top Loser (Dollar Based): \n";
 port += (topLoser() + "\n");
 port += "------------------------\n";

 port += "Biggest Holding: " + getBiggestHolding() + "\n";
 port += ("Smallest Holding: " + getSmallestHolding() + "\n");
 port += "------------------------\n";
 JOptionPane.showMessageDialog(null, port);
  }
  public String toString(){
 String output = ticker + "\n";
 output += "Number of stocks/coins: " + numStocks +"\n";
 output += "Average Price Paid: $" + addZeros(getAvgPrice()) + "\n";
 if(crypto) {
 output += "Current Price: $" + addZeros(Stocks.getCryptoPrice(ticker))+ "\n";
 }
 else {
 output += "Current Price: $" + addZeros(Stocks.getPrice(ticker))+ "\n";
 }
 output += "Total Gain/Loss: $" + addZeros(getNumGL())+ "\n";
 output += "Percentage Gain/Loss: " + addZeros(getPercGL())+ "%\n";
 return output;
  }
}
