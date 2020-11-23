package com.department.registration.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateRequest {
    private Long id;
    private String name;
    private String local;
    private String city;
    private String state;
    private String board;
}
