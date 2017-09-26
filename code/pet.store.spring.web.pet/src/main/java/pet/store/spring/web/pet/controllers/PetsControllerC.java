package pet.store.spring.web.pet.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pet.store.spring.web.pet.api.uses.interfaces.PetsWebAdapterI;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

@RestController
@RequestMapping("/pet")
public class PetsControllerC {
	
	protected PetsWebAdapterI m_petsWebAdapter;
	
	public PetsControllerC(PetsWebAdapterI petsWebAdapter) {
		m_petsWebAdapter = petsWebAdapter;
	}
	
	@ApiOperation(value = "Find pet by ID")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation", response = PetUiEntityI.class),
       	@ApiResponse(code = 400, message = "Invalid ID supplied", response = MethodArgumentTypeMismatchException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public PetUiEntityI read(
			@ApiParam(value = "ID of pet to return", required = true) @PathVariable int id
			) throws Exception {
	return m_petsWebAdapter.read(id);
	}
	 
	@ApiOperation(value = "Add a new pet to the store")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation"),
        @ApiResponse(code = 405, message = "Invalid input", response = Exception.class)
    })
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public void create(
			@ApiParam(value = "Pet object that needs to be added to the store", required = true) 
			@RequestBody PetUiEntityI pet
			) throws Exception {
		m_petsWebAdapter.create(pet);
	}
	
	@ApiOperation(value = "Deletes a pet")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation"),
       	@ApiResponse(code = 400, message = "Invalid ID supplied", response = MethodArgumentTypeMismatchException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(
			@ApiParam(value = "Pet id to delete", required = true) @PathVariable int id
			) throws Exception {
		m_petsWebAdapter.delete(id);
	}
	
}