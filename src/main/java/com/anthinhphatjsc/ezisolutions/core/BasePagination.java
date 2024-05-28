package com.anthinhphatjsc.ezisolutions.core;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePagination<D extends BaseDTO> {
    private Integer currentPage;
    private Integer sizePage;
    private List<D> data;
    private Integer lastPage;
    private Long total;


    public BasePagination(Page<D> page) {
        this.currentPage = page.getPageable().getPageNumber() + 1;
        this.sizePage = page.getPageable().getPageSize();
        this.lastPage = page.nextOrLastPageable().getPageNumber() + 1;
        this.total = page.getTotalElements();
        this.data = page.getContent();
    }

}
