package org.uniara.AuthService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.AuthService.DTO.LoginUserDTO;
import org.uniara.AuthService.constant.Constant;
import org.uniara.AuthService.model.ResponseToken;
import org.uniara.AuthService.model.User;
import org.uniara.AuthService.security.JwtTokenProvider;
import org.uniara.AuthService.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(Constant.API_LOGIN)
    public ResponseToken login(@RequestBody LoginUserDTO user) {
        Optional<User> userFound = userService.findByEmail(user.getEmail());

        if(userFound.isEmpty() || !userFound.get().getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas!");
        }

        String token = jwtTokenProvider.generateToken(userFound.get().getUsername());

        return new ResponseToken(token, "Autenticado");
    }

    @PostMapping(Constant.API_AUTH)
    public boolean auth(@RequestHeader("Authorization") String token) {
        return jwtTokenProvider.validateToken(token.replace("Bearer ", ""));
    }
}
