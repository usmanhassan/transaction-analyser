/**
 * 
 */
package au.com.mebank.facade;



import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;
import au.com.mebank.parser.FileParser;
import au.com.mebank.util.ValidationUtil;

/**
 * @author Usman
 *
 */
public class DefaultAnalyzeManager implements AnalyzeManager {

	private  FileParser fileParser;
	
	public DefaultAnalyzeManager(FileParser fileParser) {
		super();
		this.fileParser = fileParser;
	}

	@Override
	public ResponseDTO analyzeAndProcessTransactions(RequestDTO request) {

	    ValidationUtil.validateInput(request);

		return fileParser.parse(request);
	}

}
