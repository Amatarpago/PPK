/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.RoleDto;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.entity.ERole;
import com.stis.schedule.entity.User;
import com.stis.schedule.mapper.UserMapper;
import com.stis.schedule.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(UserMapper.mapToEntity(userDto));
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        userRepository.save(UserMapper.mapToEntity(userDto));
        return userDto;
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.isPresent() ? UserMapper.mapToDto(user.get()) : null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent() ? UserMapper.mapToDto(user.get()) : null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
            .map(user -> UserMapper.mapToDto(user))
            .collect(Collectors.toList());
    }

    @Override
    public boolean hasRole(UserDto user, ERole role) {
        boolean found = false;

        for (RoleDto roleDto : user.getRoles()) {
            if (roleDto.getName() == role) {
                found = true;
            }
        }

        return found;
    }

    @Override
    public UserDto assignSupervisor(UserDto user, UserDto supervisor) throws ResponseStatusException {
        if (!hasRole(user, ERole.ROLE_DOSEN) || !hasRole(supervisor, ERole.ROLE_DOSEN)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user bukan dosen atau supervisor bukan dosen");
        }

        user.setSupervisor(supervisor);
        updateUser(user);
        return user;
    }

    @Override
    public UserDto dismissSupervisor(UserDto user) {
        user.setSupervisor(null);
        updateUser(user);
        return user;
    }

    @Override
    public List<UserDto> getAllUsersSupervisedBy(UserDto supervisor) {
        List<User> supervisee = userRepository.findAllBySupervisor(UserMapper.mapToEntity(supervisor));

        return supervisee.stream()
            .map(user -> UserMapper.mapToDto(user))
            .collect(Collectors.toList());
    }
    
}