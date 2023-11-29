package io.growtogether.service;

import io.growtogether.dto.ApiResponse;
import io.growtogether.dto.RoleRequest;
import io.growtogether.dto.RoleResponse;
import io.growtogether.mapper.ApiResponseMapper;
import io.growtogether.mapper.RoleMapper;
import io.growtogether.model.Role;
import io.growtogether.repository.RoleRepository;
import io.growtogether.service.impl.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final ApiResponseMapper apiResponseMapper;
    private final RoleMapper roleMapper;

    @Override
    public void addRole(RoleRequest roleRequest) {

        this.roleRepository.save(this.roleMapper.mapToRole(roleRequest));
    }

    @Override
    public ApiResponse<List<RoleResponse>> getRoles(int page, int size) {
        Page<Role> pageableRoles = this.roleRepository.findAll(PageRequest.of(page - 1, size));
        var roleResponse = pageableRoles.stream()
                .map(this.roleMapper::mapToRoleResponse)
                .toList();

        return this.apiResponseMapper.mapToApiResponse(pageableRoles, roleResponse);
    }
}
