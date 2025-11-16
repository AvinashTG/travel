package com.srt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColumnVO {
    private String id;
    private String displayName;
    private String field;
    private String aggFunc;
}
