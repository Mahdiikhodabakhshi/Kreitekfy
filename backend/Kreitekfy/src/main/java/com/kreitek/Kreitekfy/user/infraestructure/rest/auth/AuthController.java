package com.kreitek.Kreitekfy.user.infraestructure.rest.auth;


import com.kreitek.Kreitekfy.user.application.dto.LoginDto;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;
import com.kreitek.Kreitekfy.user.application.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final HttpServletResponse httpServletResponse;

    public AuthController(AuthService authService, JwtService jwtService,
                          PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, HttpServletResponse httpServletResponse) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.httpServletResponse = httpServletResponse;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {

        System.out.println(loginDto);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUsername(), loginDto.getPassword()));
        UserDto user = authService.getUser(loginDto.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto) {
        // En la base de datos no queremos guardar la contraseña, generamos
        // un hash.
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto userDtoRegistered = authService.register(userDto);
        String token = jwtService.generateToken(userDtoRegistered);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
