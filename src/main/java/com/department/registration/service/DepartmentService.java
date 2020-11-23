package com.department.registration.service;

import com.department.registration.model.DepartmentEntity;
import com.department.registration.repository.DepartmentRepository;
import com.department.registration.request.DepartmentRequest;
import com.department.registration.response.DefaultResponse;
import com.department.registration.response.DepartmentResponse;
import com.department.registration.response.ListDepartmentResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public ListDepartmentResponse findAll() {
        return ListDepartmentResponse.builder()
            .payload(this.toSuccessResponse())
            .departments(departmentRepository.findAll())
            .build();
    }

    public DepartmentResponse findById(long id) {
        return DepartmentResponse.builder()
            .payload(this.toSuccessResponse())
            .departmentEntity(departmentRepository.findById(id))
            .build();
    }

    public DepartmentResponse save(DepartmentRequest request) {
        return DepartmentResponse.builder()
            .payload(toSuccessResponse())
            .departmentEntity(departmentRepository.save(toEntity(request)))
            .build();
    }

    public DepartmentResponse updateEntity(DepartmentEntity request) {
        return DepartmentResponse.builder()
            .payload(toSuccessResponse())
            .departmentEntity(departmentRepository.save(request))
            .build();
    }

    public void deleteById(long id) {
        departmentRepository.deleteById(id);
    }

    public boolean isExistsDepartment(long id) {
        return Objects.isNull(departmentRepository.findById(id));
    }

    public DefaultResponse toSuccessResponse() {
        return DefaultResponse.builder()
            .status("SUCCESS")
            .messages("Process executed successfully")
            .build();
    }

    public DefaultResponse toNotFoundResponse() {
        return DefaultResponse.builder()
            .status("NOT_FOUND")
            .messages("Resource Not Found")
            .build();
    }

    DepartmentEntity toEntity(DepartmentRequest request) {
        return departmentRepository.save(DepartmentEntity.builder()
            .name(request.getName())
            .local(request.getLocal())
            .city(request.getCity())
            .state(request.getState())
            .board(request.getBoard())
            .build());
    }
}
