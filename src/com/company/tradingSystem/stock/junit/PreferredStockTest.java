package com.company.tradingSystem.stock.junit;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.factories.StockFactory;
import com.company.tradingSystem.stock.PreferredStock;
import junit.framework.TestCase;

import java.util.Date;

public class PreferredStockTest extends TestCase
{

    public void testCreates() throws Exception
    {
        PreferredStock stock = (PreferredStock) StockFactory.getStock("test2", 321, 100, 1.5);
                      
        assertNotNull(stock);
        assertEquals(100, stock.getParValue());
        assertEquals("test2", stock.getName());
        assertEquals(1.5, stock.getDividend());
        assertEquals(0.0075, stock.getDividendYield(200));
        assertEquals(100.0, stock.getPERatio(150));
        assertEquals(0, stock.getTrades().size());
        assertEquals(0.0, stock.getVolumeWeightedStockPrice5Min(new Date()));   // as non added
    }
        
    public void testFails() throws Exception
    {
        try
        {
            PreferredStock stock = (PreferredStock) StockFactory.getStock("test1", 1000, 0, 1.5);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            PreferredStock stock = (PreferredStock) StockFactory.getStock("test1", -1, 1000, 1.5);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            PreferredStock stock = (PreferredStock) StockFactory.getStock("test1", 1000, -1, 1.5);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            PreferredStock stock = (PreferredStock) StockFactory.getStock("test1", 1000, 1000, -1.5);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}


        try
        {
            PreferredStock stock = (PreferredStock) StockFactory.getStock(null, 1000, 1000, 1.5);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}
    }

}