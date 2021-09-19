package com.star.ris.common.dto;

import com.star.ris.common.RisConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String status;
    private String data;
    private String message;

    public String toStringRecord(){
        StringBuilder builder = new StringBuilder();
        builder.append(status);
        builder.append(RisConstants.SEPARATOR_COMA);
        builder.append(data);
        builder.append(RisConstants.SEPARATOR_COMA);
        builder.append(message);
        return builder.toString();
    }

    public String toStringRecordError(){
        StringBuilder builder = new StringBuilder();
        builder.append(status);
        builder.append(RisConstants.SEPARATOR_COMA);
        builder.append(message);
        return builder.toString();
    }
}
