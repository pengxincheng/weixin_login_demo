package com.pxc.weixin_login_demo.dto.resp;

/**
 * @author
 * @date 2018/5/31 11:11
 * @desc
 */
public class PageVo {
    //分页信息数据
    private Integer pageSize=15;//默认15
    private Integer pageNo;
    private Long totalRow;//总行数
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
    }

    public Long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Long totalRow) {
        this.totalRow=totalRow;
    }
}
