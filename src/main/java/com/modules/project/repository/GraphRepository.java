package com.modules.project.repository;

import com.modules.project.entity.GraphPageRecord;
import com.modules.project.entity.GraphQuery;
import com.modules.project.entity.QAEntityItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 操作neo4j知识图谱Repository接口
 */
public interface GraphRepository {
	/**
	 * 领域标签分页
	 * @param queryItem
	 * @return
	 */
	GraphPageRecord<HashMap<String, Object>> getPageDomain(GraphQuery queryItem);
	/**
	 * 删除Neo4j 标签
	 *
	 * @param domain
	 */
	void deleteKGdomain(String domain);

	/**
	 * 查询图谱节点和关系
	 *
	 * @param query
	 * @return node relationship
	 */
	HashMap<String, Object> getDomainGraph(GraphQuery query);

	/**
	 * 查询图谱节点和关系（条件无节点标签）
	 *
	 * @param query
	 * @return node relationship
	 */
	HashMap<String, Object> getNodeNameGraph(GraphQuery query);

	/**
	 * 获取节点列表
	 *
	 * @param domain
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	HashMap<String, Object> getDomainNodes(String domain, Integer pageIndex, Integer pageSize);

	/**
	 * 获取某个领域指定节点拥有的上下级的节点数
	 *
	 * @param domain
	 * @param nodeid
	 * @return long 数值
	 */
	long getRelationNodeCount(String domain, long nodeid);

	/**
	 * 创建领域,默认创建一个新的节点,给节点附上默认属性
	 *
	 * @param domain
	 */
	void createDomain(String domain);

	/**
	 * 获取/展开更多节点,找到和该节点有关系的节点
	 *
	 * @param domain
	 * @param nodeid
	 * @return
	 */
	HashMap<String, Object> getMorerelationNode(String domain, String nodeid);

	/**
	 * 更新节点名称
	 *
	 * @param domain
	 * @param nodeid
	 * @param nodename
	 * @return 修改后的节点
	 */
	HashMap<String, Object> updateNodeName(String domain, String nodeid, String nodename);

	/**
	 * 创建单个节点
	 *
	 * @param domain
	 * @param entity
	 * @return
	 */
	HashMap<String, Object> createNode(String domain, QAEntityItem entity);

	/**
	 * 批量创建节点和关系
	 *
	 * @param domain
	 *            领域
	 * @param sourcename
	 *            源节点
	 * @param relation
	 *            关系
	 * @param targetnames
	 *            目标节点数组
	 * @return
	 */
	HashMap<String, Object> batchCreateNode(String domain, String sourcename, String relation, String[] targetnames);

	/**
	 * 批量创建下级节点
	 *
	 * @param domain
	 *            领域
	 * @param sourceid
	 *            源节点id
	 * @param entitytype
	 *            节点类型
	 * @param targetnames
	 *            目标节点名称数组
	 * @param relation
	 *            关系
	 * @return
	 */
	HashMap<String, Object> batchCreateChildCode(String domain, String sourceid, Integer entitytype,
			String[] targetnames, String relation);

	/**
	 * 批量创建同级节点
	 *
	 * @param domain
	 *            领域
	 * @param entitytype
	 *            节点类型
	 * @param sourcenames
	 *            节点名称
	 * @return
	 */
	List<HashMap<String, Object>> batchCreateSameCode(String domain, Integer entitytype, String[] sourcenames);

	/**
	 * 添加关系
	 *
	 * @param domain
	 *            领域
	 * @param sourceid
	 *            源节点id
	 * @param targetid
	 *            目标节点id
	 * @param ship
	 *            关系
	 * @return
	 */
	HashMap<String, Object> createLink(String domain, long sourceid, long targetid, String ship);

	/**
	 * 更新关系
	 *
	 * @param domain
	 *            领域
	 * @param shipid
	 *            关系id
	 * @param shipname
	 *            关系名称
	 * @return
	 */
	HashMap<String, Object> updateLink(String domain, long shipid, String shipname);

	/**
	 * 删除节点(先删除关系再删除节点)
	 *
	 * @param domain
	 * @param nodeid
	 * @return
	 */
	List<HashMap<String, Object>> deleteNode(String domain, long nodeid);

	/**
	 * 删除关系
	 *
	 * @param domain
	 * @param shipid
	 */
	void deleteLink(String domain, long shipid);

	/**
	 * 段落识别出的三元组生成图谱
	 *
	 * @param domain
	 * @param entitytype
	 * @param operatetype
	 * @param sourceid
	 * @param rss
	 *            关系三元组
	 *            [[startname;ship;endname],[startname1;ship1;endname1],[startname2;ship2;endname2]]
	 * @return node relationship
	 */
	HashMap<String, Object> createGraphByText(String domain, Integer entitytype, Integer operatetype, Integer sourceid,
			String[] rss);
	/**
	 * 批量创建节点，关系
	 * @param domain
	 * @param params 三元组 sourcenode,relationship,targetnode
	 */
	void batchCreateGraph(String domain, List<Map<String,Object>> params);
	/**
	 * 更新节点有无附件
	 * @param domain
	 * @param nodeId
	 * @param status
	 */
	void updateNodeFileStatus(String domain,long nodeId, int status);
	/**
	 * 导入csv
	 * @param domain
	 * @param csvUrl
	 * @param status
	 */
	void batchInsertByCSV(String domain, String csvUrl, int status) ;

	void updateCorrdOfNode(String domain, String uuid, Double fx, Double fy);

	/**
	 * 查询所有节点标签
	 * @return
	 */
	List<String> getNodesLabels();

	/**
	 * 查询所有关系类型
	 * @return
	 */
	public List<String> getRelationshipType();

	/**
	 * 查询节点关系种类
	 * @return
	 */
	public List<String> getNodesSchema();
}
