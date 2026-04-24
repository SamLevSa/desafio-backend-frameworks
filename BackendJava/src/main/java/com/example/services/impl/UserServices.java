public interface UserServices {
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(Long id);
    UserResponseDTO create(UserRequestDTO dto);
    void delete(Long id);
}