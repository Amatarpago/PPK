
package com.stis.schedule.mapper;

import com.stis.schedule.dto.RoleDto;
import com.stis.schedule.entity.Role;

public class RoleMapper {
    public static Role mapToEntity(RoleDto roleDto) {
        return Role.builder()
            .id(roleDto.getId())
            .name(roleDto.getName())
            .build();
    }

    public static RoleDto mapToDto(Role role) {
        return RoleDto.builder()
            .id(role.getId())
            .name(role.getName())
            .build();
    }
}
