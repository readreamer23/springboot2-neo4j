package com.modules.project.controller;

import com.modules.common.utils.Neo4jUtil;
import com.modules.common.web.BaseController;
import com.modules.common.web.Result;
import com.modules.project.entity.GraphQuery;
import com.modules.project.entity.QAEntityItem;
import com.modules.project.service.GraphService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * neo4j图数据管理接口
 *
 */

@Api(tags = "neo4j图数据管理")
@Slf4j
@RestController
@RequestMapping("/graph")
public class GraphController extends BaseController {

    @Autowired
    private Neo4jUtil neo4jUtil;

    @Autowired
    private GraphService graphService;

    @ApiOperation(value = "执行Cypher查询", notes = "执行Cypher查询")
    @GetMapping(value = "/getCypherResult")
    public Result getCypherResult(String cypher) {
        try {
            HashMap<String, Object> graphData = neo4jUtil.getGraphNodeAndShip(cypher);
            return success(graphData);
        } catch (Exception e) {
            log.error("执行Cypher查询异常：" + e.getMessage());
           return error("执行Cypher查询异常");
        }
    }

    @ApiOperation(value = "查询图节点和关系", notes = "查询图节点和关系")
    @GetMapping(value = "/getDomainGraph")
    public Result getDomainGraph(@Validated GraphQuery query) {
        try {
            HashMap<String, Object> graphData = graphService.getDomainGraph(query);
            return success(graphData);
        } catch (Exception e) {
            log.error("查询图节点和关系异常：" + e.getMessage());
           return error("查询图节点和关系异常");
        }
    }

    @ApiOperation(value = "获取节点列表", notes = "获取节点列表")
    @GetMapping(value = "/getDomainNodes")
    public Result getDomainNodes(String domain, Integer pageIndex, Integer pageSize) {
        try {
            HashMap<String, Object> graphData = graphService.getDomainNodes(domain, pageIndex,pageSize);
            return success(graphData);
        } catch (Exception e) {
            log.error("获取节点列表异常：" + e.getMessage());
            return error("获取节点列表异常");
        }
    }

    @ApiOperation(value = "查询所有节点标签", notes = "查询所有的节点标签")
    @GetMapping(value = "/getNodesLabels")
    public Result getNodesLabels() {
        try {
            List<String> graphData = graphService.getNodesLabels();
            return success(graphData);
        } catch (Exception e) {
            log.error("查询所有节点标签异常：" + e.getMessage());
            return error("查询所有节点标签异常");
        }
    }

    @ApiOperation(value = "查询所有关系类型", notes = "查询所有关系类型")
    @GetMapping(value = "/getRelationshipType")
    public Result getRelationshipType() {
        try {
            List<String> graphData = graphService.getRelationshipType();
            return success(graphData);
        } catch (Exception e) {
            log.error("查询所有关系类型异常：" + e.getMessage());
            return error("查询所有关系类型异常");
        }
    }

    @ApiOperation(value = "创建节点", notes = "创建节点")
    @GetMapping(value = "/createNode")
    public Result createNode(String domain, String name) {
        try {
            QAEntityItem entity=new QAEntityItem();
            entity.setName(name);
            HashMap<String, Object> hashMap=graphService.createNode(domain,entity);
            return success(hashMap);
        } catch (Exception e) {
            log.error("创建节点异常：" + e.getMessage());
            return error("创建节点异常");
        }
    }





}
