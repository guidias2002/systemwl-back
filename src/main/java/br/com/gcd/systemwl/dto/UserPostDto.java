package br.com.gcd.systemwl.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserPostDto {
    private String name;
    private String login;
    private String password;
    private String email;
}
