package com.tamnafriends.authservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "nickname")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;  // BCrypt 등 암호화 저장

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 10)
    private String gender;  // '남' '여'

    @Column(length = 20)
    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(length = 20)
    private String status = "ACTIVE";

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @Column(name = "phone_verified", nullable = false)
    private boolean phoneVerified = false;

    // 기본 생성자 필수 (JPA)
    protected User() {}

    // 필요한 경우 생성자/빌더 패턴 등 추가
    public User(String email, String password, String nickname, LocalDate birthday, String gender, String phone) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public String getNickname() { return nickname; }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Getter/Setter
    // ... (IDE 자동 생성 또는 Lombok 사용 가능)

    // updatedAt 자동 업데이트
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    // 필요에 따라 toString, equals, hashCode, 빌더 등 추가
}
