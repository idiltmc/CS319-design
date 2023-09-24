import java.util.Map;

public class BalancedInvestor extends User {

    static final int TRANSACTION_COUNT = 7;
    static final double BOUNDARY_CONST = 0.08;




    public BalancedInvestor(StockMarket sm, String name, double ib){
        super(sm,name, ib);
    }


    @Override
    public String getType(){
        return "Balanced Investor";
    }

    @Override
    public double getBoundaryConst(){
        return BOUNDARY_CONST;
    }

    @Override
    public int getTransactionBoundary(){
        return TRANSACTION_COUNT;
    }

    @Override
    public boolean satisfiesBuying(Stock s){
        if(getInvestment_budget() <= s.getPrice() * 20){
            return false;
        }
        return true;
    }


    @Override
    public boolean satisfiesSelling(Stock s){
        if(getInvestment_budget() >= s.getPrice() * 1000){
            return false;
        }
        return true;
    }


}
