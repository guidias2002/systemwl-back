package br.com.gcd.systemwl.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String generateToken(String login);
}
