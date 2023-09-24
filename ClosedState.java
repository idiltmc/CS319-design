public class ClosedState implements StockMarketState{
    static final double TRANSACTION_FEE = -1;

    private StockMarket market;



    public ClosedState(StockMarket market) {
        this.market = market;
    }
    @Override
    public boolean open() {

        market.setState(new OpenState(market));
        return true;
    }

    @Override
    public String getStateName(){
        return "closed";
    }
    @Override
    public double getTransactionFee(){
        return TRANSACTION_FEE;
    }
    @Override
    public boolean close() {

        //if  we are coming here, stock market is already closed ,
        // and we are trying to close it again
        return false;
    }

    @Override
    public void updateState() {

    }


}
