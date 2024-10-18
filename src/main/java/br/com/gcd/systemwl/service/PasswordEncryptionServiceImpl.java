package br.com.gcd.systemwl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionServiceImpl {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }
}
