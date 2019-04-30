package au.com.mebank.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.jws.Oneway;

import org.junit.BeforeClass;
import org.junit.Test;

import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;

public class CSVParserTest {
	
	FileParser csvParser = new CSVParser();
	final String filePath = "src/test/resources/files_transactions/account_transactions.csv";


	
	@Test
	public void testNegativeAmountWithReversalOutsideTime() throws Exception{
		

		final String accountID = "ACC334455";
		final String fromDate = "20/10/2018 12:00:00";
		final String toDate = "20/10/2018 19:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("-25.00");
		Integer expectedNumberOfTransactions = 1;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
	
	@Test
	public void testNegativeAmountWithOutReversal() throws Exception{
		

		final String accountID = "ACC334499";
		final String fromDate = "21/10/2018 06:00:00";
		final String toDate = "22/10/2018 15:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("-7.25");
		Integer expectedNumberOfTransactions = 1;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
	@Test
	public void testSingleTransactionWithReversal() throws Exception{
		

		final String accountID = "ACC334455";
		final String fromDate = "20/10/2018 13:00:00";
		final String toDate = "20/10/2018 19:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("0");
		Integer expectedNumberOfTransactions = 0;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
	@Test
	public void testMultipleTransactionWithMultipleReversal() throws Exception{
		

		final String accountID = "ACC334454";
		final String fromDate = "20/10/2018 13:00:00";
		final String toDate = "20/10/2018 19:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("0");
		Integer expectedNumberOfTransactions = 0;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}

	@Test
	public void testPositiveAmountWithNoReversal() throws Exception{
		

		final String accountID = "ACC334453";
		final String fromDate = "15/08/2018 11:00:00";
		final String toDate = "17/11/2018 23:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("68.50");
		Integer expectedNumberOfTransactions = 2;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
	@Test
	public void testPositiveAmountWithReversal() throws Exception{
		

		final String accountID = "ACC334452";
		final String fromDate = "15/08/2018 11:00:00";
		final String toDate = "17/11/2018 23:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("41.00");
		Integer expectedNumberOfTransactions = 1;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
	@Test
	public void testTransactionDoesNotMeetCriteria() throws Exception{
		

		final String accountID = "ACC334452";
		final String fromDate = "15/08/2019 11:00:00";
		final String toDate = "17/11/2019 23:00:00";
		
		BigDecimal expectedAmount = new BigDecimal("0");
		Integer expectedNumberOfTransactions = 0;
		
		RequestDTO request = new RequestDTO(accountID, fromDate, toDate, filePath);
		
		ResponseDTO response = csvParser.parse(request);
		
		assertEquals(expectedNumberOfTransactions,response.getNumberOfTransactions());
		assertEquals(expectedAmount,response.getAccountBalance());
		
	}
	
}
