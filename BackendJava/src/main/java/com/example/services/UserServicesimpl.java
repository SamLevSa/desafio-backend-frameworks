@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
            .stream()
            .map(u -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail()))
            .toList();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        User user = new User(null, dto.name(), dto.email());
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}