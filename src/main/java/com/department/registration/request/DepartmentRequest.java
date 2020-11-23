package com.department.registration.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String local;
    private String city;
    @Size(min = 2, max = 2, message = "State is mandatory in format 'UF' ")
    private String state;
    private String board;

}
