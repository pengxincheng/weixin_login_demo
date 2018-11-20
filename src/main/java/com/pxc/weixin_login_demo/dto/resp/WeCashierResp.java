package com.pxc.weixin_login_demo.dto.resp;

import java.util.List;

/**
 * @author
 * @date 2018/5/31 10:55
 * @desc
 */
public class WeCashierResp<T> {
    public  static  final String SUCCESS="SUCCESS";//成功
    public  static  final String FAIL="FAIL";//失败
    public  static  final String EX="EX";//系统异常

    private String code;//响应码
    private String msg;//响应消息
    private String errorCode;//错误码
    private List<T> listDatas;//数据集合
    private T data;//单笔数据


    private  PageVo  pageVo;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode=errorCode;
    }

    public List<T> getListDatas() {
        return listDatas;
    }

    public void setListDatas(List<T> listDatas) {
        this.listDatas=listDatas;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data=data;
    }

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo=pageVo;
    }
}
