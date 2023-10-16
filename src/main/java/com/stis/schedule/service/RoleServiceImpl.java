/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.RoleDto;
import com.stis.schedule.entity.ERole;
import com.stis.schedule.entity.Role;
import com.stis.schedule.mapper.RoleMapper;
import com.stis.schedule.repository.RoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto getRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByName(ERole.valueOf(roleName));
        
        return role.isPresent() ? RoleMapper.mapToDto(role.get()) : null;
    }
    
}
