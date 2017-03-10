package com.company.tradingSystem.junit;

import com.company.tradingSystem.Formulae;
import com.company.tradingSystem.trade.Trade;
import com.company.tradingSystem.trade.TradeType;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

public class FormulaeTest extends TestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

    }

    public void testGetCommonDividendYield() throws Exception
    {
        assertEquals(0.01,Formulae.getCommonDividendYield(10, 1000));
    }

    public void testGetPreferredDividendYield() throws Exception
    {
        assertEquals(0.025,Formulae.getPreferredDividendYield(5.0, 1000, 2000));
    }

    public void testGetPERatio() throws Exception
    {
        assertEquals(100.0, Formulae.getPERatio(1000, 10));
    }

    public void testGetGeometricMean() throws Exception
    {
        double[] ds = {20.0, 30.0, 25.5, 20.6, 26.7, 35.0};
        assertEquals(25.79, Formulae.getGeometricMean(ds), 0.01);
    }

    public void testGetVolumeWeightedStockPrice() throws Exception
    {
        ArrayList<Trade> trades = new ArrayList<Trade>();
        trades.add(new Trade(new Date(), 1000, TradeType.BUY, 200));
        trades.add(new Trade(new Date(), 2000, TradeType.SELL, 220));
        trades.add(new Trade(new Date(), 4000, TradeType.BUY, 210));
        trades.add(new Trade(new Date(), 1234, TradeType.SELL, 210));
        trades.add(new Trade(new Date(), 3210, TradeType.BUY, 220));
        trades.add(new Trade(new Date(), 4444, TradeType.BUY, 170));

        Date end = new Date();
        Date start = new Date(end.getTime() - 300000);

        assertEquals(201.46, Formulae.getVolumeWeightedStockPrice(trades, start), 0.01);

    }

}