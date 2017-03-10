package com.company.tradingSystem.stock.junit;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.factories.StockFactory;
import com.company.tradingSystem.stock.CommonStock;
import junit.framework.TestCase;

import java.util.Date;

public class CommonStockTest extends TestCase
{
    public void testCreates() throws Exception
    {
        CommonStock stock = (CommonStock) StockFactory.getStock("test1", 123, 10);

        assertNotNull(stock);
        assertEquals("test1", stock.getName());
        assertEquals(10, stock.getParValue());
        assertEquals(123.0, stock.getDividend());
        assertEquals(0.1, stock.getDividendYield(1230));
        assertEquals(10.0, stock.getPERatio(1230));
        assertEquals(0, stock.getTrades().size());
        assertEquals(0.0, stock.getVolumeWeightedStockPrice5Min(new Date()));   // as non added
    }
        
    public void testFails() throws Exception
    {
        try
        {
            CommonStock stock = (CommonStock) StockFactory.getStock("test1", 1000, 0);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            CommonStock stock = (CommonStock) StockFactory.getStock("test1", -1, 1000);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            CommonStock stock = (CommonStock) StockFactory.getStock("test1", 1000, -1);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}

        try
        {
            CommonStock stock = (CommonStock) StockFactory.getStock(null, 1000, 1000);
            fail("Should have thrown SSSMException due to bad input");
        }
        catch(SSSMException e) {}
    }

}