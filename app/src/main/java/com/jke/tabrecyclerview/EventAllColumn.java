package com.jke.tabrecyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes
 * Created by 荆柯 on 2017/6/4.
 */

public class EventAllColumn {
    private List<String> colums = new ArrayList<>();

    public EventAllColumn(List<String> colums) {
        this.colums = colums;
    }

    public List<String> getColums() {
        return colums;
    }
}
