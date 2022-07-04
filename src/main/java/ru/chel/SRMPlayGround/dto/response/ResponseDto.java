package ru.chel.SRMPlayGround.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ResponseDto<T> {
    private boolean ok;
    private T data;
}
