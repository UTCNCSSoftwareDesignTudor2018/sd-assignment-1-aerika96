package businessLogic.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidator implements Validator<String> {
	
	private static final int LENGTH=8;
	private static final String ID_PATTERN="[A-Z][A-Z][0-9]*";

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
