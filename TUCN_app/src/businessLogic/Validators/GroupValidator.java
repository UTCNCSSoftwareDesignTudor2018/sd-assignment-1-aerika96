package businessLogic.Validators;

public class GroupValidator implements Validator<Integer> {

	@Override
	public boolean validate(Integer t) {
		if(t<10000) {
			return false;
		}
		return true;
		
	}

}
