package com.ow.tracer.common.vo;

import java.util.Map;

/**
 * @auther: Easy
 * @date: 19-6-12 10:42
 * @description:
 */
public class GetParam<T> {
    public Integer current;
    public T searchForm;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public T getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(T searchForm) {
        this.searchForm = searchForm;
    }
}
