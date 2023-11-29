package io.growtogether.mapper;

import io.growtogether.dto.RoleRequest;
import io.growtogether.dto.RoleResponse;
import io.growtogether.model.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RoleMapper {
    public RoleResponse mapToRoleResponse(Role role) {
        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .authority(role.getAuthority())
                .createdDate(role.getCreatedDate())
                .build();
    }

    public Role mapToRole(RoleRequest roleRequest) {
        return Role.builder()
                .authority(roleRequest.getAuthority())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
