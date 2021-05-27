package com.exam.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    @NotBlank(message = "Username cant be blank")
    @Size(min=3,max=20,message = "Username must be between 3 and 20")
    private String username;
    @NotBlank(message = "Password cant be blank")
    @Size(min=3,max=20,message = "Password must be between 3 and 20")
    private String password;

    public UserLoginBindingModel() {
    }

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
}
