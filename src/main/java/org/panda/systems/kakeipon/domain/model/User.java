package org.panda.systems.kakeipon.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private Long userId;

    @NotNull
    private String nickName;

    private String firstName;

    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private Date birthday;

    @NotNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName roleName;

    @NotNull
    private LocalDateTime entryDate;

    @NotNull
    private LocalDateTime updateDate;

    public Long getUserId() {
        return this.userId;
    }

    protected void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return this.nickName;
    }

    void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // ToDo: implements decryption
    public String getPassword() {
        return password;
    }

    // ToDo: implements encryption
    void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleName getRoleName() {
        return this.roleName;
    }

    void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getEntryDate() {
        return this.entryDate;
    }

    void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
