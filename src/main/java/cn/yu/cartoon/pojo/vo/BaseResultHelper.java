package cn.yu.cartoon.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回操作帮助信息
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/22 17:01
 **/
@ApiModel(value = "BaseResultHelper", description = "操作结果")
public class BaseResultHelper<T> {

    @ApiModelProperty(name = "code", notes = "操作是否成功", example = "SUCCESS")
    private String code;

    @ApiModelProperty(name = "msg", notes = "成功或错误的信息", example = "上传成功")
    private String msg;

    @ApiModelProperty(name = "data", notes ="返回的数据")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResultHelper{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
