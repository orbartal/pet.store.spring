package pet.store.spring.web.pet.model.interfaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;

@JsonDeserialize(as = PetUiEntityC.class)
public interface PetUiEntityI {

	public int getId();

	public void setId(int id);

	public String getName();

	public void setName(String name);

	public String getStatus();

	public void setStatus(String status);

}