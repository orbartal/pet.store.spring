package pet.store.spring.web.pet.Validators.classes;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class PetValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		 return PetUiEntityI.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		if (obj==null) {
			err.reject("obj==null");
		}
		PetUiEntityI pet = (PetUiEntityI) obj;
		if (pet.getId()<0) {
			err.reject("id<0");
		}else if (pet.getName()==null || pet.getName().isEmpty()) {
			err.reject("invalid pet name: " + pet.getName());
		}else if (pet.getStatus()==null || pet.getStatus().isEmpty()) {
			err.reject("invalid pet status: " + pet.getStatus());
		}
	}
}