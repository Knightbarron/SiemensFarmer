package com.github.tenx.xcom.data.models.auth;

public class RegistrationBody {

    private String email;
    private String password;

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

    public RegistrationBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
