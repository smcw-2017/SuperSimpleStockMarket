package com.company.tradingSystem.trade.junit;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.trade.Trade;
import com.company.tradingSystem.trade.TradeType;
import junit.framework.TestCase;

import java.util.Date;

public class TradeTest extends TestCase
{

    public void testCreates() throws Exception
    {
        Date time = new Date();

        Trade trade = new Trade(time, 100, TradeType.BUY, 200);

        assertNotNull(trade);
        assertEquals(time, trade.getTimeStamp());
        assertEquals(100, trade.getQuantity());
        assertEquals(TradeType.BUY, trade.getType());
        assertEquals(200, trade.getPrice());


        Thread.sleep(10);
        assertFalse((new Date()).equals(trade.getTimeStamp()));


        Trade trade2 = new Trade(time, 200, TradeType.SELL, 250);

        assertNotNull(trade2);
        assertEquals(time, trade2.getTimeStamp());
        assertEquals(200, trade2.getQuantity());
        assertEquals(TradeType.SELL, trade2.getType());
        assertEquals(250, trade2.getPrice());

    }

    public void testFails() throws Exception
    {
        Date time = new Date();
        try
        {
            Trade trade = new Trade(time, 0, TradeType.BUY, 200);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            Trade trade = new Trade(time, 100, TradeType.BUY, 0);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            Trade trade = new Trade(time, -1, TradeType.BUY, 200);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}


        try
        {
            Trade trade = new Trade(time, 100, TradeType.BUY, -1);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}



        try
        {
            Trade trade = new Trade(null, 100, TradeType.BUY, 1);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

    }
}