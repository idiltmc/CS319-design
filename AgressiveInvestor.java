import java.util.HashMap;
import java.util.Map;

public class AgressiveInvestor extends User {

    static final int TRANSACTION_COUNT = 10; //allowed transaction count per day
    static final double BOUNDARY_CONST = 0.1;
    static final double SELLING_BOUNDARY_CONST = 0.12;


    public AgressiveInvestor(StockMarket sm, String name, double ib){
        super(sm,name, ib);
    }



    @Override
    public boolean satisfiesBuying(Stock s){

        int numOfShares = getInvestment_portfolio().get(s.getName());
        //if the change rate is less than -2, we are selling all of our shares, not buying
        //but if we don't have any shares, we can buy with the -2 change
        if(numOfShares > 0 && s.getChange() < -2){
            return false;
        }
        return true;
    }

    @Override
    public double getSellingBoundaryConst(){
        return SELLING_BOUNDARY_CONST;
    }


    //this method is overriden because of the special case with -2
    @Override
    public int shouldSellStock(Stock s){

        if(s.getChange() < -2){
            int numOfShares = getInvestment_portfolio().get(s.getName());
            System.out.println(getName()+ " calculated that they should sell "
                    + numOfShares + " shares of " + s.getFullName());
            sellStock(s,numOfShares);
            return numOfShares;
        }

        else{

            return super.sellingAmount(s);
        }


    }

    @Override
    public String getType(){
        return "Aggressive Investor";
    }

    @Override
    public int getTransactionBoundary(){
        return TRANSACTION_COUNT;
    }

    @Override
    public double getBoundaryConst(){
        return BOUNDARY_CONST;
    }


}
