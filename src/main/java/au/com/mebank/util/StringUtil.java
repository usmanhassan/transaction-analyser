/**
 *
 */
package au.com.mebank.util;

/**
 * Helper class for String
 * 
 * @author Usman Hassan
 *
 */
public class StringUtil {

  private StringUtil() {
    throw new IllegalStateException("Utility class");
  }

	/**
	 * <p>
	 * Checks if a CharSequence is empty (""), null or whitespace only.
	 * </p>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace only
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
