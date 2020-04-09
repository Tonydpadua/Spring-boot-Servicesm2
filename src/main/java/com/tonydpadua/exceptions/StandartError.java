package com.tonydpadua.exceptions;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandartError implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status;
    private String msg;
    private Long timeStamp;


}
