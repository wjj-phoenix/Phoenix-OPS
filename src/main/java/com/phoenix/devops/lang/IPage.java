package com.phoenix.devops.lang;

import java.util.List;

import com.mybatisflex.core.paginate.Page;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IPage<T> {
    @Parameter(description = "当前页码")
    private long page;

    /**
     * 当前页的数量
     */
    @Parameter(description = "每页数据量大小")
    private long limit;

    /**
     * 总页数
     */
    @Parameter(description = "总页数")
    private long pages;

    /**
     * 总记录数
     */
    @Parameter(description = "总的数据量")
    private long total;

    /**
     * 结果列表
     */
    @Parameter(description = "结果数据")
    private List<T> rows;

    public IPage(Page<T> page) {
        this.page = page.getPageNumber();
        this.limit = page.getPageSize();
        this.pages = page.getTotalPage();
        this.total = page.getTotalRow();
        this.rows = page.getRecords();
    }
}