package com.company.tradingSystem.junit;

import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.TradeProcessor;
import com.company.tradingSystem.factories.StockFactory;
import com.company.tradingSystem.interfaces.Stock;
import junit.framework.TestCase;

import java.util.Date;

public class TradeProcessorTest extends TestCase
{
    TradeProcessor m_processor;
    Stock m_testStock;
    Date m_oldDate;
    Date m_olderDate;

    public void setUp() throws Exception
    {
        super.setUp();

        m_testStock = StockFactory.getStock("TETLY", 20, 100);
        m_oldDate = new Date();
        m_oldDate.setTime(m_oldDate.getTime() - 60000);
        m_olderDate = new Date();
        m_olderDate.setTime(m_olderDate.getTime() - 600000);

        m_processor = new TradeProcessor();
        m_processor.addStock(StockFactory.getStock("TEA", 20, 100));
        m_processor.addStock(StockFactory.getStock("EARLGREY", 30, 300, 3));
        m_processor.addStock(m_testStock);

    }

    public void testAddStock() throws Exception
    {
       // (m_processor.getStock("xyz"));

        System.out.println("1");
        Stock stock = StockFactory.getStock("COFFEE", 0, 100);
        m_processor.addStock(stock);

        assertEquals(4, m_processor.getStockList().length);
    }

    public void testGetStockList() throws Exception
    {
        assertEquals(3, m_processor.getStockList().length);
    }

    public void testGetStock() throws Exception
    {
        assertEquals(m_testStock, m_processor.getStock("TETLY"));
    }

    public void testAddBuyTrade() throws Exception
    {
        m_processor.addBuyTrade("TEA", m_olderDate, 100, 100);
        assertEquals(1, m_processor.getStock("TEA").getTrades().size());

        m_processor.addBuyTrade("TEA", new Date(), 100, 100);
        assertEquals(2, m_processor.getStock("TEA").getTrades().size());

        try
        {
            m_processor.addBuyTrade("TEA", m_oldDate, -1, 100);
            fail("Should have thrown SSSMException for bad trade");
        }
        catch(SSSMException e){}
        try
        {
            m_processor.addBuyTrade(null, m_oldDate, 100, 100);
            fail("Should have thrown SSSMException for bad trade");
        }
        catch(SSSMException e){}

        assertEquals(2, m_processor.getStock("TEA").getTrades().size());
    }


    public void testAddSellTrade() throws Exception
    {
        m_processor.addSellTrade("EARLGREY", m_olderDate, 100, 100);
        assertEquals(1, m_processor.getStock("EARLGREY").getTrades().size());

        m_processor.addSellTrade("EARLGREY", new Date(), 100, 100);
        assertEquals(2, m_processor.getStock("EARLGREY").getTrades().size());

        try
        { 
            m_processor.addSellTrade("TEA", m_oldDate, 100, -1);
            fail("Should have thrown SSSMException for bad trade");
        }
        catch(SSSMException e){}
        try
        {
            m_processor.addSellTrade("doesn't exist", m_oldDate, 100, 100);
            fail("Should have thrown SSSMException for bad trade");
        }
        catch(SSSMException e){}

        assertEquals(2, m_processor.getStock("EARLGREY").getTrades().size());
    }


    public void testCalculateDividend() throws Exception
    {
        double d1 = m_processor.calculateDividendYield("TEA", 2000);

        assertEquals(0.01,d1);

        double d2 = m_processor.calculateDividendYield("EARLGREY", 3000);

        assertEquals(0.003, d2);
    }

    public void testCalculatePERatio() throws Exception
    {
        double d1 = m_processor.calculatePERatio("TEA", 100);
        assertEquals(5.0, d1);
        double d2 = m_processor.calculatePERatio("EARLGREY", 900);
        assertEquals(100.0, d2);
    }

    public void testGetVolumeWeightedStockPrice() throws Exception
    {
        m_processor.addBuyTrade("TEA", m_olderDate, 1000, 200);
        m_processor.addBuyTrade("TEA", m_olderDate, 2000, 220);
        m_processor.addBuyTrade("TEA", m_oldDate, 4000, 210);
        m_processor.addBuyTrade("TEA", m_oldDate, 1234, 210);
        m_processor.addBuyTrade("TEA", new Date(), 3210, 220);
        m_processor.addBuyTrade("TEA", new Date(), 4444, 170);

        double d2 = m_processor.getVolumeWeightedStockPrice("TEA");

        assertEquals(198.70, d2, 0.01);
    }

    public void testGetAllShareIndex() throws Exception
    {
        m_processor.addBuyTrade("TEA", m_olderDate, 1000, 200);
        m_processor.addBuyTrade("TEA", m_olderDate, 2000, 220);
        m_processor.addBuyTrade("EARLGREY", m_oldDate, 4000, 210);
        m_processor.addBuyTrade("TEA", m_oldDate, 1234, 210);
        m_processor.addBuyTrade("TETLY", new Date(), 3210, 220);
        m_processor.addBuyTrade("TEA", new Date(), 4444, 170);

        double d1 = m_processor.getAllShareIndex();
        assertEquals(202.11, d1, 0.01);
    }

}