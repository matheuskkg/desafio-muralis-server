package mkkg.muralis.handlers;

import mkkg.muralis.exceptions.CpfEmUsoException;
import mkkg.muralis.util.ExceptionHandlerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CpfEmUsoExceptionHandler {

    @ExceptionHandler(CpfEmUsoException.class)
    public ResponseEntity handleCpfEmUsoException(CpfEmUsoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandlerUtil.getErrorsMap(e.getMessage()));
    }
}
