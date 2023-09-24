public class HighVolatileState implements StockMarketState{

    private StockMarket market;
    static final double TRANSACTION_FEE = 0.03;


    public HighVolatileState(StockMarket market) {
        this.market = market;
    }
    @Override
    public boolean open() {
        return false;
    }

    @Override
    public String getStateName(){
        return "high-volatile";
    }
    @Override
    public boolean close() {
        this.market.resetDailyTransactions();
        this.market.setState(new ClosedState(market));
        return true;
    }

    @Override
    public void updateState() {

    }


    @Override
    public double getTransactionFee(){
        return TRANSACTION_FEE;
    }


}
