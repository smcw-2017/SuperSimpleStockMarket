package com.company.tradingSystem.stock;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.trade.Trade;

import java.util.*;

/**
 * Abstract base class of stock
 * Has the basic aspects relating to the Stock interface and is implemented in CommonStock or PreferredStock.
 */
public abstract class Stock implements com.company.tradingSystem.interfaces.Stock
{
    private String m_name;
    private List<Trade> m_trades = new ArrayList<Trade>();

    private Comparator<Trade> m_tradeCom = new Comparator<Trade>()
    {
        public int compare(final Trade o1, final Trade o2)
        {
            long l = o1.getTimeStamp().getTime() - o2.getTimeStamp().getTime();

            return (l<0?-1:l>0?1:0);
        }
    };

    public Stock(final String p_name) throws SSSMException
    {
        if(p_name == null)
        {
            throw new SSSMException("Invalid stock symbol name");
        }
        m_name = p_name;
    }

    public String getName()
    {
        return m_name;
    }

    public void addTrade(final Trade p_trade)
    {
        m_trades.add(p_trade);
    }

    public List<Trade> getSortedTrades()
    {
        Collections.sort(m_trades, m_tradeCom);
        return m_trades;
    }

    public List<Trade> getTrades()
    {
        return m_trades;
    }

}
