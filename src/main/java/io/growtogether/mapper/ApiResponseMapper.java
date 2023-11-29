package io.growtogether.mapper;

import io.growtogether.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiResponseMapper {

    private final BuildProperties buildProperties;

//    public NoContentOutput mapToNoContentOutput() {
//        return NoContentOutput.builder()
//                .apiName(buildProperties.getName())
//                .version(buildProperties.getVersion())
//                .status(Status.builder().code(HttpStatus.CREATED.value()).value("OK").build())
//                .build();
//    }

    public <T, U> ApiResponse<List<U>> mapToApiResponse(Page<T> t, List<U> u) {

        var apiResponse = new ApiResponse<List<U>>();
        apiResponse.setApiName(buildProperties.getName());
        apiResponse.setVersion(buildProperties.getVersion());
        apiResponse.setResponse(u);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setPageSize(t.getSize());
        apiResponse.setCurrentPageNumber(t.getNumber());
        apiResponse.setTotalRecords(t.getTotalElements());
        return apiResponse;
    }

}
