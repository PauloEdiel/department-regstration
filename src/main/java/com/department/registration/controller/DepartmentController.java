package com.department.registration.controller;

import com.department.registration.model.DepartmentEntity;
import com.department.registration.request.DepartmentRequest;
import com.department.registration.response.DefaultResponse;
import com.department.registration.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/departments")

public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-department")
    public ResponseEntity<?> findByCode(@RequestParam(name = "id") long id) {

        if (departmentService.isExistsDepartment(id)) {
            return new ResponseEntity(departmentService.toNotFoundResponse(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(departmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@Valid @RequestBody DepartmentRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        return new ResponseEntity(departmentService.save(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentEntity request) {
        departmentService.updateEntity(request);
        return new ResponseEntity(DefaultResponse.builder().messages("OK").status("Success").build(), HttpStatus.OK);
    }

    @DeleteMapping("/department")
    public ResponseEntity<?> deleteByCode(@RequestParam(name = "id") long id) {
        if (departmentService.isExistsDepartment(id)) {
            return new ResponseEntity(departmentService.toNotFoundResponse(), HttpStatus.NOT_FOUND);
        }
        departmentService.deleteById(id);
        return new ResponseEntity(departmentService.toSuccessResponse(), HttpStatus.OK);
    }
}
