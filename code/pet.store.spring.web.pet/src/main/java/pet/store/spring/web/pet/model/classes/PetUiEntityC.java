package pet.store.spring.web.pet.model.classes;

import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class PetUiEntityC implements PetUiEntityI {
	protected int id;
	protected String name;
	protected String status;
	//category
	//tags
	
	public PetUiEntityC() {}
	
	public PetUiEntityC(int id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#getStatus()
	 */
	@Override
	public String getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see pet.store.spring.web.pet.model.classes.PetUiEntityI#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
}
