package businessLogic.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPValidator implements Validator<String> {
	

	private static final int LENGTH=13;
	private static final String ID_PATTERN="[1-2][0-9][0-1][1-9][0-3][1-9][0-9]*";

	@Override
	public boolean validate(String t) {
			Pattern pattern = Pattern.compile(ID_PATTERN);
			Matcher m = pattern.matcher(t);
			if(t.length()!=LENGTH || !m.matches()) {
				return false;
			}
			return true;
		
	}
	

}
