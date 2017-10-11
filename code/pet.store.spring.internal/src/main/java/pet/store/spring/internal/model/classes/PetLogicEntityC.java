package pet.store.spring.internal.model.classes;

import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public class PetLogicEntityC implements PetLogicEntityI {
	protected Long id;
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
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!this.getClass().isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final PetLogicEntityC other = (PetLogicEntityC) obj;
	    boolean bId = (this.id == null)? (other.id == null) : (this.id.equals(other.id));
	    boolean bName = (this.name == null)? (other.name == null) : (this.name.equals(other.name));
	    boolean bStatus = (this.status == null)? (other.status == null) : (this.status.equals(other.status));
	    return (bId && bName && bStatus);
	}
}
