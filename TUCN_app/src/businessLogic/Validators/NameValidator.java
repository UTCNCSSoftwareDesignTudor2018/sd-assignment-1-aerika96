package businessLogic.Validators;

public class NameValidator  implements Validator<String>{

	private static final int MIN_LENGTH=5;
	private static final int MAX_LENGTH=45;

	@Override
	public boolean validate(String t) {
		if( t.length()<MIN_LENGTH || t.length()>MAX_LENGTH){
			return false;
		}
		return true;
	}

}
