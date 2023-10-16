/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.controller;

import com.stis.schedule.auth.ChangePasswordRequest;
import com.stis.schedule.auth.WrongPasswordException;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.service.UserService;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;
    
    @Operation(summary = "Melihat data user yang terotentikasi.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "data user", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @GetMapping("/account")
    public ResponseEntity<?> getProfile(Principal principal) {
        UserDto user = userService.getUserByEmail(principal.getName());
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Mengubah data user yang terotentikasi. Hanya dapat mengubah nama.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "data user yang telah diperbarui", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @PutMapping("/Account")
    public ResponseEntity<?> editProfile(Principal principal, @RequestBody @Valid UserDto request) {
        UserDto user = userService.getUserByEmail(principal.getName());
        user.setName(request.getName());

        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Mengubah password dari user yang terotentikasi.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "data user yang telah diperbarui", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @PutMapping("/account/password")
    public ResponseEntity<?> changePassword(Principal principal, @RequestBody @Valid ChangePasswordRequest request) {
        UserDto user = userService.getUserByEmail(principal.getName());
        if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        user.setPassword(encoder.encode(request.getNewPassword()));
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Menghapus akun user yang terotentikasi.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil menghapus akun", content = @Content)
        }
    )
    @DeleteMapping("/account")
    public ResponseEntity<?> deleteProfile(Principal principal) {
        UserDto user = userService.getUserByEmail(principal.getName());
        userService.deleteUser(user.getId());

        return ResponseEntity.ok().build();
    }

}