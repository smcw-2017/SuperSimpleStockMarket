package com.company.tradingSystem;

import com.company.tradingSystem.interfaces.Stock;
import com.company.tradingSystem.trade.Trade;
import com.company.tradingSystem.trade.TradeType;

import java.util.*;

/**
 * This is to handle the flow of trades
 * Created by Stuart on 07/03/2017.
 */
public class TradeProcessor
{
    // Data in memory for stocks and trades.

    // Stocks.
    HashMap<String, Stock> m_stocks = new HashMap<String, Stock>();

    // API

    /**
     * Add a stock for the system to know of.
     * @param p_stock
     */
    public void addStock(final Stock p_stock)
    {
        m_stocks.put(p_stock.getName(), p_stock);
    }

    public Stock[] getStockList()
    {
        return m_stocks.values().toArray(new Stock[m_stocks.size()]);
    }

    /**
     * Fetch stock for given stock name
     * @param p_stockSymbol
     * @return
     * @throws SSSMException
     */
    public Stock getStock(final String p_stockSymbol) throws SSSMException
    {
        Stock stock = m_stocks.get(p_stockSymbol);
        if(stock == null)
        {
            throw new SSSMException("Tried to access stock that doesn't exist :" + p_stockSymbol);
        }
        return stock;
    }

    /**
     * Record a new buy trade.
     * @param p_stockSymbol
     * @param p_timeStamp
     * @param p_quantity
     * @param p_price
     */
    public void addBuyTrade(final String p_stockSymbol, final Date p_timeStamp,
                            final long p_quantity, final long p_price) throws SSSMException
    {
        Trade trade = new Trade(p_timeStamp, p_quantity, TradeType.BUY, p_price);
        addTradeImpl(p_stockSymbol, trade);
    }


    /**
     * Record a new sell trade.
     * @param p_stockSymbol
     * @param p_timeStamp
     * @param p_quantity
     * @param p_price
     */
    public void addSellTrade(final String p_stockSymbol, final Date p_timeStamp,
                            final long p_quantity, final long p_price) throws SSSMException
    {
        Trade trade = new Trade(p_timeStamp, p_quantity, TradeType.SELL, p_price);
        addTradeImpl(p_stockSymbol, trade);
    }


    private void addTradeImpl(final String p_stockSymbol, final Trade p_trade) throws SSSMException
    {
        // Fetch stock.
        Stock stock = getStock(p_stockSymbol);

        // record trade
        stock.addTrade(p_trade);
    }


    // Queries on stocks

    public double calculateDividendYield(final String p_stockSymbol, final long p_price) throws SSSMException
    {
        Stock stock = getStock(p_stockSymbol);
        return stock.getDividendYield(p_price);
    }

    public double calculatePERatio(final String p_stockSymbol, final long p_price) throws SSSMException
    {
        Stock stock = getStock(p_stockSymbol);
        return stock.getPERatio(p_price);
    }


    /**
     * Get trades for last 5 minutes from current time.
     * @param p_stockSymbol
     * @return
     */
    public double getVolumeWeightedStockPrice(final String p_stockSymbol) throws SSSMException
    {
        Stock stock = getStock(p_stockSymbol);
        return stock.getVolumeWeightedStockPrice5Min(new Date());
    }

    /**
     * Get the VolumeWeightedStockPrice for all stock, then get mean value
     * @param p_date
     * @return
     */
    public double getAllShareIndex()
    {
        Date date = new Date();
        Stock[] stocks = getStockList();
        int stockCount = stocks.length;
        double[] vwsPrices = new double[stockCount];

        for(int i = 0; i < stockCount; i++)
        {
            final Stock stock = stocks[i];
            double vwsPrice = stock.getVolumeWeightedStockPrice5Min(date);

            // Store values for use in all share index
            vwsPrices[i] = vwsPrice;
        }
        return Formulae.getGeometricMean(vwsPrices);
    }

}
