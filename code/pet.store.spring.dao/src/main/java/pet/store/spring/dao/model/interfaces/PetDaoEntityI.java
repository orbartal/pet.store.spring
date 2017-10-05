package pet.store.spring.dao.model.interfaces;

public interface PetDaoEntityI {
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public String getStatus();
	public void setStatus(String status);
}