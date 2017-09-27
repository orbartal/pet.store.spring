package pet.store.spring.dao.model.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pet.store.spring.dao.model.interfaces.PetDaoEntityI;

@Entity
@Table(name = "pets") 
public class PetDaoEntityC implements PetDaoEntityI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "id")
	protected int id;
	
	@Column (name = "name")
	protected String name;
	
	@Column (name = "password")
	protected String status;
	
	public PetDaoEntityC() {}
	
	public PetDaoEntityC(int id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
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
