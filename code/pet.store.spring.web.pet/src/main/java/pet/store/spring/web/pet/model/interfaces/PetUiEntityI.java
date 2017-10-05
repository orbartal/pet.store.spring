package pet.store.spring.web.pet.model.interfaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;

@JsonDeserialize(as = PetUiEntityC.class)
public interface PetUiEntityI {

	public long getId();

	public void setId(long id);

	public String getName();

	public void setName(String name);

	public String getStatus();

	public void setStatus(String status);

}