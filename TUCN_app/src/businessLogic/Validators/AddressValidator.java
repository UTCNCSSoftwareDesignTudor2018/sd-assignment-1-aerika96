package businessLogic.Validators;


public class AddressValidator implements Validator<String> {
	private static final int MIN_LENGTH=5;
	private static final int MAX_LENGTH=100;

	public boolean validate(String t) {
		if( t.length()<MIN_LENGTH || t.length()>MAX_LENGTH){
			return false;
		}
		return true;
		
	}

}
