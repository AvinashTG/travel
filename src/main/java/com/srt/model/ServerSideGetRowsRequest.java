package com.srt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerSideGetRowsRequest {
    private int rowNumber;
    private int pageSize;
    // row group columns
    private List<ColumnVO> rowGroupCols;
    // value columns
    private List<ColumnVO> valueCols;
    // pivot columns
    private List<ColumnVO> pivotCols;
    // true if pivot mode is one, otherwise false
    private boolean pivotMode;
    // what groups the user is viewing
    private List<String> groupKeys;
    // if filtering, what the filter model is
    private List<ColumnFilter> filterModel;
    // if sorting, what the sort model is
    private List<SortModel> sortModel;

    private Sort getSort(String defaultField) {
        if (sortModel == null || sortModel.isEmpty()) {
            return Sort.by(defaultField).ascending();
        }

        Sort sort = Sort.unsorted();
        for (SortModel sm : sortModel) {
            Sort newSort = sm.getSort().equalsIgnoreCase("asc") ?
                    Sort.by(sm.getColId()).ascending() :
                    Sort.by(sm.getColId()).descending();
            sort = sort.and(newSort);
        }
        return sort;
    }

    public PageRequest getPageRequest(String defaultField) {
        Sort sort = getSort(defaultField);
        return PageRequest.of(rowNumber, pageSize, sort);
    }
}
