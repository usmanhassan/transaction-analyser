/**
 * 
 */
package au.com.mebank.analyser;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;
import au.com.mebank.facade.AnalyzeManager;
import au.com.mebank.facade.DefaultAnalyzeManager;
import au.com.mebank.parser.CSVParser;

/**
 * @author Usman
 *
 */
public class RunApp {

	private static final Logger log = Logger.getLogger(RunApp.class.getName());
	private static ResourceBundle rb = ResourceBundle.getBundle("application");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.setLevel(Level.INFO);
		System.out.println("please enter the account id");

		final Scanner scanner = new Scanner(System.in);
		final String defaultFilePath = rb.getString("defaultFilePath");

			final String input = scanner.nextLine();
			
			final String accountID = input;
			System.out.println(
					"please enter start date time date(yyyy-mm-dd hh:mm:ss) to reterive relative account balance");
			final String fromDate = scanner.nextLine();
			System.out.println(
					"please enter end date time date(yyyy-mm-dd hh:mm:ss) to reterive relative account balance");
			final String toDate = scanner.nextLine();
			System.out.println("please enter the file path or press \"d\" for default file " + defaultFilePath);
			String filePath = scanner.nextLine();

			if (filePath.equals("d")) {
				filePath = defaultFilePath;
			}
			scanner.close();


			final AnalyzeManager analyzeManager = new DefaultAnalyzeManager(new CSVParser());

			ResponseDTO response = null;
			try {

				response = analyzeManager
						.analyzeAndProcessTransactions(new RequestDTO(accountID, fromDate, toDate, filePath));

				DecimalFormat Currency = new DecimalFormat("$00.00");
				System.out.println("Relative balance for the period is: " + Currency.format(response.getAccountBalance()));
				System.out.println("Number of transactions included is: " + response.getNumberOfTransactions());

			} catch (final IllegalArgumentException e) {

				log.severe("One of the Argument was wrong ");
				System.exit(1);
			} 

				
			}

		

	
}
