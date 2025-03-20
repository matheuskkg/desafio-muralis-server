package mkkg.muralis.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd
public class ExceptionHandlerUtil {

    public static Map<String, List<String>> getErrorsMap(String errors) {
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("erros", List.of(errors));

        return errorsResponse;
    }

    public static Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("erros", errors);

        return errorsResponse;
    }
}
