package com.kiwihouse.controller.resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.entity.AuthResource;
import com.kiwihouse.domain.vo.MenuTreeNode;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.ResourceService;
import com.kiwihouse.shiro.filter.FilterChainManager;
import com.kiwihouse.util.TreeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   资源URL管理
 * @author tomsun28
 * @date 21:36 2018/3/17
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "资源管理")
public class ResourceController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    Map<String, Object> map = new HashMap<String, Object>();
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private FilterChainManager filterChainManager;
    @ApiOperation(value = "获取用户被授权菜单",notes = "通过uid获取对应用户被授权的菜单列表,获取完整菜单树形结构")
    @GetMapping("authorityMenu")
    public Response getAuthorityMenu(HttpServletRequest request) {
        String uid = request.getHeader("appId");
        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<AuthResource> resources = resourceService.getAuthorityMenusByUid(uid);

        for (AuthResource resource : resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Response().Success(6666,"return menu list success").addData("menuTree",menuTreeNodes);
    }

    @ApiOperation(value = "获取全部菜单列", httpMethod = "GET")
    @GetMapping("menus")
    public Response getMenus() {

        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<AuthResource> resources = resourceService.getMenus();

        for (AuthResource resource: resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Response().Success(6666,"return menus success").addData("menuTree",menuTreeNodes);
    }

    @ApiOperation(value = "增加菜单",httpMethod = "POST")
    @PostMapping("menu")
    public Response addMenu(@RequestBody AuthResource menu ) {

        Boolean flag = resourceService.addMenu(menu);
        if (flag) {
        	filterChainManager.reloadFilterChain();
            return new Response().Success(Code.ADD_SUCCESS,Code.ADD_SUCCESS.getMsg());
        } else {
            return new Response().Fail(Code.ADD_FAIL,Code.ADD_FAIL.getMsg());
        }
    }

    @ApiOperation(value = "修改菜单",httpMethod = "PUT")
    @PutMapping("menu")
    public Response updateMenu(@RequestBody AuthResource menu) {

        Boolean flag = resourceService.modifyMenu(menu);
        if (flag) {
        	filterChainManager.reloadFilterChain();
            return new Response().Success(Code.UPDATE_SUCCESS,Code.UPDATE_SUCCESS.getMsg());
        } else {
            return new Response().Fail(Code.UPDATE_FAIL, Code.UPDATE_FAIL.getMsg());
        }
    }

    @ApiOperation(value = "删除菜单", notes = "根据菜单ID删除菜单", httpMethod = "DELETE")
    @DeleteMapping("menu/{menuIds}")
    public Response deleteMenuByMenuId(@PathVariable String menuIds) {

        Boolean flag = resourceService.deleteBatch(menuIds);
        if (flag) {
        	filterChainManager.reloadFilterChain();
            return new Response().Success(Code.DELETE_SUCCESS, Code.DELETE_SUCCESS.getMsg());
        } else {
            return new Response().Fail(Code.DELETE_FAIL, Code.DELETE_FAIL.getMsg());
        }
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取API list", notes = "需要分页,根据teamId判断,-1->获取api分类,0->获取全部api,id->获取对应分类id下的api",httpMethod = "GET")
    @GetMapping("api/{teamId}/{currentPage}/{pageSize}")
    public Response getApiList(@PathVariable Integer teamId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {

        List<AuthResource> resources = null;
        if (teamId == -1) {
            // -1 为获取api分类
            resources = resourceService.getApiTeamList();
            return new Response().Success(6666,"return apis success").addData("data",resources);
        }
        PageHelper.startPage(currentPage, pageSize);
        if (teamId == 0) {
            // 0 为获取全部api
            resources = resourceService.getApiList();
        } else {
            // 其他查询teamId 对应分类下的apis
            resources = resourceService.getApiListByTeamId(teamId);
        }
        PageInfo pageInfo = new PageInfo(resources);
        return new Response().Success(6666,"return apis success").addData("data",pageInfo);
    }

    @ApiOperation(value = "增加API",httpMethod = "POST")
    @PostMapping("api")
    public Response addApi(@RequestBody AuthResource api ) {

        Boolean flag = resourceService.addMenu(api);
        if (flag) {
            return new Response().Success(6666,"add api success");
        } else {
            return new Response().Fail(1111,"add api fail");
        }
    }

    @ApiOperation(value = "修改API",httpMethod = "PUT")
    @PutMapping("api")
    public Response updateApi(@RequestBody AuthResource api) {

        Boolean flag = resourceService.modifyMenu(api);
        if (flag) {
            return new Response().Success(6666,"update api success");
        } else {
            return new Response().Fail(1111, "update api fail");
        }
    }

    @ApiOperation(value = "删除API", notes = "根据API_ID删除API", httpMethod = "DELETE")
    @DeleteMapping("api/{apiId}")
    public Response deleteApiByApiId(@PathVariable Integer apiId) {

        Boolean flag = resourceService.deleteMenuByMenuId(apiId);
        if (flag) {
            return new Response().Success(6666, "delete api success");
        } else {
            return new Response().Fail(1111, "delete api fail");
        }
    }
    
    @SuppressWarnings("unused")
	@ApiOperation(value = "查询权限", notes = "权限操作", httpMethod = "GET")
    @GetMapping("auth/select")
    public Map<String, Object> list(Integer page, Integer limit,Integer roleId,AuthResource auResource) {
    	try {
    		if(auResource==null) {
    			auResource = new AuthResource();
    		}
    		map = resourceService.selectPage(page,limit,roleId,auResource);
    		map.put("code", 0);
    		map.put("msg",Code.QUERY_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
        return map;
    }
    
    @ApiOperation(value = "查询静态资源列表", notes = "根据type=1", httpMethod = "GET")
    @GetMapping("/static")
    public Response selectStaticResource() {
        return resourceService.selectStaticResource();
    }
}
