package com.example.SpringProject.Beans;

import com.example.SpringProject.Service.ClientType;

public class UserDetails {
    public String password;
    public String email;
    public ClientType clientType;

    public UserDetails(String password, String email, ClientType clientType) {
        this.password = password;
        this.email = email;
        this.clientType = clientType;
    }
}
