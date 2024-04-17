package com.example.untitled.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@Table(name = "users")
public class User {
    @Id
    @NotBlank(message = "Username cannot be blank")//Валідація на рівні моделі з використанням аннотацій:
    @Column(name = "username")
    private String username;
    @NotBlank(message = "Password cannot be blank")//Валідація на рівні моделі з використанням аннотацій:
    @Column(name = "password")
    private String password;
    @Column(name = "phone-number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "b-day")
    private LocalDate birthday;
    @Column(name = "info-about-me")
    private String infoAboutMe;
    @Column(name = "location")
    private String location;
    @Column(name = "avatar")
    private String avatar;

    public enum UserStatus {
        NONAME,
        MODEL,
        PHOTOGRAF
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot contain spaces");
        }
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.contains(" ")) {
            throw new IllegalArgumentException("Password cannot contain spaces");
        }
        this.password = password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NumberParseException {
        // Перевірка валідності номеру телефону перед ініціалізацією користувача
        if (phoneNumber != null && !phoneNumber.isEmpty()) {

            if (isValidPhoneNumber(phoneNumber)) {
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
        if (email == null || email.trim().isEmpty()) {
           this.email=null;
        }
        else {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        else{
        this.email = email;}
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = null;
        } else {
            this.name = name;
        }
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDate getB_day() {
        return birthday;
    }

    public void setB_day(LocalDate b_day) {
        if (b_day == null || b_day.isEqual(LocalDate.now())) {
            this.birthday = null;
        } else {
            this.birthday = b_day;
        }
    }


    public String getInfo_about_me() {
        return infoAboutMe;
    }

    public void setInfo_about_me(String info_about_me) {
        if (info_about_me == null || info_about_me.trim().isEmpty()) {
            this.infoAboutMe = null;
        } else {
            this.infoAboutMe = info_about_me;
        }
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(location==null || location.trim().isEmpty()){
            this.location=null;
        }
        else {
        this.location = location;}
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        if (avatar == null || avatar.trim().isEmpty()) {
            this.avatar = null;
        } else {
            this.avatar = avatar;
        }
    }

    // Метод для перевірки валідності номеру телефону
    private boolean isValidPhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, null));
        } catch (NumberParseException e) {
            return false;
        }
    }


    public class EmailValidator {

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        public static boolean isValid(String email) {
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

}
