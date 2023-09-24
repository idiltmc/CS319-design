public class OpenState implements StockMarketState{

    static final double TRANSACTION_FEE = (1.5)/100;
    private StockMarket market;

    public OpenState(StockMarket market) {
        this.market = market;
    }
    @Override
    public boolean open() {
        //if  we are coming here, stock market is already open ,
        // and we are trying to open again
        return false;
    }


    @Override
    public double getTransactionFee(){
        return TRANSACTION_FEE;
    }

    @Override
    public String getStateName(){
        return "open";
    }


    @Override
    public void updateState(){
        if(this.market.getTransactions() > 10 && this.market.getTotalTransactionCost() < 500000){

            this.market.setState(new LowVolatileState(market));
        }

        else if( this.market.getTotalTransactionCost() > 1000000){
            this.market.setState(new HighVolatileState(market));
        }

    }
    @Override
    public boolean close() {

        this.market.resetDailyTransactions();
        this.market.setState(new ClosedState(market));
        return true;
    }


}
