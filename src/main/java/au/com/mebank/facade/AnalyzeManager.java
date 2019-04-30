/**
 * 
 */
package au.com.mebank.facade;

import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;

/**
 * @author Usman
 *
 */
public interface AnalyzeManager {

	/**
	 *  This works as a facade responsible for end to end processing
	 * @param requestDTO contains the criteria
	 * @return 
	 */
	public ResponseDTO analyzeAndProcessTransactions(RequestDTO requestDTO);
}
