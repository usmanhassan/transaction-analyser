/**
 * 
 */
package au.com.mebank.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import au.com.mebank.constants.TransactionConstants;
import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;
import au.com.mebank.dto.TransactionDTO;
import au.com.mebank.dto.TransactionDTO.TransactionType;
import au.com.mebank.util.DateUtil;

/**
 * @author Usman
 *
 */
public class CSVParser implements FileParser {

	enum FileHeaders {
		transactionId, fromAccountId, toAccountId, createdAt, amount, transactionType, relatedTransaction
	}

	// to hold the transaction amount
	Map<String, BigDecimal> transactionAmountMap = new HashMap<String, BigDecimal>();

	
	@Override
	public ResponseDTO parse(RequestDTO requestDTO) {

		ResponseDTO response = new ResponseDTO();
		try {
			String accountId = requestDTO.getAccountID();

			Iterable<CSVRecord> transactions = readTransactionsFromFile(requestDTO);

			StreamSupport.stream(transactions.spliterator(), false).map(mapToItem).filter(filterRecords(requestDTO))
					.forEach(transaction -> {
						if (transaction.getTransactionType().equals(TransactionType.PAYMENT)) {

							if (transaction.getFromAccountId().equals(accountId)) {
								transactionAmountMap.put(transaction.getTransactionId(),
										new BigDecimal(transaction.getAmount()).negate());
							} else if (transaction.getToAccountId().equals(accountId)) {
								transactionAmountMap.put(transaction.getTransactionId(),
										new BigDecimal(transaction.getAmount()));
							}
						} else if (transaction.getTransactionType().equals(TransactionType.REVERSAL)) {

							transactionAmountMap.remove(transaction.getRelatedTransaction());

						}
					});

			// calculate the relative balance
			BigDecimal relativeBalance = transactionAmountMap.values().stream().reduce(BigDecimal.ZERO,
					BigDecimal::add);

			response.setAccountBalance(relativeBalance);
			response.setNumberOfTransactions(transactionAmountMap.size());

			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	/**
	 * Reads the CSV file  
	 */
	private final Iterable<CSVRecord> readTransactionsFromFile(RequestDTO requestDTO) throws IOException {

		BufferedReader reader = Files.newBufferedReader(Paths.get(requestDTO.getFilePath()));

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(FileHeaders.class).withSkipHeaderRecord().withTrim()
				.parse(reader);

		return records;
	}

	// used for mapping
	private final Function<CSVRecord, TransactionDTO> mapToItem = record -> {

		final TransactionDTO transaction = new TransactionDTO();
		transaction.setAmount(record.get(FileHeaders.amount));
		transaction.setCreatedAt(DateUtil.convertStringToDateWithTime((record.get(FileHeaders.createdAt)),
				TransactionConstants.TRANSACTION_DATE_TIME_FORMAT));
		transaction.setFromAccountId(record.get(FileHeaders.fromAccountId));
		transaction.setToAccountId(record.get(FileHeaders.toAccountId));
		if (record.size() >= FileHeaders.values().length) {
			transaction.setRelatedTransaction(record.get(FileHeaders.relatedTransaction));
		}
		transaction.setTransactionId(record.get(FileHeaders.transactionId));
		transaction.getTransactionType();
		transaction.setTransactionType(TransactionType.valueOf(record.get(FileHeaders.transactionType)));
		return transaction;
	};

	// used as a filter
	private final Predicate<? super TransactionDTO> filterRecords(RequestDTO requestDTO) {

		return transaction -> {

			boolean meetCriteria = false;

			String fromAccountId = transaction.getFromAccountId();
			String toAccountId = transaction.getToAccountId();
			String accountId = requestDTO.getAccountID();
			LocalDateTime createdAt = transaction.getCreatedAt();
			LocalDateTime fromDate = DateUtil.convertStringToDateWithTime(requestDTO.getFromDate(),
					TransactionConstants.TRANSACTION_DATE_TIME_FORMAT);
			LocalDateTime toDate = DateUtil.convertStringToDateWithTime(requestDTO.getToDate(),
					TransactionConstants.TRANSACTION_DATE_TIME_FORMAT);
			TransactionType transactionType = transaction.getTransactionType();

			if (fromAccountId.equals(accountId) || toAccountId.equals(accountId)) {

				if (transactionType.equals(TransactionType.REVERSAL) || (transactionType.equals(TransactionType.PAYMENT)
						&& isDateBetween(createdAt, fromDate, toDate))) {

					return true;
				}

			}

			return meetCriteria;

		};
	}

	public boolean isDateBetween(LocalDateTime dateToCheck, LocalDateTime fromDate, LocalDateTime toDate) {
		return dateToCheck.compareTo(fromDate) >= 0 && dateToCheck.compareTo(toDate) <= 0;
	}
}
