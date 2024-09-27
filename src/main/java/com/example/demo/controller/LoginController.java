package com.example.demo.controller;

import com.example.demo.dto.Login;
import com.example.demo.dto.Sessao;
import com.example.demo.model.user;
import com.example.demo.repository.userRepository;
import com.example.demo.security.JWTCreator;
import com.example.demo.security.JWTObject;
import com.example.demo.security.SecurityConfigJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/entry")
public class LoginController {

    @Autowired
    private SecurityConfigJwt securityConfig;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private userRepository repository;

    @PostMapping(value = "/login")
    public ResponseEntity<Sessao> logar(@RequestBody Login login) {
        user User = repository.findyUsername(login.getUsername());
        if(User!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), User.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(User.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + securityConfig.EXPIRATION)));
            jwtObject.setRoles(User.getRoles());
            sessao.setToken(JWTCreator.create(securityConfig.PREFIX, jwtObject,securityConfig.KEY));
            return ResponseEntity.ok().body(sessao);
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

}
