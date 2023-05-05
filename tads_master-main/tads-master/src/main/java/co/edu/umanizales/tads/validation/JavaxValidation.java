package co.edu.umanizales.tads.validation;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class JavaxValidation {

    /*Se hace con el objetivo de que saque un error en caso tal de que los datos ingresados no sean aquellos
    que se necesitan para ingresar correctamente los datos
     */
    //Le decimos que nos responda con una mala solicitud
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    //Van a entrar todas las excepciones de controladores
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Usamos las excepciones que establecimos. Se ejecuta cuando enviamos datos incorrectos
    public ResponseEntity<ResponseDTO> handleValidateException(MethodArgumentNotValidException exception){
        //Usamos un hashmap que es similar a los reportes
        Map<String, String> errors = new HashMap<>();
        //Le decimos que abra un campo para los errores, y envie cierto mensaje
        //Error es una función anónima
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            //Obtenemos el nombre del campo que tiene el error
            errors.put(error.getField(), error.getDefaultMessage());
        });
        //Nos otorga los errores
        return new ResponseEntity<>(new ResponseDTO(400, errors, null), HttpStatus.BAD_REQUEST);
    }
}
