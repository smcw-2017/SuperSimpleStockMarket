package com.company.tradingSystem.factories.junit;

import com.company.tradingSystem.factories.StockFactory;
import com.company.tradingSystem.interfaces.Stock;
import junit.framework.TestCase;

import java.util.Date;

public class StockFactoryTest extends TestCase
{

    public void setUp() throws Exception
    {
        super.setUp();

    }


    public void testGetStock() throws Exception
    {
        Stock s = StockFactory.getStock("test1", 123, 10);
        assertNotNull(s);
        assertEquals("test1", s.getName());
        assertEquals(10, s.getParValue());
        assertEquals(123.0, s.getDividend());
        assertEquals(0.1, s.getDividendYield(1230));
        assertEquals(10.0, s.getPERatio(1230));
        assertEquals(0, s.getTrades().size());
        assertEquals(0.0, s.getVolumeWeightedStockPrice5Min(new Date()));   // as non added

    }

    public void testGetStock1() throws Exception
    {
        Stock s = StockFactory.getStock("test2", 321, 100, 1.5);
        assertNotNull(s);
        assertEquals(100, s.getParValue());
        assertEquals("test2", s.getName());
        assertEquals(1.5, s.getDividend());
        assertEquals(0.0075, s.getDividendYield(200));
        assertEquals(100.0, s.getPERatio(150));
        assertEquals(0, s.getTrades().size());
        assertEquals(0.0, s.getVolumeWeightedStockPrice5Min(new Date()));   // as non added
    }

}