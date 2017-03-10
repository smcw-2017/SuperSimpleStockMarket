package com.company.tradingSystem.stock;

import com.company.tradingSystem.Formulae;
import com.company.tradingSystem.SSSMException;

/**
 * Implements the Stock interface for Preferred stock.
 * holds additional details over common stock.
 */
public class PreferredStock extends CommonStock
{
    private double m_fixedDividend; // percentage value

    public PreferredStock(final String p_name,
                          final long p_lastDividend,
                          final long p_parValue,
                          final double p_fixedDividend) throws SSSMException
    {
        super(p_name, p_lastDividend, p_parValue);
        // Verify that the fixed dividend is not negative.
        if(p_fixedDividend<0)
        {
            throw new SSSMException("Invalid quantity for fixed dividend :" + m_fixedDividend);
        }
        m_fixedDividend = p_fixedDividend;
    }

    public double getDividend()
    {
        return m_fixedDividend * getParValue() / 100.0;
    }

    public double getDividendYield(long p_price)
    {
        return Formulae.getPreferredDividendYield(
                m_fixedDividend, getParValue(), p_price);
    }
}
