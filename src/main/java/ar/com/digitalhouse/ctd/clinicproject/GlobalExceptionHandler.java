package ar.com.digitalhouse.ctd.clinicproject;

import ar.com.digitalhouse.ctd.clinicproject.exception.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger( GlobalExceptionHandler.class );

    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity<?> notFound( ResourceNotFoundException e ) {

        logger.error( "ResourceNotFoundException: " + e.getMessage() );
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<?> handleValidationExceptions ( MethodArgumentNotValidException e ) {

        Map<String, Map<String, String>> errors = new HashMap<>();
        Map<String, String> mapErrors = new HashMap<>();
        errors.put( "errors", mapErrors );

        e.getBindingResult()
         .getAllErrors()
         .forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapErrors.put( fieldName , errorMessage );
            logger.error( "MethodArgumentNotValidException: "+ e.getObjectName() + ": " + fieldName + ": " + errorMessage );
         });

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( errors );
    }

}
