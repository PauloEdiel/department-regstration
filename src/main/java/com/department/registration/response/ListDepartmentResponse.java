package com.department.registration.response;

import com.department.registration.model.DepartmentEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ListDepartmentResponse {
    private DefaultResponse payload;
    private List<DepartmentEntity> departments;
}

