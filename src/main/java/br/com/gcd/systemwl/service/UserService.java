package br.com.gcd.systemwl.service;

import br.com.gcd.systemwl.dto.UserDto;
import br.com.gcd.systemwl.dto.UserLoginDto;
import br.com.gcd.systemwl.dto.UserPostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    void createUser(UserPostDto userPostDto);

    UserDto getUserDtoById(Long id);

    List<UserDto> listUserDto();

    void deleteUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    Map<String, Object> loginUser(UserLoginDto userLoginDto);

    Map<String, Boolean> checkUser(String login, String email);
}
