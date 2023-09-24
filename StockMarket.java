import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StockMarket {

    private String name,symbol;
    private LinkedList<Stock> stocks;

    private final List<Observer> observers;

    private int transactions;
    private double totalTransactionCost;

    private StockMarketState state;

    public StockMarket(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
        this.stocks = new LinkedList<Stock>();;
        initializeStocks();
        this.state = new ClosedState(this);
        this.observers = new ArrayList<>();
        //starting a new day
        this.transactions = 0;
        this.totalTransactionCost = 0;

        System.out.println("Stock Market with name " +  getName() + " created.");

    }

    public void addObserver(User newInvestor){
        this.observers.add(newInvestor);
    }

    public String getName(){

        return  name + " (" + symbol + ")";

    }


    public void addStock(Stock s){

        s.setMarket(this);
        this.stocks.add(s);
        s.addMessage();


    }


    public LinkedList<Stock> getStocks(){
        return this.stocks;
    }


    public void resetDailyTransactions(){
        for(int i = 0; i< this.observers.size() ; i++){
            this.observers.get(i).reset();
        }
    }
    public boolean open(){
        return state.open();

    }
    public boolean close(){


        return state.close();
    }
    public void initializeStocks(){

        for(int i = 0; i< this.stocks.size() ; i++){
            this.stocks.get(i).setMarket(this);
        }

    }
    public void updateStock(Stock s){

        for(int i = 0; i< this.observers.size() ; i++){
            this.observers.get(i).update(s);
        }

    }

    public double getFee(){
        return this.state.getTransactionFee();
    }
    public void incrementTotalTransactionCost(double newTransactionAmount){
        this.totalTransactionCost += newTransactionAmount;
    }

    public void incrementTransactionCount(){
        this.transactions += 1;
    }


    public String getStateName(){
        return this.state.getStateName();
    }
    public double buyStock(Stock s, int amount, User user) {


        double transaction = s.getPrice() * amount;


        //check if it is closed
        if(this.state.getTransactionFee() < 0){

            System.out.println(user.getName() + " has made a transaction to buy "
                    + amount + " shares of " +s.getName() + " while the Stock Market is closed.\n" +
                    "Unable to process the transaction.");
            return -1;
        }




        //calculate the total fee, and see if it is applicable
        double fee = transaction * this.state.getTransactionFee();
        double total = transaction + fee;
        if(user.getInvestment_budget() >= total){
            incrementTotalTransactionCost(total);
            incrementTransactionCount();
            return total;
        }

        return -1;
    }

    public void updateState(){
        this.state.updateState();
    }

    public double sellStock(Stock s, int amount, User user) {



        double transaction = s.getPrice() * amount;
        double fee = transaction * this.state.getTransactionFee();


        if(this.state.getTransactionFee() < 0){
            System.out.println(user.getName() + " has made a transaction to sell "
                    + amount + " shares of " +s.getName() + " while the Stock Market is closed.\n" +
                    "Unable to process the transaction.");
            return -1;
        }



        if(user.getInvestment_budget() >= fee){
            incrementTotalTransactionCost(fee);
            incrementTransactionCount();
            return fee;
        }

        return -1;
    }


    public void setState(StockMarketState state){
        this.state = state;
    }

    public int getTransactions(){
        return transactions;
    }

    public double getTotalTransactionCost(){
        return totalTransactionCost;
    }


    public String getAllStocks(){
        String stockInfo ="";

        LinkedList<Stock> list = this.stocks;

        if(list.size()  == 0){
            return "No stocks available";
        }

        for(int i = 0; i < this.stocks.size(); i++){
            stockInfo +=  "\n" + list.get(i).toString() + "\n";
        }

        return stockInfo;
    }

    public String toString(){
        String str = "--- Stock Market Information --- \n";
        str += "Name:" + this.name + " (" + this.symbol + ")\n"
                + "Stocks:\n " + getAllStocks();
        return str;
    }
}
