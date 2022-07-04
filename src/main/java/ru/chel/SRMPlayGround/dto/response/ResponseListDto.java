package ru.chel.SRMPlayGround.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class ResponseListDto<T> {
    private boolean ok;
    private List<T> data;
}
