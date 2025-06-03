package com.tamnafriends.authservice.controller;

import com.tamnafriends.authservice.dto.SigninRequest;
import com.tamnafriends.authservice.dto.SignupRequest;
import com.tamnafriends.authservice.entity.User;
import com.tamnafriends.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//추후 회원가입 이외의 기능(로그인, 이메일 중복체크 등)이 늘어나면
//컨트롤러에서 메서드만 추가하면 됨.
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/auth")
    public String hello() {
        return "API Running!";
    }


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        User user = authService.signin(request);
        return ResponseEntity.ok(user.getNickname() + "님 로그인 성공!");
    }
}
