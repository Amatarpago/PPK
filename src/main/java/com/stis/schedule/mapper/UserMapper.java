/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.mapper;

import com.stis.schedule.dto.RoleDto;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.entity.Role;
import com.stis.schedule.entity.User;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static User mapToEntity(UserDto userDto) {
        Set<Role> roles = new HashSet<>();
        User supervisor = null;
        if (userDto.getRoles() != null) {
            roles = userDto.getRoles().stream()
                .map(role -> RoleMapper.mapToEntity(role))
                .collect(Collectors.toSet());
        }
        if (userDto.getSupervisor() != null) {
            supervisor = mapToEntity(userDto.getSupervisor());
        }

        return User.builder()
            .id(userDto.getId())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .roles(roles)
            .build();
    }

    public static UserDto mapToDto(User user) {
        Set<RoleDto> roleDtos = new HashSet<>();
        UserDto supervisorDto = null;
        if (user.getRoles() != null) {
            roleDtos = user.getRoles().stream()
                .map(role -> RoleMapper.mapToDto(role))
                .collect(Collectors.toSet());
        }
        if (user.getSupervisor() != null) {
            supervisorDto = mapToDto(user.getSupervisor());
        }

        return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .password(user.getPassword())
            .roles(roleDtos)
            .build();
    }
}