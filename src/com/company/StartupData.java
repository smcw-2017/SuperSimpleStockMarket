package com.company;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.factories.StockFactory;
import com.company.tradingSystem.interfaces.Stock;
import com.company.tradingSystem.trade.Trade;
import com.company.tradingSystem.trade.TradeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Set up the system to have some stock data already there.  Also test with trade data to be passed in.
 *
 * Created by Stuart on 08/03/2017.
 */
public class StartupData
{
    private static StartupData ourInstance = new StartupData();

    public static StartupData getInstance()
    {
        return ourInstance;
    }

    private Date m_testTime = null;


    private StartupData()
    {
    }

    /**
     * Create and return stock data to be loaded on start up
     * @return List of Stock
     */
    public List<Stock> getStocks()
    {
        List<Stock> stocks = new ArrayList<Stock>();
        try
        {
            stocks.add(StockFactory.getStock("TEA", 0, 100));
            stocks.add(StockFactory.getStock("POP", 8, 100));
            stocks.add(StockFactory.getStock("ALE", 23, 60));
            stocks.add(StockFactory.getStock("GIN", 8, 100, 2));
            stocks.add(StockFactory.getStock("JOE", 13, 250));
        }
        catch(SSSMException e)
        {
            System.out.println("Error in test :" + e.getMessage());
        }
        return stocks;
    }


    /**
     * Create and return trade data to be loaded on start up
     * @return List of Trade
     */
    public List<Record> getTestTrades()
    {
        List<Record> data = new ArrayList<Record>();
        try
        {
            // Trade Symbol, date, quantity, bs, price

            data.add(new Record("TEA", new Trade(getOffsetTime(-250), 10, TradeType.BUY, 200)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-240), 100, TradeType.BUY, 202)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-150), 1000, TradeType.SELL, 203)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-150), 100, TradeType.BUY, 205)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-130), 10, TradeType.BUY, 204)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-125), 100, TradeType.SELL, 204)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-120), 100, TradeType.BUY, 204)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-100), 2000, TradeType.SELL, 205)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-100), 100, TradeType.BUY, 201)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-100), 100, TradeType.SELL, 199)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-89), 100, TradeType.BUY, 198)));
            data.add(new Record("TEA", new Trade(getOffsetTime(-44), 1000, TradeType.BUY, 197)));


            data.add(new Record("POP", new Trade(getOffsetTime(-250), 10, TradeType.BUY, 200)));
            data.add(new Record("POP", new Trade(getOffsetTime(-240), 100, TradeType.BUY, 202)));
            data.add(new Record("POP", new Trade(getOffsetTime(-150), 1000, TradeType.SELL, 203)));


            data.add(new Record("ALE", new Trade(getOffsetTime(-260), 10, TradeType.BUY, 130)));
            data.add(new Record("ALE", new Trade(getOffsetTime(-200), 100, TradeType.BUY, 140)));
            data.add(new Record("ALE", new Trade(getOffsetTime(-150), 1000, TradeType.SELL, 145)));


            data.add(new Record("GIN", new Trade(getOffsetTime(-200), 10, TradeType.BUY, 200)));
            data.add(new Record("GIN", new Trade(getOffsetTime(-40), 100, TradeType.BUY, 202)));
            data.add(new Record("GIN", new Trade(getOffsetTime(-15), 1000, TradeType.SELL, 203)));


            data.add(new Record("JOE", new Trade(getOffsetTime(-25), 10, TradeType.BUY, 500)));
            data.add(new Record("JOE", new Trade(getOffsetTime(-24), 100, TradeType.BUY, 505)));
            data.add(new Record("JOE", new Trade(getOffsetTime(-15), 1000, TradeType.SELL, 505)));
        }
        catch(SSSMException e)
        {
            System.out.println("Error in test :" + e.getMessage());
        }
        return data;
    }

    /**
     * Get what counts as the current time according to the test.
     * As the test is not run over real time and has hard coded time stamps, testing with a hard coded current time.
     * For real live data with valid times, would use current time.
     * @return
     */
    public Date getTestCurrentTime()
    {
        if(m_testTime != null)
        {
            return m_testTime;
        }
        else
        {
            m_testTime = new Date();
            return m_testTime;
        }
    }

    /**
     * This gets a date offset by an amount from the test time
     * @param p_offset in seconds
     * @return
     */
    private Date getOffsetTime(int p_offset)
    {
        Date d = getTestCurrentTime();
        return new Date(d.getTime() + p_offset*1000);
    }

    public static class Record
    {
        public String symbol;
        public Trade trade;

        public Record(final String p_symbol, final Trade p_trade)
        {
            symbol = p_symbol;
            trade = p_trade;
        }
    }

}
