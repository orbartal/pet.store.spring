package pet.store.spring.internal.model.classes;

import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public class PetLogicEntityC implements PetLogicEntityI {
	protected long id;
	protected String name;
	protected String status;
	
	public PetLogicEntityC() {}
	
	public PetLogicEntityC(long id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
}
