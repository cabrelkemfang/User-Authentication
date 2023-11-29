package io.growtogether.service.impl;

import io.growtogether.dto.ApiResponse;
import io.growtogether.dto.RoleRequest;
import io.growtogether.dto.RoleResponse;

import java.util.List;

public interface IRoleService {
    void addRole(RoleRequest roleRequest);

    ApiResponse<List<RoleResponse>> getRoles(int page, int size);
}
