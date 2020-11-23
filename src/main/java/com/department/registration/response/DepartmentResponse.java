package com.department.registration.response;

import com.department.registration.model.DepartmentEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartmentResponse {

    private DefaultResponse payload;
    private DepartmentEntity departmentEntity;

}

