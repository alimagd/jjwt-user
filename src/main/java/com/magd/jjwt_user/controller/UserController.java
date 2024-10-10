package com.magd.jjwt_user.controller;

import com.magd.jjwt_user.model.dto.AuthRequest;
import com.magd.jjwt_user.model.dto.UserInfoResponseDto;
import com.magd.jjwt_user.model.entity.UserInfo;
import com.magd.jjwt_user.security.JwtService;
import com.magd.jjwt_user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {

        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile") // http://localhost:8080/auth/admin/adminProfile // ROLE_ADMIN
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @GetMapping("/admin/all_users")
    public List<UserInfoResponseDto> getAllUsersInfoDto() {
        List<UserInfo> userInfoList = service.getAllUsers();
        return userInfoList.stream().map(userInfo -> new UserInfoResponseDto(
                userInfo.getName(), userInfo.getEmail(), userInfo.getRoles()
        )).toList();
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}