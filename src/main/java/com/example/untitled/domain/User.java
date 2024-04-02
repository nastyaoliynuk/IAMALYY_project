package com.example.untitled.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "users")
public class User {
    @Id
    @NotBlank
    @Column(name = "username")
    private String username;
    @NotBlank
    @Column(name = "password")
    private String password;
    @Column(name = "phone-number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "b-day")
    private LocalDate birthday;
    @Column(name = "info-about-me")
    private String infoAboutMe;
    @Column(name = "location")
    private String location;
    @Column(name = "avatar")
    private String avatar;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NumberParseException {
        // Перевірка валідності номеру телефону перед ініціалізацією користувача
        if (phoneNumber != null) {
            if (phoneNumber.isEmpty() || isValidPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            } else {
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE,
                        "Fail number");
            }
        } else {
            this.phoneNumber = null; // Встановлення значення null для поля phoneNumber
        }
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDate getB_day() {
        return birthday;
    }

    public void setB_day(LocalDate b_day) {
        this.birthday = b_day;
    }


    public String getInfo_about_me() {
        return infoAboutMe;
    }

    public void setInfo_about_me(String info_about_me) {
        this.infoAboutMe = info_about_me;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public void setLoginAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Метод для перевірки валідності номеру телефону
    private boolean isValidPhoneNumber(String phoneNumber) throws NumberParseException {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, null));
        } catch (NumberParseException e) {
            return false;
        }
    }
}
