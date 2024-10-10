package com.magd.jjwt_user.model.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {


    private  String username; // email
    private  String password;

}
