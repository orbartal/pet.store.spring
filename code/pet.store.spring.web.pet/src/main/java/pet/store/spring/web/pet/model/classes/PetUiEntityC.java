package pet.store.spring.web.pet.model.classes;

import java.io.Serializable;

import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class PetUiEntityC implements PetUiEntityI, Serializable  {
	private static final long serialVersionUID = 2304047613280144192L;
	protected Long id;
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
	

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!this.getClass().isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final PetUiEntityC other = (PetUiEntityC) obj;
	    boolean bId = (this.id == null)? (other.id == null) : (this.id.equals(other.id));
	    boolean bName = (this.name == null)? (other.name == null) : (this.name.equals(other.name));
	    boolean bStatus = (this.status == null)? (other.status == null) : (this.status.equals(other.status));
	    return (bId && bName && bStatus);
	}
}
