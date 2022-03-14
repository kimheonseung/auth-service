package com.devh.auth.service;

import com.devh.auth.entity.Department;
import com.devh.auth.repository.DepartmentRepository;
import com.devh.auth.vo.DepartmentVO;
import com.devh.common.api.vo.TreeVO;
import com.devh.common.exception.ServiceException;
import com.devh.common.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentVO findById(Long id) throws ServiceException {
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(id);
            return entityToVO(optionalDepartment.orElseThrow());
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<TreeVO<DepartmentVO>> findTree() throws ServiceException {
        try {
            List<TreeVO<DepartmentVO>> result = new ArrayList<>();
            List<Department> departmentList = departmentRepository.findAll();
            List<DepartmentVO> departmentVOList = new ArrayList<>();
            departmentList.forEach(e -> departmentVOList.add(entityToVO(e)));

            for(DepartmentVO v : departmentVOList) {
                v.setText();
                if(v.isRoot()) {
                    result.add(v);
                    continue;
                }

                DepartmentVO parent = searchParent(departmentVOList, v);

                parent.addChild(v);
            }

            return result;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public DepartmentVO findOneByName(String name) throws ServiceException {
        try {
            Department department = departmentRepository.findOneByName(name).orElseThrow();
            return entityToVO(department);
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean save(DepartmentVO departmentVO) throws ServiceException {
        try {
//            Department department = voToEntity(departmentVO);
//            department.setDepartment(departmentVO.getParentId() == null ? null : departmentRepository.findById(departmentVO.getParentId()).orElseThrow());
//            departmentRepository.save(department);
            departmentRepository.save(voToEntity(departmentVO));
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(DepartmentVO departmentVO) throws ServiceException {
        try {
            Department dbDepartment = departmentRepository.findById(departmentVO.getId()).orElseThrow();
            Department dbDepartmentParent = dbDepartment.getDepartment();
            final Long dbParentId = dbDepartmentParent == null ? null : dbDepartmentParent.getId();
            final Long parentId = departmentVO.getParentId();

            final String departmentName = departmentVO.getName();
            final String departmentDescription = departmentVO.getDescription();

            if(departmentName != null)
                dbDepartment.setName(departmentName);
            if(departmentDescription != null)
                dbDepartment.setDescription(departmentDescription);

            if(
                    (dbParentId != null && !dbParentId.equals(parentId)) ||
                    (parentId != null && !parentId.equals(dbParentId))
            ) {
                Department dbParent = parentId == null ? null : departmentRepository.findById(parentId).orElseThrow();
                dbDepartment.setDepartment(dbParent);
            }

            departmentRepository.save(dbDepartment);
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            departmentRepository.deleteById(id);
            return true;
//            return departmentRepository.save(voToEntity(departmentVO)) == 1;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deleteAllById(Iterable<? extends Long> ids) throws ServiceException {
        try {
            departmentRepository.deleteAllById(ids);
            return true;
//            return departmentRepository.save(voToEntity(departmentVO)) == 1;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    private DepartmentVO searchParent(List<DepartmentVO> departmentVOList, DepartmentVO v) throws Exception {
        final int size = departmentVOList.size();
        int tryCount = 0;
        while (tryCount < size) {
            DepartmentVO pick = departmentVOList.get(tryCount++);
            if(v.getParentId().equals(pick.getId()))
                return pick;
        }

        throw new Exception("Cannot find parent node. " + v.toString());
    }

}
