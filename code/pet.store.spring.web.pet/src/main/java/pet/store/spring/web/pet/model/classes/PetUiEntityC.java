package pet.store.spring.web.pet.model.classes;

import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class PetUiEntityC implements PetUiEntityI {
	protected long id;
	protected String name;
	protected String status;
	//category
	//tags
	
	public PetUiEntityC() {}
	
	public PetUiEntityC(long id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id1) {
		id = id1;
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
