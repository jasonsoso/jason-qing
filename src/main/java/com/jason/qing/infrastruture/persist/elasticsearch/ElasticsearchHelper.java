package com.jason.qing.infrastruture.persist.elasticsearch;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;

/**
 * Elasticsearch 帮助类
 * @author Jason
 * @date 2014年3月16日 上午11:59:36
 */
public class ElasticsearchHelper {

	/**
	 * 判断索引是否存在
	 * @param client 客户端
	 * @param index 索引名称
	 * @return
	 */
	public static boolean isExistsIndex(Client client, String index) {
		IndicesExistsRequest request = new IndicesExistsRequestBuilder(client
				.admin().indices(), index).request();
		IndicesExistsResponse response = client.admin().indices()
				.exists(request).actionGet();
		return response.isExists();
	}

}
