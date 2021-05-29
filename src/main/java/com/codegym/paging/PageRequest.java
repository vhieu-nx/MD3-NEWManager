package com.codegym.paging;


import com.codegym.sort.Sorter;

public class PageRequest implements  Pageble{
    private Integer page;
    private Integer maxPageItems;
    private Sorter sorter;

    public PageRequest(Integer page, Integer maxPageItems, Sorter sorter) {
        this.page = page;
        this.maxPageItems = maxPageItems;
        this.sorter = sorter;
    }

    public Sorter getSorter() {
        if (this.sorter != null){
            return this.sorter;
        }
        return null;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItems() {
        return maxPageItems;
    }

    public void setMaxPageItems(Integer maxPageItems) {
        this.maxPageItems = maxPageItems;
    }

    public  PageRequest(Integer page, Integer maxPageItems){
        this.page = page;
        this.maxPageItems = maxPageItems;
    }
    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxPageItems != null) {
            return (this.page - 1) * this.maxPageItems;
        }else {
            return null;
        }
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItems;
    }
}
