package com.pxc.weixin_login_demo.utils;

import com.pxc.weixin_login_demo.dto.resp.PageVo;
import com.pxc.weixin_login_demo.dto.resp.WeCashierResp;

import java.util.List;

/**
 * @author
 * @date 2018/5/31 11:01
 * @desc
 */
public class WeCashierRespFactory {

    public final  static String SUCCESS_SMG="成功";

    public  static <T> WeCashierResp builderSuccess(){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.SUCCESS );
        weCashierResp.setMsg( SUCCESS_SMG);
        return  weCashierResp;
    }


    public  static <T> WeCashierResp builderSuccess(List<T> datas, PageVo pageVo){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.SUCCESS );
        weCashierResp.setListDatas( datas );
        weCashierResp.setPageVo( pageVo );
        weCashierResp.setMsg(SUCCESS_SMG);
        return  weCashierResp;
    }



    public  static <T> WeCashierResp builderSuccess(T data){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.SUCCESS );
        weCashierResp.setData( data );
        weCashierResp.setMsg( SUCCESS_SMG);
        return  weCashierResp;
    }



    public  static <T> WeCashierResp builderFail(String errorCode, String msg){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.FAIL );
        weCashierResp.setErrorCode( errorCode );
        weCashierResp.setMsg( msg);
        return  weCashierResp;
    }

    public  static <T> WeCashierResp builderFail(String errorCode, String msg, T data){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.FAIL );
        weCashierResp.setData( data );
        weCashierResp.setErrorCode( errorCode );
        weCashierResp.setMsg( msg);
        return  weCashierResp;
    }

    public  static <T> WeCashierResp builderFail(String msg){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.FAIL );
        weCashierResp.setMsg( msg);
        return  weCashierResp;
    }


    public  static <T> WeCashierResp builderFail(String msg, T data){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.FAIL );
        weCashierResp.setMsg( msg);
        weCashierResp.setData( data );
        return  weCashierResp;
    }


    public  static <T> WeCashierResp builderEx(String errorCode, String msg){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.EX );
        weCashierResp.setErrorCode( errorCode );
        weCashierResp.setMsg( msg);
        return  weCashierResp;
    }



    public  static <T> WeCashierResp builderEx(String errorCode, String msg, T data){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.EX );
        weCashierResp.setErrorCode( errorCode );
        weCashierResp.setMsg( msg);
        weCashierResp.setData( data );
        return  weCashierResp;
    }
    public  static <T> WeCashierResp builderEx(String msg){
        WeCashierResp  weCashierResp=new WeCashierResp();
        weCashierResp.setCode( WeCashierResp.EX );
        weCashierResp.setMsg( msg);
        return  weCashierResp;
    }

}
