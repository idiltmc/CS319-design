import java.util.Map;

public class ConservativeInvestor extends User {


    static final int TRANSACTION_COUNT = 5;
    static final double BOUNDARY_CONST = 0.05;

    public ConservativeInvestor(StockMarket sm, String name, double ib){
        super(sm,name, ib);
    }


    @Override
    public String getType(){
        return "Conservative Investor";
    }

    @Override
    public int getTransactionBoundary(){
        return TRANSACTION_COUNT;
    }


    @Override
    public double getBoundaryConst(){
        return BOUNDARY_CONST;
    }


    @Override
    public boolean satisfiesBuying(Stock s){
        if(getInvestment_budget() <= s.getPrice() * 25){
            return false;
        }
        return true;
    }


    @Override
    public boolean satisfiesSelling(Stock s){
        if(getInvestment_budget() <= s.getPrice() * 75){
            return false;
        }
        return true;
    }

}
