package cn.yu.cartoon.controller;

import cn.yu.cartoon.pojo.dto.Combo;
import cn.yu.cartoon.pojo.vo.BaseResultHelper;
import cn.yu.cartoon.service.ComboService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 商品套餐设置的接口
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 14:14
 **/
@Controller
public class ComboController {

    Logger logger = LoggerFactory.getLogger(ComboController.class);

    private final ComboService comboService;

    @Autowired
    public ComboController(ComboService comboService) {
        this.comboService = comboService;
    }

    @ApiOperation("套餐信息的提交设置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "integer", name = "packagePrice", value = "套餐的售价", required = true),
            @ApiImplicitParam(paramType = "byte", name = "packageType", value = "套餐充值的类型（金币还是vip天数）", required = true),
            @ApiImplicitParam(paramType = "integer", name = "packageCurrency", value = "充进的金币数", required = true),
            @ApiImplicitParam(paramType = "integer", name = "vipDay", value = "充值的vip天数", required = true)
    })
    @PostMapping(value = "/combo")
    @ResponseBody
    public BaseResultHelper<Combo> setCombo(Combo combo, HttpServletRequest request) {

        return null;
    }

    @ApiOperation("获得套餐优惠信息")
    @GetMapping(value = "/combo/{comboId}")
    @ResponseBody
    public BaseResultHelper getCombo(@PathVariable Integer comboId) {

        BaseResultHelper<Combo> resultHelper = new BaseResultHelper<>();
        Combo combo = comboService.getComboById(comboId);
        if (null != combo) {
            resultHelper.setCode("SUCCESS");
            resultHelper.setMsg("成功获取套餐信息");
            resultHelper.setData(combo);
            return resultHelper;
        } else {
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("没有找到该套餐");
            return resultHelper;
        }
    }
}
