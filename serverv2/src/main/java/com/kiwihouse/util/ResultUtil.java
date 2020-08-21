package com.kiwihouse.util;

import com.github.pagehelper.Page;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 陈伟平
 * @date 2020-03-30-下午 3:21
 */
public class ResultUtil {

    /**
     * 增加行数如果为null或者0则表示删除失败
     *
     * @param row 行数
     * @return ResultList
     */
    public static ResultList verifyDelete(Integer row) {
        if (null == row || row == 0) {
            return new ResultList(Code.DELETE_NULL.getCode(), Code.DELETE_NULL.getMsg(), null);
        } else {
            return new ResultList(Code.DELETE_SUCCESS.getCode(), Code.DELETE_SUCCESS.getMsg(), null);
        }
    }

    /**
     * 更新行数如果为null或者0则表示更新失败
     *
     * @param row 行数
     * @return ResultList
     */
    public static ResultList verifyUpdate(Integer row) {
        if (null == row || row == 0) {
            return new ResultList(Code.UPDATE_NULL.getCode(), Code.UPDATE_NULL.getMsg(), null);
        } else {
            return new ResultList(Code.UPDATE_SUCCESS.getCode(), Code.UPDATE_SUCCESS.getMsg(), null);
        }
    }

    /**
     * 添加行数如果为null或者0则表示更新失败
     *
     * @param row 行数
     * @return ResultList
     */
    public static ResultList verifyAdd(Integer row) {
        if (null == row || row == 0) {
            return new ResultList(Code.ADD_FAIL.getCode(), Code.ADD_FAIL.getMsg(), null);
        } else {
            return new ResultList(Code.ADD_SUCCESS.getCode(), Code.ADD_SUCCESS.getMsg(), null);
        }
    }

    /**
     * 更新行数如果为null或者0则表示更新失败
     *
     * @param row 行数
     * @return ResultList
     */
    public static ResultList verifyQuery(List<?> list, Integer row) {
        if (list.isEmpty()) {
            return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(), null);
        } else {
            if (null == row || row == 0) {
                return new ResultList(Code.EXECUTION_ERROR.getCode(), Code.EXECUTION_ERROR.getMsg(), null);
            } else {
                return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
            }
        }
    }

    /**
     * 参数不正确
     *
     * @param message 消息
     * @return ResultList
     */
    public static ResultList paramsError(String message) {
        return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), message, null);
    }

    /**
     * 无数据
     *
     * @param
     * @return ResultList
     */
    public static ResultList queryNull() {
        return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(), null);
    }


    public static ResultList resp(Code code) {
        return new ResultList(code.getCode(), code.getMsg(), null);
    }

    public static ResultList resp(Code code, Result result) {
        return new ResultList(code.getCode(), code.getMsg(), result);
    }


    public static ResultList resp(Code code, String msg) {
        return new ResultList(code.getCode(), msg, null);
    }

    /**
     * resp obj
     *
     * @param code
     * @param data
     * @return
     */
    public static ResultList respObj(Code code, Object data) {
        return new ResultList(code.getCode(), code.getMsg(), new Result<>(1, data));
    }

    /**
     * resp list
     *
     * @param code
     * @param data
     * @return
     */
    public static ResultList respList(Code code, Object data) {
        List ret = Arrays.asList(data);
        return new ResultList(code.getCode(), code.getMsg(), new Result<>(1, ret));
    }


    /**
     * resp list
     *
     * @param code
     * @param data
     * @return
     */
    public static ResultList respList(Code code, List data) {
        return new ResultList(code.getCode(), code.getMsg(), new Result<>(data.size(), data));
    }

    /**
     * resp分页
     *
     * @param code
     * @param row  total总数
     * @param data
     * @return
     */
    public static ResultList respPage(Code code, Integer totalRow, Object data) {
        return new ResultList(code.getCode(), code.getMsg(), new Result<>(totalRow, data));
    }

    /**
     * resp分页。自动计算total大小
     *
     * @param code
     * @param data
     * @return
     */
    public static ResultList respPage(Code code, List data) {
        return new ResultList(code.getCode(), code.getMsg(), new Result<>(Math.toIntExact(((Page) data).getTotal()), data));
    }
}
