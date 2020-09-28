package com.kiwihouse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.ReportedDto;
import com.kiwihouse.service.DevHistoryDateService;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/history")
@Api(tags = "历史记录")
public class DevHistoryDateController extends BaseController{
	@Autowired
	DevHistoryDateService historyDateService;
	@ApiOperation(value = "queryInfo",
            notes = "<br>@description: <b>查询设备历史记录 ---》返回统计</b></br>" +
                    "<br>@Date: <b>2020-1-3 15:36:22</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = ReportedDto.class)})
    @GetMapping(value = "/info")
	public Response historyDevInfo(@Validated ReportedQueryVo reportedQueryVo) {
		
		return historyDateService.historyDevInfo(reportedQueryVo);
		
	}
	@ApiOperation(value = "selectAll",
            notes = "<br>@description: <b>查询设备历史记录</b></br>" +
                    "<br>@Date: <b>2020-1-3 15:36:22</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = ReportedDto.class)})
    @GetMapping(value = "/selectAll")
	public Map<String, Object> selectAll(@Validated ReportedQueryVo reportedQueryVo) {
		try {
			map = historyDateService.selectAll(reportedQueryVo);
			map.put("code", 0);
			map.put("msg",Code.QUERY_SUCCESS);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
		
		
		
		
	}
}
