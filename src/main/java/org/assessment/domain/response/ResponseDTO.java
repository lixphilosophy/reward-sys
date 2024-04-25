package org.assessment.domain.response;

import lombok.*;

/**
 * ResponseDTO general return type
 * String msg error message
 * String code error code
 * <T> ent main return payload
 * <U> ext extra return payload
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T, U> {
    private String msg;
    private String code;
    private Data<T, U> data;

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data<T, U> {
        private T ent;
        private U ext;
    }
}