package web.application.com.middleware.elasticsearch;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import web.application.com.common.constans.CommonConst;

/**
 * ES的操作数据类
 * 
 * 备注：对es的一些操作做了一些封装，抽出来一些操作，就是传统的dao层，数据服务
 * 
 * @author sdc
 *
 */
@Component
public class EslaticSearchTemplet {

	
	private static Logger log = LoggerFactory.getLogger(EslaticSearchTemplet.class);

	@Autowired
    private TransportClient client;
    
    
	/**
	 * 暂时用不到，配置了全局
	 */
	@SuppressWarnings("resource")
//	@Before
	public void getClient() {
		try {
	        //设置集群名称
	        Settings settings = Settings.builder().put("cluster.name", "my-application").build();// 集群名
	        //创建client
			client  = new PreBuiltTransportClient(settings)
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(CommonConst.IP), CommonConst.PORT));
		} catch (UnknownHostException e) {
			log.error("es 创建链接失败...", e);
		}
	}
    
    

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
//    @Test
    public void buildIndex() {
    	/*String index
        if (!isIndexExist(index)) {
            log.info("Index is not exits!");
        }*/
        CreateIndexResponse buildIndexresponse = client.admin().indices().prepareCreate(CommonConst.ES_INDEX_BUDGET_DELTA).execute().actionGet();
        log.info(" 创建索引的标志: " + buildIndexresponse.isAcknowledged());

//        return buildIndexresponse.isAcknowledged();
    }
    
    /**
	 *  创建索引并添加文档
	 * @throws Exception
	 */
//	@Test
	public void addIndexAndDocument(String index, String type, String id, String offset, String key, String value, String partition) throws Exception{
				
		IndexResponse response = client.prepareIndex(index, type)
		.setSource(XContentFactory.jsonBuilder().startObject()
	    .field("id", id)
		.field("offset",offset)
		.field("key",key)
		.field("value",value)
		.field("partition",partition)
		.endObject())
		.get();
		System.out.println("添加索引成功,版本号："+response.getVersion());
	}
    
	
	
	
	/**
     * 添加数据
     *
     * @param data  添加的数据类型 json格式的
     * @param index 索引<----->关系型数据库
     * @param type  类型<----->关系型数据表
     * @param id    数据ID<----->id
     * @return
     */
    public String addTargetDataALL(JSONObject data, String index, String type, String id) {
    	
    	log.info("start add onece index , this index name is :" + index );
        //判断一下次id是否为空，为空的话就设置一个id
        if(id == null) {
            id = UUID.randomUUID().toString();
        }
        //正式添加数据进去
        IndexResponse response = client.prepareIndex(index, type, id).setSource(data).get();

        log.info("addTargetDataALL 添加数据的状态:{}", response.status().getStatus());

        return response.getId();
    }
    

     /**
     * 删除索引
     *
     * @param index
     * @return
     */
    public boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            log.info(" 索引不存在 ！！！！！!");
        }
        DeleteIndexResponse diResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (diResponse.isAcknowledged()) {
            log.info("删除索引**成功** index->>>>>>>" + index);
        } else {
            log.info("删除索引**失败** index->>>>> " + index);
        }
        return diResponse.isAcknowledged();
    }

    /**
     * 查询数据
     * @param index 索引<----->关系型数据库
     * @param type  类型<----->关系型数据表
     * @param id    数据ID<----->id
     * @return
     */
    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            log.info(" 无法查询数据，缺唯一值!!!!!!! ");
            return null;
        }
        //来获取查询数据信息
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        GetResponse getResponse = getRequestBuilder.execute().actionGet(); 
        //这里也有指定的时间获取返回值的信息，如有特殊需求可以

        return getResponse.getSource();
    }

    /**
     * 更新数据
     *
     * @param data  添加的数据类型 json格式的
     * @param index 索引<----->关系型数据库
     * @param type  类型<----->关系型数据表
     * @param id    数据ID<----->id
     * @return
     */
    public void updateDataById(JSONObject data, String index, String type, String id) {
        if(index == null || type == null || id == null) {
            log.info(" 无法更新数据，缺唯一值!!!!!!! ");
            return;
        }

        //更新步骤
        UpdateRequest up = new UpdateRequest();
        up.index(index).type(type).id(id).doc(data);

        //获取响应信息
        //.actionGet(timeoutMillis)，也可以用这个方法，当过了一定的时间还没得到返回值的时候，就自动返回。
        UpdateResponse response = client.update(up).actionGet();
        log.info("更新数据状态信息，status{}", response.status().getStatus());
    }

    

    /**
     * 通过ID删除数据
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     * @param id    数据ID
     */
    public void delDataById(String index, String type, String id) {

        if(index == null || type == null || id == null) {
            log.info(" 无法删除数据，缺唯一值!!!!!!! ");
            return;
        }
        //开始删除数据
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();

        log.info("删除数据状态，status-->>>>{},", response.status().getStatus());
    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist(String index) {
        IndicesExistsResponse iep = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        if (iep.isExists()) {
            log.info("此索引 [" + index + "] 已经在ES集群里存在");
        } else {
            log.info(" 没有此索引 [" + index + "] ");
        }
        return iep.isExists();
    }

}
