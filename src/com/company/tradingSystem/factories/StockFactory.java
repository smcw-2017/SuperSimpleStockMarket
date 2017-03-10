package com.company.tradingSystem.factories;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.stock.CommonStock;
import com.company.tradingSystem.stock.PreferredStock;
import com.company.tradingSystem.stock.Stock;

/**
 * Call this to create an instance of a Stock object
 */
public class StockFactory
{
    /**
     * Returns appropriate Stock type.
     *
     * @param p_symbol Symbol for stock
     * @param p_lastDividend Last dividend, in pennies.
     * @param p_parValue Par value, in pennies.
     * @return Stock. Common stock with these parameters
     * @throws SSSMException
     */
    public static Stock getStock(final String p_symbol,
                                 final long p_lastDividend,
                                 final long p_parValue) throws SSSMException
    {
        return new CommonStock(p_symbol, p_lastDividend, p_parValue);
    }


    /**
     * Returns appropriate Stock type.
     *
     * @param p_symbol Symbol for stock
     * @param p_lastDividend Last dividend, in pennies.
     * @param p_parValue Par value, in pennies.
     * @param p_fixedDividend Fixed dividend amount as percentage.
     * @return Stock. Preferred stock with these parameters   
     * @throws SSSMException
     */
    public static Stock getStock(final String p_symbol,
                                 final long p_lastDividend,
                                 final long p_parValue,
                                 final double p_fixedDividend) throws SSSMException
    {
        return new PreferredStock(p_symbol, p_lastDividend, p_parValue, p_fixedDividend);
    }
}
