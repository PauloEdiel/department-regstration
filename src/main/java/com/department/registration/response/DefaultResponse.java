package com.department.registration.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class DefaultResponse {
    private String status;
    private String messages;
}
