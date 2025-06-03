package com.tamnafriends.authservice.repository;

import com.tamnafriends.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 회원 찾기
    Optional<User> findByEmail(String email);

    // 닉네임으로 회원 찾기
    Optional<User> findByNickname(String nickname);

    // 이메일 중복 체크
    boolean existsByEmail(String email);

    // 닉네임 중복 체크
    boolean existsByNickname(String nickname);
}
