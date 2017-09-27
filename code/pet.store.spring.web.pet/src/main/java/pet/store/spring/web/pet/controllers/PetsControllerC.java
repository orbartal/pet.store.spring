package pet.store.spring.web.pet.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pet.store.spring.web.pet.exceptions.InvalidPetIdInputException;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

@RestController
@RequestMapping("/pet")
public class PetsControllerC {
	
	protected PetsWebServiceI m_petsWebService;
	
	public PetsControllerC(PetsWebServiceI petsWebAdapter) {
		m_petsWebService = petsWebAdapter;
	}
	
	@ApiOperation(value = "Find pet by ID")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation", response = PetUiEntityI.class),
       	@ApiResponse(code = 400, message = "Invalid Id supplied", response = InvalidPetIdInputException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public PetUiEntityI read(
			@ApiParam(value = "ID of pet to return", required = true) @PathVariable String id
			) throws Exception {
		return m_petsWebService.read(id);
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
		m_petsWebService.create(pet);
	}
	
	@ApiOperation(value = "Deletes a pet")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation"),
       	@ApiResponse(code = 400, message = "Invalid ID supplied", response = InvalidPetIdInputException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(
			@ApiParam(value = "Pet id to delete", required = true) @PathVariable String id
			) throws Exception {
		m_petsWebService.delete(id);
	}
	
	///////////////////////////////////////////////////////////////////////
	// Handle spring exception on convert json in request to pet object //
	/////////////////////////////////////////////////////////////////////
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
	public void handleMessageNotReadableException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
	public void handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {}
}