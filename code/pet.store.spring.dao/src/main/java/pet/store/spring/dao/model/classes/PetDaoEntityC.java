package pet.store.spring.dao.model.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import pet.store.spring.dao.model.interfaces.PetDaoEntityI;

@Entity
@Table(name = "pets") 
public class PetDaoEntityC implements PetDaoEntityI {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "id")
	protected long id;
	
	@Column (name = "name")
	protected String name;
	
	@Column (name = "password")
	protected String status;
	
	public PetDaoEntityC() {}
	
	public PetDaoEntityC(long id, String name, String status) {
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
