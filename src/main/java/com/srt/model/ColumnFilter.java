package com.srt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnFilter {
    // entity column id
    private String columnId;
    // filter type - text, number, date, dateTime
    private String filterType;
    // filter operator - equals, notEqual, lessThan, greaterThan, like, inRange, in etc.
    private String filterOperator;
    // filter value, if filter operation is equals, notEqual, lessThan, greaterThan, like etc.
    private String value;
    // filter values, if filter operation is in operation
    private List<String> values;
    // from value, if filter operation is inRange (value & valueTo)
    private String valueTo;
}
