package com.company.tradingSystem.trade;

import com.company.tradingSystem.SSSMException;

import java.util.Date;

/**
 * Trade details to represent a buy or sell trade.
 * Immutable object.
 */
public class Trade
{
    private Date m_timeStamp;   // Time of the trade.
    private long m_quantity;     // Quantity bought or sold.
    private TradeType m_type;   // The trade type (BUY/SELL)
    private long m_price;       // The trade price in pennies

    public Trade(final Date p_timeStamp, final long p_quantity,
                 final TradeType p_type, final long p_price) throws SSSMException
    {
        // Verify that the quantity and price are not negative or zero.
        if(p_quantity<=0)
        {
            throw new SSSMException("Invalid quantity for trade :" + p_quantity);
        }
        if(p_price<=0)
        {
            throw new SSSMException("Invalid price for trade :" + p_price);
        }
        if(p_timeStamp == null)
        {
            throw new SSSMException("Invalid timeStamp for trade");
        }
        if(p_timeStamp.getTime() > (new Date()).getTime())
        {
            throw new SSSMException("Invalid timeStamp for trade");
        }

        m_timeStamp = p_timeStamp;
        m_quantity = p_quantity;
        m_type = p_type;
        m_price = p_price;
    }

    public Date getTimeStamp()
    {
        return m_timeStamp;
    }

    public long getQuantity()
    {
        return m_quantity;
    }

    public TradeType getType()
    {
        return m_type;
    }

    public long getPrice()
    {
        return m_price;
    }
}
