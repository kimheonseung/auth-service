package com.devh.auth.service;

import com.devh.auth.entity.Department;
import com.devh.auth.vo.DepartmentVO;
import com.devh.common.api.vo.TreeVO;
import com.devh.common.exception.ServiceException;

import java.util.List;

public interface DepartmentService {
    default DepartmentVO entityToVO(Department e) {
        if(e == null)
            return null;
        else
            return DepartmentVO.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .parentId(e.getDepartment() == null ? null : e.getDepartment().getId())
                .build();
    }
    default Department voToEntity(DepartmentVO v) {
        if(v == null)
            return null;
        else
            return Department.builder()
                    .id(v.getId())
                    .name(v.getName())
                    .description(v.getDescription())
                    .department(v.getParentId() == null ? null : Department.builder().id(v.getParentId()).build())
                .build();
    }

    DepartmentVO findById(Long id) throws ServiceException;
    List<TreeVO<DepartmentVO>> findTree() throws ServiceException;
    DepartmentVO findOneByName(String name) throws ServiceException;
    boolean save(DepartmentVO departmentVO) throws ServiceException;
    boolean update(DepartmentVO departmentVO) throws ServiceException;
    boolean deleteById(Long id) throws ServiceException;
    boolean deleteAllById(Iterable<? extends Long> ids) throws ServiceException;
}
