package com.example.readingisgood.types.requests;

public class SignInRequest {
    private String mail;
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
