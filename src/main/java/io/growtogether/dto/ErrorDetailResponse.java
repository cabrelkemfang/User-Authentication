package io.growtogether.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetailResponse {
    private String version;
    private String apiName;
    private String message;
    private int status;
    private List<ValidationError> errors;
}
