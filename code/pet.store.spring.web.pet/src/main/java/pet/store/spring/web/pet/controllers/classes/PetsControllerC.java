package pet.store.spring.web.pet.controllers.classes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
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
import pet.store.spring.web.pet.Validators.classes.PetValidator;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.InvalidPetIdInputException;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

@Validated
@RestController
@RequestMapping(PetsControllerI.URL_PATH)
public class PetsControllerC {
	
	protected PetsWebServiceI m_petsWebService;
	
	public PetsControllerC(PetsWebServiceI petsWebAdapter) {
		m_petsWebService = petsWebAdapter;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.addValidators(new PetValidator ());
	}
	
	@ApiOperation(value = "Find pet by ID")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation", response = PetUiEntityI.class),
       	@ApiResponse(code = 400, message = "Invalid Id supplied", response = InvalidPetIdInputException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = PetsControllerI.READ_BY_ID_URL_PATH, method = RequestMethod.GET, produces = "application/json")
	public PetUiEntityI read(
			@ApiParam(value = "ID of pet to return", required = true)  @Min(0) @Validated @PathVariable Long petId
			) throws Exception {
		return m_petsWebService.read(petId);
	}
	 
	@ApiOperation(value = "Add a new pet to the store")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation"),
        @ApiResponse(code = 405, message = "Invalid input", response = Exception.class)
    })
	@RequestMapping(value = PetsControllerI.CREATE_URL_PATH, method = RequestMethod.POST, produces = "application/json")
	public void create(
			@ApiParam(value = "Pet object that needs to be added to the store", required = true) 
			@Validated @RequestBody PetUiEntityI pet
			) throws Exception {
		m_petsWebService.create(pet);
	}
	
	@ApiOperation(value = "Delete a pet")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Successful operation"),
       	@ApiResponse(code = 400, message = "Invalid ID supplied", response = InvalidPetIdInputException.class),
        @ApiResponse(code = 404, message = "Pet not found", response = Exception.class)
    })
	@RequestMapping(value = PetsControllerI.DELETE_BY_ID_URL_PATH, method = RequestMethod.DELETE, produces = "application/json")
	public void delete(
			@ApiParam(value = "Pet id to delete", required = true) @Min(0) @Validated @PathVariable Long petId
			) throws Exception {
		m_petsWebService.delete(petId);
	}
	
	//////////////////////////////
	// Handle server exception //
	////////////////////////////
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
	public void handle(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
		e.toString();
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
	public void handle(HttpServletRequest request, HttpMessageNotReadableException e) {
		e.toString();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
	public void handle(HttpServletRequest request, MethodArgumentNotValidException e) {
		e.toString();
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.BAD_REQUEST, reason="Invalid ID supplied")  // 400
	public void handle(HttpServletRequest request, ConstraintViolationException e) {
		e.toString();
	}

}