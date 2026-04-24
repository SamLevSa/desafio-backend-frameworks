@RestControllerAdvice

public class globalexceptionhandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity <String> handleRuntime(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOTFOUND).body(ex.getMessage();)
    }
}