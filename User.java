import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class User implements Observer {

    private StockMarket stockMarket;
    private String name;

    private int numOfTransactions;
    private double investment_budget;


    private Map<String,Integer> investment_portfolio;

    public User(StockMarket sm, String name,double ib){
        this.stockMarket = sm;
        this.name = name;
        this.investment_budget = ib;
        this.numOfTransactions = 0;

        this.stockMarket.addObserver(this);
        System.out.println(getName() + " is now trading on " + stockMarket.getName());

        initializePortfolio(); //add every stock inside the market and initialize the amounts to 0

        //this.investment_portfolio.put("Microsoft (MSFT)", 1731);
    }


    public void initializePortfolio(){

        this.investment_portfolio =  new HashMap<String, Integer>();


        LinkedList<Stock> current = this.stockMarket.getStocks();

        for(int i = 0; i < current.size() ; i++){
            investment_portfolio.put(current.get(i).getName(),0);

        }
    }

    public StockMarket getStockMarket(){
        return stockMarket;
    }

    public String getName(){
        return name;
    }

    public double getInvestment_budget(){
        return investment_budget;
    }

    public int getTransactionBoundary(){
        return 0;
    }

    public String getType(){
        return "User";
    }


    public void transactionError(){

        System.out.println(getName()+ "tried to make their " + (getTransactionBoundary() + 1) +
        "th transaction of the day, however as an " + getType() + " they are only allowed up to " + getTransactionBoundary());
    }


    //this method is used for the end of each day to reset the transaction count.
    public void reset(){
        this.numOfTransactions = 0;
    }
    public int getTransactionCount(){
        return numOfTransactions;
    }
    public boolean buyStock(Stock stock, int amount){

        double totalFee = this.stockMarket.buyStock(stock,amount,this);


        if(this.numOfTransactions == getTransactionBoundary()){
            transactionError();
            return false;

        }
        if(totalFee > 0 ){

            this.investment_budget = getInvestment_budget() - totalFee;
            this.investment_portfolio.put(stock.getName(),amount);
            this.numOfTransactions += 1;


            System.out.println(getName() + " has made a transaction to buy " + amount
                    + " shares of " + stock.getFullName() + " while the Stock Market is " + stockMarket.getStateName());


            //check for updates on states
            this.stockMarket.updateState();
            return true;


        }
        return false;
    }

    public double getBoundaryConst(){
        return 1;
    }

    public double getSellingBoundaryConst(){
        return getBoundaryConst(); //for balanced and conservative these are going to be same,
        // I will only override it for aggressive
    }


    public boolean sellStock(Stock stock, int amount){

        double totalFee = this.stockMarket.sellStock(stock,amount,this);

        if(this.numOfTransactions == getTransactionBoundary()){
            transactionError();
            return false;

        }

        else if(totalFee > 0){
            this.investment_budget = getInvestment_budget() - totalFee;
            this.numOfTransactions += 1;
            //check for updates on states
            this.stockMarket.updateState();
            return true;
        }
        return false;

    }


    public boolean satisfiesBuying(Stock s){
        return true;
    }


    public boolean satisfiesSelling(Stock s){
        return true;
    }
    public int buyingAmount(Stock s){


        if(!satisfiesBuying(s)){
            return 0;
        }
        double boundary_const = getBoundaryConst();

        double boundary = boundary_const * investment_budget;

        int amount = (int)(boundary / s.getPrice());

        int shares = (int)( s.getMarketCap() / s.getPrice());


        if(amount <= shares){
            return amount;
        }

        return shares;


    }

    public Map<String,Integer> getInvestment_portfolio(){
        return investment_portfolio;
    }


    public int sellingAmount(Stock s){

        int numOfShares = this.investment_portfolio.get(s.getName());;



        if(!satisfiesSelling(s)){
            return 0;
        }

        double boundary = getInvestment_budget() * (getSellingBoundaryConst());
        int amount = (int)(boundary / s.getPrice());



        if(amount <= numOfShares){
            return amount;
        }

        return numOfShares;

    }
    public int shouldBuyStock(Stock s){

        int amount = 0;

        if(s.getChange() < 0){
            amount = buyingAmount(s);
        }
        System.out.println(getName()+ " calculated that they should buy "
                + amount + " shares of " + s.getFullName());
        return amount;
    }

    public int shouldSellStock(Stock s){


        int amount = 0;

        int shareNumber = this.investment_portfolio.get(s.getName());

        if(shareNumber == 0){
            System.out.println(getName()+ " does not own any shares so cannot sell " + s.getFullName());
            return 0;
        }

        if(s.getChange() > 0){
            amount = sellingAmount(s);
        }

        System.out.println(getName()+ " calculated that they should sell "
                + amount + " shares of " + s.getFullName());

        return amount;

    }

    public void setInvestment_budget(double budget){
        this.investment_budget = budget;
    }

    @Override
    public void update(Stock stock) {
        System.out.println(getName() + " is alerted of change in " + stock.getFullName());

        int amount = shouldBuyStock(stock);

        if(amount >0){
            buyStock(stock,amount);
        }
        amount = shouldSellStock(stock);
        if(amount > 0 ){
            sellStock(stock,amount);
        }


    }
}
