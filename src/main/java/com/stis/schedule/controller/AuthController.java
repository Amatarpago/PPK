/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.controller;

import com.stis.schedule.auth.ApiErrorResponse;
import com.stis.schedule.auth.AuthRequest;
import com.stis.schedule.auth.AuthResponse;
import com.stis.schedule.auth.JwtUtils;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "Otentikasi user untuk mendapatkan token jwt.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "otentikasi berhasil", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "email atau password salah", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            })
        }
    )
    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String accessToken = jwtUtils.generateJwtToken(authentication);
        AuthResponse response = new AuthResponse(request.getEmail(), accessToken);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Membuat akun user baru.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "berhasil membuat akun baru", content = @Content)
        }
    )
    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody @Valid UserDto request) {
        userService.createUser(request);
        return ResponseEntity.status(201).build();
    }
}
