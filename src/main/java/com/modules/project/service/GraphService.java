package com.modules.project.service;

import com.modules.common.utils.StringUtils;
import com.modules.project.entity.GraphQuery;
import com.modules.project.entity.QAEntityItem;
import com.modules.project.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * neo4j图库服务层
 *
 *@author lihui
 */

@Service
public class GraphService {

    @Autowired
    private GraphRepository graphRepository;

    /**
     * 查询图节点和关系
     *
     * @param query
     * @return
     */
    public HashMap<String, Object> getDomainGraph(GraphQuery query) {
        if(StringUtils.isNotEmpty(query.getDomain())){
            return graphRepository.getDomainGraph(query);
        }else{
            return graphRepository.getNodeNameGraph(query);
        }
    }

    /**
     * 获取节点列表
     * @param domain
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public HashMap<String, Object> getDomainNodes(String domain, Integer pageIndex, Integer pageSize){
        return graphRepository.getDomainNodes(domain, pageIndex, pageSize);
    }

    /**
     * 查询所有节点标签
     * @return
     */
    public List<String> getNodesLabels(){
        return graphRepository.getNodesLabels();
    }

    /**
     * 查询所有关系类型
     * @return
     */
    public List<String> getRelationshipType(){
        return graphRepository.getRelationshipType();
    }

    public HashMap<String, Object>  createNode(String domain, QAEntityItem entity){
        return graphRepository.createNode(domain,entity);
    }






}



