public interface StockMarketState {

    public boolean open();
    public boolean close();

    public String getStateName();

    public void updateState();

    public double getTransactionFee();
}
