public class LowVolatileState implements StockMarketState{

    static final double TRANSACTION_FEE = (0.5)/100;

    private StockMarket market;

    public LowVolatileState(StockMarket market) {
        this.market = market;
    }
    @Override
    public boolean open() {
        return false;
    }

    @Override
    public String getStateName(){
        return "low-volatile";
    }
    @Override
    public boolean close() {
        this.market.resetDailyTransactions();
        this.market.setState(new ClosedState(market));
        return true;
    }


    @Override
    public void updateState(){


        if( this.market.getTotalTransactionCost() > 1000000){
            //System.out.println("CHANGING");
            this.market.setState(new HighVolatileState(market));
        }

        else if((this.market.getTransactions() > 10 && this.market.getTotalTransactionCost() >= 500000)){
            this.market.setState(new OpenState(market));

        }

    }

    @Override
    public double getTransactionFee(){
        return TRANSACTION_FEE;
    }



}
