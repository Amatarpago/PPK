/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.UserDto;
import com.stis.schedule.entity.ERole;
import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    public boolean deleteUser(Long id);
    public List<UserDto> getAllUsers();
    public List<UserDto> getAllUsersSupervisedBy(UserDto supervisor);
    public UserDto getUser(Long id);
    public UserDto getUserByEmail(String email);
    public boolean hasRole(UserDto user, ERole role);
    public UserDto assignSupervisor(UserDto user, UserDto supervisor);
    public UserDto dismissSupervisor(UserDto user);
}
