package com.company.tradingSystem.interfaces;

import com.company.tradingSystem.trade.Trade;

import java.util.Date;
import java.util.List;

/**
 * Functions for all stocks
 *
 * Par value and last dividend in pennies.  Assuming not allowing fractions of pennies, so using long.
 * Calculations on them using double.
 */
public interface Stock
{
    /**
     * Get name of the stock
     * @return Returns the stock symbol.
     */
    String getName();

    /**
     * Get all trades.
     * @return List of all trades for this stock
     */
    List<Trade> getTrades();

    /**
     * Request trades sorted for time.  Will sort them by timestamp when this is called.
     * @param p_startTime
     * @param p_endTime
     * @return List of all trades for this stock
     */
    List<Trade> getSortedTrades();

    /**
     * Add a trade to the stock
     * @param p_trade Instance of Trade
     */
    void addTrade(Trade p_trade);

    /**
     * @return Returns the Par Value
     */
    long getParValue();

    /**
     * Calculate the Dividend yield.
     * @param p_price in pennies
     * @return calculated dividend
     */
    double getDividendYield(long p_price);

    /**
     * Get Dividend for the stock.
     * Depending on stock this could be simply the last dividend in pennies,
     * or the calculated value of fixed dividend % times par value, so not necessary round penny number so double return.
     * @return Dividend paid.
     */
    double getDividend();

    /**
     * Calculate P/E ratio
     * @param p_price in pennies
     * @return
     */
    double getPERatio(long p_price);

    /**
     * Calculate Volume Weighted Stock Price for 5 minutes prior to given time.
     * @param p_time
     * @return
     */
    double getVolumeWeightedStockPrice5Min(Date p_time);
}
