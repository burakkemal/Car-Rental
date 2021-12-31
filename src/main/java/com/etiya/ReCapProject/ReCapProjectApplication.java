package com.etiya.ReCapProject;



import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.services.fakePos.externalFakePos.FakePos;
import org.hibernate.type.SerializationException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etiya.ReCapProject.core.utilities.results.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.services.findex.externalFindexCalculator.FindexCalculator;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestControllerAdvice
public class ReCapProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReCapProjectApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.etiya.ReCapProject")).build();
	}

	@Bean
	public ModelMapper getModeMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public FindexCalculator getFindexCalculator() {
		return new FindexCalculator();
	}

	@Bean
	public FakePos getFakePos(){
		return new FakePos();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException argumentNotValidException){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(Messages.VALIDATIONERROR,validationErrors);
        return errorDataResult;
    }

    @ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResult handleEntityNotFound(EntityNotFoundException ex){
        return new ErrorResult("Data not found!");
    }
    
    @ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleNoSuchElementException(NoSuchElementException exception){
		ErrorResult error = new ErrorResult(Messages.DATANOTFOUND);
		return error;
	}


	@ExceptionHandler(SerializationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleSerializationException(SerializationException serializationException){
		ErrorResult error = new ErrorResult(Messages.FORMATERROR);
		return error;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResult handHttpMessageExceptionError(HttpMessageNotReadableException httpMessageNotReadableException){
		ErrorResult errorResult = new ErrorResult(Messages.FORMATERROR);
		return errorResult;

	}







	
}
