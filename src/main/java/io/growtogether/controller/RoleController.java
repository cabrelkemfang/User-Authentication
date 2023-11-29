package io.growtogether.controller;

import io.growtogether.dto.ApiResponse;
import io.growtogether.dto.RoleRequest;
import io.growtogether.dto.RoleResponse;
import io.growtogether.service.impl.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService iRoleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRole(@RequestBody @Valid RoleRequest roleRequest) {
        this.iRoleService.addRole(roleRequest);
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return this.iRoleService.getRoles(page, size);
    }
}
