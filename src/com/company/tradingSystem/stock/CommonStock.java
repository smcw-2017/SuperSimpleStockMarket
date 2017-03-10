package com.company.tradingSystem.stock;

import com.company.tradingSystem.Formulae;
import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.trade.Trade;

import java.util.Date;
import java.util.List;

/**
 * Implements the Stock interface for Common stock
 */
public class CommonStock extends Stock
{
    private long m_lastDividend;
    private long m_parValue;

    public CommonStock(final String p_name,
                       final long p_lastDividend,
                       final long p_parValue) throws SSSMException
    {
        super(p_name);
        // Verify that the last dividend is not negative and par value are not negative or zero.
        if(p_lastDividend<0)
        {
            throw new SSSMException("Invalid quantity for last dividend :" + p_lastDividend);
        }
        if(p_parValue<=0)
        {
            throw new SSSMException("Invalid price for par value :" + p_parValue);
        }
        m_lastDividend = p_lastDividend;
        m_parValue = p_parValue;
    }

    public double getDividend()
    {
        return m_lastDividend;
    }

    public long getParValue()
    {
        return m_parValue;
    }

    public double getDividendYield(long p_price)
    {
        return Formulae.getCommonDividendYield(m_lastDividend, p_price);
    }

    public double getPERatio(long p_price)
    {
        return Formulae.getPERatio(p_price, getDividend());
    }

    /**
     * Calculate Volume Weighted Stock Price based on trades in past 5 minutes
     * @param p_date The time to count back from.
     * @return
     */
    public double getVolumeWeightedStockPrice5Min(final Date p_date)
    {
        Date startDate = new Date(p_date.getTime() - 300000);
        List<Trade> trades = getSortedTrades();

        return Formulae.getVolumeWeightedStockPrice(trades, startDate);
    }

}
