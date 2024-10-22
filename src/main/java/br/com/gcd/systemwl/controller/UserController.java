package br.com.gcd.systemwl.controller;

import br.com.gcd.systemwl.dto.UserDto;
import br.com.gcd.systemwl.dto.UserLoginDto;
import br.com.gcd.systemwl.dto.UserPostDto;
import br.com.gcd.systemwl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserPostDto userPostDto) {
        userService.createUser(userPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable Long id) {
        UserDto userDto = userService.getUserDtoById(id);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listUserDto() {
        List<UserDto> listUser = userService.listUserDto();

        return ResponseEntity.ok(listUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);

        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserLoginDto userLoginDto) {
        Map<String, Object> response = userService.loginUser(userLoginDto);

        return ResponseEntity.ok(response);
    }

    // verificando se ja tem usuario cadastrado com email ou login
    @GetMapping("/check/{login}/{email}")
    public ResponseEntity<Map<String, Boolean>> checkUser(@RequestParam String login, @RequestParam String email) {
        Map<String, Boolean> response = userService.checkUser(login, email);

        return ResponseEntity.ok(response);
    }
}
