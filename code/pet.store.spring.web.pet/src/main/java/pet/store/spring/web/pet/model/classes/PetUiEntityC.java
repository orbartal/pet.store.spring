package pet.store.spring.web.pet.model.classes;

import java.io.Serializable;

import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class PetUiEntityC implements PetUiEntityI, Serializable  {
	private static final long serialVersionUID = 2304047613280144192L;
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
	public Long getId() {
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
