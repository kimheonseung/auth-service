package com.devh.auth.vo;

import com.devh.common.api.vo.TreeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVO extends TreeVO<DepartmentVO> {
    private Long id;
    private String name;
    private String description;
    private Long parentId;


    @Override
    public boolean isRoot() {
        return parentId == null;
    }

    @Override
    public void setText() {
        super.text = name;
    }
}