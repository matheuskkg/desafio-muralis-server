package mkkg.muralis.handlers;

import mkkg.muralis.exceptions.ContatoDuplicadoException;
import mkkg.muralis.exceptions.ContatoInvalidoException;
import mkkg.muralis.util.ExceptionHandlerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContatoExceptionHandler {

    @ExceptionHandler(ContatoInvalidoException.class)
    public ResponseEntity handleContatoInvalidoException(ContatoInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandlerUtil.getErrorsMap(e.getMessage()));
    }

    @ExceptionHandler(ContatoDuplicadoException.class)
    public ResponseEntity handleContatoDuplicadoException(ContatoDuplicadoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandlerUtil.getErrorsMap(e.getMessage()));
    }
}
