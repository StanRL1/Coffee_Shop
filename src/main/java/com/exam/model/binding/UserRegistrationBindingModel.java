package com.exam.model.binding;

import com.exam.model.validators.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegistrationBindingModel {
    @NotBlank(message = "Username cant be blank")
    @Size(min=5,max=20,message = "Username must be between 5 and 20")
    private String username;
    @NotNull(message = "Email cant be empty")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(min=5,max = 20,message = "Last Name must be between 5 and 20")
    private String lastName;
    @NotBlank(message = "Password cant be blank")
    @Size(min=3,message = "Password must be more than 3")
    private String password;
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
