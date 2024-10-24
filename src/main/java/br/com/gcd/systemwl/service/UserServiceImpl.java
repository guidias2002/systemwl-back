package br.com.gcd.systemwl.service;

import br.com.gcd.systemwl.dto.UserDto;
import br.com.gcd.systemwl.dto.UserLoginDto;
import br.com.gcd.systemwl.dto.UserPostDto;
import br.com.gcd.systemwl.entity.UserEntity;
import br.com.gcd.systemwl.mapper.UserMapper;
import br.com.gcd.systemwl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncryptionServiceImpl passwordEncryptionServiceImpl;

    @Autowired
    private TokenService tokenService;

    @Override
    public void createUser(UserPostDto userPostDto) {
        UserEntity userEntity = userMapper.toUserEntity(userPostDto);

        String encryptedPassword = passwordEncryptionServiceImpl.getPasswordEncoder().encode(userPostDto.getPassword());
        userEntity.setPassword(encryptedPassword);

        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return userRepository.findById(id)
                .map(user -> userMapper.toUserDto(user))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Override
    public List<UserDto> listUserDto() {
        List<UserEntity> listUserEntity = userRepository.findAll();

        return userMapper.toListUserDto(listUserEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if(userDto.getName() != null) {
            userEntity.setName(userDto.getName());
        }

        if(userDto.getEmail() != null) {
            userEntity.setEmail(userDto.getEmail());
        }

        if(userDto.getPassword() != null) {
            userEntity.setPassword(userDto.getPassword());
        }

        if(userDto.getLogin() != null) {
            userEntity.setLogin(userDto.getLogin());
        }

        userRepository.save(userEntity);

        return userMapper.toUserDto(userEntity);
    }

    @Override
    public Map<String, Object> loginUser(UserLoginDto userLoginDto) {
        UserEntity user = userRepository.findByLogin(userLoginDto.getLogin());

        if(user == null) {
            throw new RuntimeException("Usuário não encotrado.");
        }

        if(passwordEncryptionServiceImpl.getPasswordEncoder().matches(userLoginDto.getPassword(), user.getPassword())) {
            String token = tokenService.generateToken(userLoginDto.getLogin());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("login", userLoginDto.getLogin());

            return response;
        } else {
            throw new RuntimeException("Login ou senha incorretos.");
        }
    }

    @Override
    public Map<String, Boolean> checkUser(String login, String email) {
        boolean loginExists = userRepository.existsByLogin(login);
        boolean emailExists = userRepository.existsByEmail(email);

        Map<String, Boolean> response = new HashMap<>();
        response.put("login", loginExists);
        response.put("email", emailExists);

        return response;
    }


}
