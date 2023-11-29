package io.growtogether.mapper;

import io.growtogether.dto.ErrorDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorDetailResponseMapper {

    private final BuildProperties buildProperties;

    public ErrorDetailResponse mapToErrorDetailResponse(String message, int status) {
        return ErrorDetailResponse.builder()
                .apiName(buildProperties.getName())
                .version(buildProperties.getVersion())
                .message(message)
                .status(status)
                .build();
    }

}
