@RestController
@RequestMapping ("/api/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping 
    public ResponseEntity <List <UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity <UserResponseDTO> create(@RequestBody @Valid userrequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        userService.delete(id)
        return ResponseEntity.noContent().build();
    }
}