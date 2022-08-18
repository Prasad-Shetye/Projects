package Portfoliomanager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class gui implements ActionListener{
int count = 0;
private JLabel label;
private JFrame frame;
public JFrame frame2;
private JPanel panel;
private JButton addStock;
private JButton addCrypto;
private JButton stockPrice;
private JButton cryptoPrice;
private JButton addCash;
private JButton removeCash;
private JButton portfolio;
private JButton winner;
private JButton loser;
private JButton biggest;
private JButton smallest;
private JButton myStocks;
private JButton specifics;
private boolean end = true;

public gui() {
frame = new JFrame();

addStock = new JButton("Add Stock");
addCrypto = new JButton("Add Cryptocurrency");
cryptoPrice = new JButton("Get Cryptocurrency Price");
stockPrice = new JButton("Get Stock Price");
addCash = new JButton("Add Cash to Portfolio");
removeCash = new JButton("Remove Cash from Portfolio");



addStock.addActionListener(this);
addCrypto.addActionListener(this);
cryptoPrice.addActionListener(this);
stockPrice.addActionListener(this);
addCash.addActionListener(this);
removeCash.addActionListener(this);

label = new JLabel("Welcome to your stock porfolio! Please click on one of the above options to proceed!");
panel = new JPanel();
panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 30));
panel.setLayout(new GridLayout(0, 1));
panel.add(addStock);
panel.add(addCrypto);
panel.add(stockPrice);
panel.add(cryptoPrice);
panel.add(addCash);
panel.add(removeCash);

panel.add(label);

frame.add(panel, BorderLayout.CENTER);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setTitle("Stock Investment Portfolio: Welcome Page");
frame.pack();
frame.setVisible(true);

}
public gui(String s) {
frame2 = new JFrame();

addStock = new JButton("Add Stock");
addCrypto = new JButton("Add Cryptocurrency");
stockPrice = new JButton("Get Stock Price");
cryptoPrice = new JButton("Get Crypto Price");
addCash = new JButton("Add Cash to Portfolio");
removeCash = new JButton("Remove Cash from Portfolio");
portfolio = new JButton("View Whole Portfolio");
winner = new JButton("See Biggest Winner(s)");
loser = new JButton("See Biggest Loser(s)");
biggest= new JButton("See Biggest Holding");
smallest= new JButton("See Smallest Holding");
myStocks= new JButton("See Specific Data about a Stock/Cryptocurrency in Your Portfolio");
specifics= new JButton("See Portfolio Performance");

addStock.addActionListener(this);
addCrypto.addActionListener(this);
stockPrice.addActionListener(this);
cryptoPrice.addActionListener(this);
addCash.addActionListener(this);
removeCash.addActionListener(this);
portfolio.addActionListener(this);
winner.addActionListener(this);
loser.addActionListener(this);
biggest.addActionListener(this);
smallest.addActionListener(this);
myStocks.addActionListener(this);
specifics.addActionListener(this);

label = new JLabel("Plese pick from one of the options above, or click X in the top right corner to exit!");
panel = new JPanel();
panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 30));
panel.setLayout(new GridLayout(0, 1));

panel.add(addStock);
panel.add(addCrypto);
panel.add(stockPrice);
panel.add(cryptoPrice);
panel.add(addCash);
panel.add(portfolio);
panel.add(winner);
panel.add(loser);
panel.add(biggest);
panel.add(smallest);
panel.add(myStocks);
panel.add(specifics);
panel.add(removeCash);


panel.add(label);

frame2.add(panel, BorderLayout.CENTER);
frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame2.setTitle("Stock Investment Portfolio");
frame2.pack();
frame2.setVisible(true);

}
public static void main (String args[]) {
new gui();

}
public void actionPerformed(ActionEvent e) {
Object obj = e.getSource();
if(obj == addStock) {
String ticker = JOptionPane.showInputDialog("Please enter the ticker symbol (In all caps): ");
String buyPriceS = JOptionPane.showInputDialog("Please enter the buy price: ");
double pricePaid = Double.parseDouble(buyPriceS);
String numstockS = JOptionPane.showInputDialog("Please enter the number of stocks you bought: ");
int num = Integer.parseInt(numstockS);
new Stocks(ticker, pricePaid, num, false);
JOptionPane.showMessageDialog(null, "Stock added to portfolio!");

if(end) {
frame.setVisible(false);
new gui("");
end = false;
}

}
if(obj == addCrypto) {
String ticker = JOptionPane.showInputDialog("Please enter the cryptocurrency symbol (In all caps): ");
String buyPriceS = JOptionPane.showInputDialog("Please enter the buy price: ");
double pricePaid = Double.parseDouble(buyPriceS);
String numstockS = JOptionPane.showInputDialog("Please enter the number of coins you bought: ");
int num = Integer.parseInt(numstockS);
new Stocks(ticker, pricePaid, num, true);
JOptionPane.showMessageDialog(null, "Cryptocuurency added to portfolio!");

if(end) {
frame.setVisible(false);
new gui("");
end = false;
}
}
else if(obj == stockPrice) {
String ticker = JOptionPane.showInputDialog("Please enter the ticker symbol (In all caps): ");
JOptionPane.showMessageDialog(null, "Price of " + ticker + ": $" + StockPrice.price(ticker));
}
else if(obj == cryptoPrice) {
String ticker = JOptionPane.showInputDialog("Please enter the cryptocurrency symbol (In all caps): ");
JOptionPane.showMessageDialog(null, "Price of " + ticker + ": $" + CryptoPrice.getCPrice(ticker));
}
else if(obj == addCash) {
String cashS = JOptionPane.showInputDialog("How much cash would you like to add: ");
double cash = Double.parseDouble(cashS);
Stocks.addCash(cash);
JOptionPane.showMessageDialog(null, "Cash added!\nNew Cash balance : $" + Stocks.getCash());
if(Stocks.cash>0) {
panel.add(removeCash);
}
}
else if(obj == removeCash) {
if(Stocks.cash > 0) {
String removeS = JOptionPane.showInputDialog("How much cash would you like to remove:\nBalance: $" + Stocks.addZeros(Stocks.cash));
double remove = Double.parseDouble(removeS);
if(remove > Stocks.cash) {
JOptionPane.showMessageDialog(null, "Amount entered exceeds cash balance");

}
else {
Stocks.removeCash(remove);
JOptionPane.showMessageDialog(null, "Cash removed succesfully!\nNew Balance: $" + Stocks.getCash());

}
}
else {
JOptionPane.showMessageDialog(null, "Portfolio currently has no cash balance");

}
}
else if(obj == portfolio) {
Stocks.printPortfolioStocks();
}
else if(obj == winner) {
String win = "";

win += "Top Winner (Percentage Based):\n";
win += (Stocks.topWinnerPerc() + "\n");
win += "Top Winner (Dollar Based):\n";
win  += Stocks.topWinner() + "\n";
   JOptionPane.showMessageDialog(null, win);
}
else if(obj == loser) {
String lose = "";

lose += ("Top Loser (Percentage Based):\n");
lose += (Stocks.topLoserPerc() + "\n");
lose += "Top Loser (Dollar Based):\n";
lose += Stocks.topLoser() + "\n";
  JOptionPane.showMessageDialog(null, lose);

}
else if(obj == biggest) {
String big = "";
big += Stocks.getBiggestHolding() + "\n";
JOptionPane.showMessageDialog(null, big);
}
else if(obj == smallest) {
String small = "";
small += Stocks.getSmallestHolding() + "\n";
JOptionPane.showMessageDialog(null, small);
}
else if(obj == myStocks) {
String dialogue = "Which stock/cryptocurrency would you like to see? (Enter ticker/symbol)\n\n Stocks/Cryptocurrency currently in portfolio:\n";
dialogue += Stocks.listAllTickers();
String use = JOptionPane.showInputDialog(dialogue);

JOptionPane.showMessageDialog(null, Stocks.getStock(use));
}
else if(obj == specifics) {
String ex = "Total Gain/Loss (in Dollars): $" + Stocks.addZeros(Stocks.totalGLNum()) + "\n";
ex += ("\nTotal Gain/Loss (Percentage): " + Stocks.addZeros(Stocks.totalGLPerc()) + "%");
ex += ("\nTotal money invested in assets: $" + Stocks.addZeros(Stocks.getTotalInvested()));
ex += ("\nTotal cash in portfolio: $" + Stocks.getCash());
ex += ("\nTotal portfolio value: $" + Stocks.addZeros(Stocks.getPortfolioValue()));
//System.out.println();
//System.out.println("Please click enter to proceed!");
   JOptionPane.showMessageDialog(null, ex);
}
}

}