package com.company.tradingSystem;

import com.company.tradingSystem.trade.Trade;

import java.util.Date;
import java.util.List;

/**
 * Collection of formulae.
 *
 * Prices in pennies.
 */
public class Formulae
{
    /**
     * Calculate Common Dividend Yield <br>
     * Dividend / price.
     * @param p_dividend
     * @param p_price
     * @return
     */
    public static double getCommonDividendYield(final long p_dividend, final long p_price)
    {
        return (double)p_dividend / p_price;
    }

    /**
     * Calculate Preferred Dividend Yield <br>
     * Fixed dividend * par value / price.
     * @param p_fixedDividend
     * @param p_parValue
     * @param p_price
     * @return
     */
    public static double getPreferredDividendYield(final double p_fixedDividend, final long p_parValue, final long p_price)
    {
        return (p_fixedDividend / 100.0) * p_parValue / p_price;
    }

    /**
     * Calculate P/E ratio <br>
     * price / dividend
     * @param p_price
     * @param p_dividend
     * @return
     */
    public static double getPERatio(final long p_price, final double p_dividend)
    {
        return p_price/p_dividend;
    }

    /**
     * Calculate Geometric Mean
     * @param p_prices
     * @return
     */
    public static double getGeometricMean(final double[] p_prices)
    {
        int i;
        int nLen = p_prices.length;

        double dTot = 1.0;
        for(i=0;i<nLen;i++)
        {
            dTot *= p_prices[i];
        }

        dTot = Math.pow(dTot, (1.0/nLen));

        return dTot;
    }


    /**
     * Calculate Volume Weighted Stock Price for given trades.
     * Request is to be based on all trades in the past 5 minutes.
     * So includes all trades with timestamp after 5 minutes before the time this request is made.
     *
     * This assumes that no further trades have been added to the list with time after the request to calculate
     * this is made, i.e. not allowing multi-threaded access.
     * @param p_trades List of trades, must be sorted in time order before calling this function.
     * @param p_startDate
     * @return
     */
    public static double getVolumeWeightedStockPrice(final List<Trade> p_trades,
                                                     final Date p_startDate)
    {
        if(p_trades.size() == 0)
        {
            return 0.0;
        }

        double sumProduct = 0.0;
        double sumQuantity = 0.0;

        long lStart = p_startDate.getTime();
        int len = p_trades.size();
        int i;

        // count back for all trades in allowed time.
        for(i=len-1; i>=0; i--)
        {
            Trade trade = p_trades.get(i);
            if(trade.getTimeStamp().getTime() >= lStart)
            {
                sumProduct += trade.getPrice() * trade.getQuantity();
                sumQuantity += trade.getQuantity();
            }
            else
            {
                break;
            }
        }

        return sumProduct / sumQuantity;
    }


}
