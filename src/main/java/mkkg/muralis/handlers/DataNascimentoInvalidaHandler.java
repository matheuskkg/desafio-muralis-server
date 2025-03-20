package mkkg.muralis.handlers;

import mkkg.muralis.exceptions.DataNascimentoInvalidaException;
import mkkg.muralis.util.ExceptionHandlerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataNascimentoInvalidaHandler {

    @ExceptionHandler(DataNascimentoInvalidaException.class)
    public ResponseEntity handleDataNascimentoInvalidaException(DataNascimentoInvalidaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandlerUtil.getErrorsMap(e.getMessage()));
    }
}
