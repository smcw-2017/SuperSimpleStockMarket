Super Simple Stock Market demo.

Notes.

Below is the usage API for using SuperSimpleStockMarket, below that notes on making this.

Set up
To create a SuperSimpleStockMarket create a processor.
    TradeProcessor processor = new TradeProcessor();

Then set up the stocks calling the following per stock
    processor.addStock(stock);

A stock object is created using the stock factory.  The type of stock created is dependent on the parameters passed in.
The overloaded method can take either three or four parameters.  With the extra parameter a preferred stock is returned, otherwise it is a common stock.
	StockFactory.getStock(String symbol, long lastDividend, long parValue)
	StockFactory.getStock(String symbol, long lastDividend, long parValue, double fixedDividend)

Usage
Usage involved the adding of trades and also the reporting from requests regarding trades made.

Add a trade
    addBuyTrade(String stockSymbol, Date timeStamp, long quantity, long price)

    addSellTrade(String stockSymbol, Date timeStamp, long quantity, long price)

quantity, price, lastDividend and parValue are all values given in pennies.
fixedDividend is a percentage value.
The stockSymbol of a trade needs to match an existing stock.

Querying stock.
   To query the trades on a stock the following calls are made on the processor object to return double values with the results.

To get the dividend
    calculateDividend(String stockSymbol, double price)    

To get the P/E Ratio
    calculatePERatio(String stockSymbol, double price)
   
To get the Volume Weighted Stock Price from the current time.
    getVolumeWeightedStockPrice(String stockSymbol)
    
To get the all share index.
    getAllShareIndex()
    

Running from .bat file.
If the classes are compliled to a sub folder called 'classes', the run.bat can be used directly to run the Main class that creates the SuperSimpleStockMarket and runs a few tests on it.  This outputs the results to the console.  Note that as this system does not have any I/O, it just ends after the tests, there is no mechanism to add additional requests from the cmd prompt.


From the problem description I decided to make a system that has a concept of a stock object that would allow trades to be recorded in it and reporting done from the stock object.  As the recording of trades was described as an action for a given stock, each stock had its own separate record of its trades.  If stored in a database table the trades would be a single table and not separate table for each stock.  But as it is held in memory and trade data being accessed only in the context of its given stock, this keeps the data where it is needed and no need to filter a combined single trade data set for just those of the given stock.

The processor would contain the public API to access the system, to allow adding of stock and recording stock trades.  Along with the various reporting on these as represented by the formulae given.  The formulae kept as their own separate as conceptually independent of the stock and trade objects and also allows for easy unit tests for them.

For stock prices I used long values.  Values are given in pennies and assuming in this simple system not have prices or dividends down with fractions of pennies.  If that was needed, would need to change the longs to doubles.

As possible for the trades to come with timestamps in different order, the logic for getting the last 5 minutes of trades sorts the list by date to correctly get the trades in this time period.  I assumed that trades are not allowed to have timestamp in the future.  Depending on volume of trades and how often this call is made a different algorithm could be considered.  Such as a system to ensure that the trades cannot be entered out of time sequence so it would keep it constantly sorted.  But if no risk of performance issues such optimisation would be unnecessary extra effort.

The stock creation used overloaded method and assuming that if the additional information is not given for preferred stock then it is common stock.  The stocks only hold the details as shown in assignment so only holding last dividend and not dividend history. 

This does not have any particular thread handling logic in place. If all the requests come through a single queue handling system it will not need multithreaded support.  If it does need to be thread safe "synchronized" could be used on the methods in the TradeProcessor class to control access.


