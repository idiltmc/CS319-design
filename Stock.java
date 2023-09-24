public class Stock {
    private String name, symbol;

    private StockMarket stockMarket;

    private double price, percentChange,volume, marketCap;


    public Stock(String name, String symbol, double price, double percentChange, double volume, double marketCap){
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percentChange = percentChange;
        this.volume = volume;
        this.marketCap = marketCap;
    }



    public void setMarket(StockMarket sm){
        this.stockMarket = sm;

    }
    public void update(double price, double percentChange, double volume, double marketCap){

        this.price = price;
        this.percentChange = percentChange;
        this.volume = volume;
        this.marketCap = marketCap;

        System.out.println(getFullName() + " is updated to price: " +
                price + " TRY, percent change: " + percentChange + ",volume: "
                + volume + " and market cap: " + marketCap
        );


        this.stockMarket.updateStock(this);




    }


    public String getFullName(){
        return this.name + " (" + this.symbol + ")";
    }

    public String getName(){
        return this.name;
    }
    public double getChange(){
        return percentChange;
    }

    public double getPrice(){
        return  price;
    }

    public double getMarketCap(){
        return marketCap;
    }


    public void addMessage(){

        System.out.println(getFullName() + "stock with price  "
                + getPrice() + " TRY, "  + getChange() + " percent change,"
                +  this.volume + " volume and " + this.marketCap + " market cap is added to " + this.stockMarket.getName());
    }
    public String toString(){
        String str = "--- Stock Information ---\n";
        str +=  "Name: " +  getFullName() + "\n"
                + "Price: " + this.price+ " TRY\n"
                + "Percent Change: " + this.percentChange + "\n"
                + "Volume: " + this.volume + "\n" +
                "Market Cap: " + this.marketCap;

        return str;
    }
}
