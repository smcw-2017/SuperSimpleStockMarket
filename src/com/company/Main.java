package com.company;

import com.company.tradingSystem.Formulae;
import com.company.tradingSystem.SSSMException;
import com.company.tradingSystem.TradeProcessor;
import com.company.tradingSystem.interfaces.Stock;
import com.company.tradingSystem.trade.Trade;
import com.company.tradingSystem.trade.TradeType;

import java.util.Date;
import java.util.List;

/**
 * Entry point for Super Simple Stock Market
 */
public class Main
{

    /**
     * Creates the Super Simple Stock Market and runs some test cases.
     * @param args
     */
    public static void main(String[] args)
    {
        // Create simple stock market system.
        TradeProcessor processor = new TradeProcessor();

        // Load existing data.
        addStartupStocks(processor);

        // This simulates trades being added as this assignment does not include actual IO
        // Real code would be waiting for input instead at this point.

        try
        {
            addStartupTrades(processor);
        }
        catch(SSSMException e)
        {
            System.out.println("Error in test :" + e.getMessage());
        }


        // test the reporting

        Stock[] stocks = processor.getStockList();

        int stockCount = stocks.length;
        Date date = StartupData.getInstance().getTestCurrentTime();
        double[] vwsPrices = new double[stockCount];

        for(int i = 0; i < stockCount; i++)
        {
            final Stock stock = stocks[i];
            long examplePrice = stock.getParValue() * 2;
            System.out.println("For stock " + stock.getName() + ":");
            System.out.println("Dividend yield :" + stock.getDividendYield(examplePrice));
            System.out.println("P/E ratio :" + stock.getPERatio(examplePrice));

            double vwsPrice = stock.getVolumeWeightedStockPrice5Min(date);
            System.out.println("Volume Weighted Stock Price :" + vwsPrice);

            // Store values for use in all share index
            vwsPrices[i] = vwsPrice;
        }

        double allShareIndex = Formulae.getGeometricMean(vwsPrices);

        System.out.println("GBCE All Share Index :" + allShareIndex);

        // through the processor API
        double allShareIndex2 = processor.getAllShareIndex();
        System.out.println("GBCE All Share Index through API:" + allShareIndex2);

    }

    private static void addStartupStocks(final TradeProcessor p_processor)
    {
        List<Stock> stocks = StartupData.getInstance().getStocks();
        for(Stock stock : stocks)
        {
            p_processor.addStock(stock);
        }
    }

    private static void addStartupTrades(final TradeProcessor p_processor) throws SSSMException
    {
        List<StartupData.Record> records = StartupData.getInstance().getTestTrades();
        for(StartupData.Record record : records)
        {
            Trade trade = record.trade;
            if(trade.getType() == TradeType.BUY)
            {
                p_processor.addBuyTrade(record.symbol, trade.getTimeStamp(), trade.getQuantity(), trade.getPrice());
            }
            else
            {
                p_processor.addSellTrade(record.symbol, trade.getTimeStamp(), trade.getQuantity(), trade.getPrice());
            }
        }
    }
}
