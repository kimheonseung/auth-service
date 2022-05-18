package com.devh.auth.controller;

import com.devh.auth.service.DepartmentService;
import com.devh.auth.vo.DepartmentVO;
import com.devh.module.api.constant.ApiStatus;
import com.devh.module.api.response.ApiResponse;
import com.devh.module.api.vo.TreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("tree")
    public ApiResponse<TreeVO<DepartmentVO>> getTree() {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, departmentService.findTree());
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ApiResponse<DepartmentVO> get(@PathVariable("id") Long id) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, departmentService.findById(id));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("register")
    public ApiResponse<Boolean> postRegister(@RequestBody DepartmentVO departmentVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, departmentService.save(departmentVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("update")
    public ApiResponse<Boolean> postUpdate(@RequestBody DepartmentVO departmentVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, departmentService.update(departmentVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("delete")
    public ApiResponse<Boolean> postDelete(@RequestBody DepartmentVO departmentVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, departmentService.deleteById(departmentVO.getId()));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
