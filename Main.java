import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {



        //EXAMPLE 1
        /*
        Stock stock = new Stock("Apple", "APPL", 100, -3.28,1001513.0 , 1024150.0);
        System.out.println(stock);
         */


        //EXAMPLE 2
        /*
        Stock apple = new Stock("Apple", "APPL", 100, 1.82,1023113.0 , 100350.0);
        Stock microsoft = new Stock("Microsoft", "MSFT",123.4,3.28,1001513.0, 1024150.0);
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        nyseMarket.addStock(apple);
        nyseMarket.addStock(microsoft);
        System.out.println(nyseMarket);
         */

        //EXAMPLE 3
        /*
        StockMarket nasdaqMarket = new StockMarket("NASDAQ","NASDAQ");
        System.out.println(nasdaqMarket);
         */


        //EXAMPLE 4 and 5

        /*
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        Stock microsoft = new Stock("Microsoft", "MSFT",123.4,-3.28,1001513.0, 1024150.0);
        nyseMarket.addStock(microsoft);

        User beff = new AgressiveInvestor(nyseMarket,"Beff Jezos",990023.21);
        beff.shouldBuyStock(microsoft); //FOR EXAMPLE 4


        microsoft.update(123.4,3.28,1001513.0, 1024150.0);
        nyseMarket.open();
        beff.buyStock(microsoft,1731);


        beff.setInvestment_budget(990023.21);
        beff.shouldSellStock(microsoft); // FOR EXAMPLE 5
         */


        //EXAMPLE 6
        /*
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        Stock microsoft = new Stock("Microsoft", "MSFT",123.4,3.28,1001513.0, 1024150.0);
        nyseMarket.addStock(microsoft);

        User beff = new AgressiveInvestor(nyseMarket,"Beff Jezos",990023.21);
        double firstBudget =  beff.getInvestment_budget();
        beff.getInvestment_portfolio().put(microsoft.getName(),962);

        nyseMarket.open();
        microsoft.update( 123.4,-3.28,1001513.0, 1024150.0);


        System.out.println("Original cost: " + firstBudget + " TRY");
        System.out.println("Fee: %" + nyseMarket.getFee() * 100 + " TRY");
        System.out.println("Total Cost: " + (firstBudget - beff.getInvestment_budget()) + " TRY");
        System.out.println(beff.getName() + " has " + beff.getInvestment_budget() + " TRY left");

         */


        //EXAMPLE 7

        /*
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        Stock microsoft = new Stock("Microsoft", "MSFT",123.4,3.28,1001513.0, 1024150.0);
        nyseMarket.addStock(microsoft);

        User beff = new AgressiveInvestor(nyseMarket,"Beff Jezos",990023.21);
        beff.getInvestment_portfolio().put(microsoft.getName(),962);
        microsoft.update( 123.4,-3.28,1001513.0, 1024150.0);

         */

        //EXAMPLE 8

        /*
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        Stock apple = new Stock("Apple", "APPL", 100, 1.82,1023113.0 , 100350.0);
        nyseMarket.addStock(apple);

        User barren = new AgressiveInvestor(nyseMarket,"Barren Wuffet",990023.21);

        nyseMarket.open();
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        barren.sellStock(apple,1);
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        barren.sellStock(apple,1);
        barren.buyStock(apple,1);
        barren.buyStock(apple,1);
        */


        //EXAMPLE 9 and 10

        /*
        StockMarket nyseMarket = new StockMarket("New York Stock Exchange","NYSE");
        Stock microsoft = new Stock("Microsoft", "MSFT",123.4,3.28,1001513.0, 1024150.0);
        nyseMarket.addStock(microsoft);

        User eray = new BalancedInvestor(nyseMarket,"Eray Tüzün",1000000);
        nyseMarket.open();

        microsoft.update(89.12, -3.12, 3774231.0, 32834141.0);

         */




        //FURTHER TESTS




        StockMarket stockMarket = new StockMarket("New York Stock Exchange", "NYSE");
        Stock apple = new Stock("Apple", "AAPL", 100.00, 3.2, 1000000, 10000);
        Stock microsoft = new Stock("Microsoft", "MSFT", 123.4, -3.28, 9742423.0, 24824213.0);
        stockMarket.addStock(apple);
        stockMarket.addStock(microsoft);
        User aggressive = new AgressiveInvestor(stockMarket, "Beff Jezos",
                91023.21);



        User balanced = new BalancedInvestor(stockMarket, "Steve Jobs",10000000);

        User conservative = new ConservativeInvestor(stockMarket, "Idil Atmaca",1000);




        stockMarket.open();
        aggressive.buyStock(microsoft, 1731);
        aggressive.sellStock(microsoft, 40);

        //num of transaction is 2, total is less than 1000000 : OPEN STATE
        System.out.println("Current state: " + stockMarket.getStateName());
        System.out.println("Total cost: " + stockMarket.getTotalTransactionCost());


        aggressive.buyStock(microsoft, 4);
        //aggressive.buyStock(apple, 100);
        balanced.buyStock(apple, 200);
        balanced.buyStock(apple, 3);
        balanced.buyStock(microsoft, 3);
        conservative.buyStock(apple,2);
        conservative.buyStock(microsoft,1);
        conservative.sellStock(microsoft,1);
        conservative.buyStock(microsoft,1);
        conservative.buyStock(apple,1);


        //total of 11 transaction and less than 5000000 : LOW-VOLATILE STATE

        System.out.println("Current state: " + stockMarket.getStateName());
        System.out.println("Total cost: " + stockMarket.getTotalTransactionCost());

        microsoft.update(123.3, 5, 1001513.0, 1024150.0);

        //num of transaction more than 10, total is less than 1000000 and more than 500000 : OPEN STATE
        System.out.println("Current state: " + stockMarket.getStateName());
        System.out.println("Total cost: " + stockMarket.getTotalTransactionCost());

        apple.update(100, -0.2, 274242, 252343224);
        System.out.println("Current state: " + stockMarket.getStateName());
        System.out.println("Total cost: " + stockMarket.getTotalTransactionCost());


        User test = new AgressiveInvestor(stockMarket, "Aggressive Person",10000000);
        test.buyStock(microsoft,500);


        microsoft.update(97.7, -3.2, 274242, 252343224);

        //after the update, we are expecting the Aggressive person to sell all their shares and don't buy


        //more than 1000000: HIGH-VOLATILE
        System.out.println("Current state: " + stockMarket.getStateName());
        System.out.println("Total cost: " + stockMarket.getTotalTransactionCost());


        System.out.println("count:" +conservative.getTransactionCount()); //check if the count resets when the day has ended
        stockMarket.close();
        System.out.println("count:" +conservative.getTransactionCount());
        System.out.println("Current state: " + stockMarket.getStateName());


        StockMarket wallStreet = new StockMarket("Wall Street", "WOFS");
        User belfrot = new BalancedInvestor(wallStreet, "Jordan Belfrot",100000000);

        //checking for observer pattern
        Stock amazon = new Stock("Amazon", "AMZ", 123.4, 3.28, 9752423.0, 24824213.0);


        wallStreet.open();
        wallStreet.addStock(amazon);

        amazon.update(123.4, -3.28, 9752423.0, 24824213.0);

        wallStreet.close();
        belfrot.sellStock(amazon,12);








    }
}