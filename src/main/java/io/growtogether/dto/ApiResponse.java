package io.growtogether.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String version;
    private String apiName;
    private int status;
    T response;
    private int pageSize;
    private int currentPageNumber;
    private long totalRecords;

}

