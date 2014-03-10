package com.jason.qing.infrastruture.persist.elasticsearch;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class ElasticsearchTransportClient implements
	InitializingBean{
	//FactoryBean<TransportClient>, InitializingBean, DisposableBean
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String name;
	
	//private TransportClient transportClient;
	//private Map<String, Integer> transportAddresses;
	//
	//public void setTransportAddresses(Map<String, Integer> transportAddresses) {
	//	this.transportAddresses = transportAddresses;
	//}



	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.info("----------------------------111111111");
		System.out.println("------------------------22222222222");
		System.out.println("--------------------------------name:"+name);
		//if (null != transportAddresses) {
		//	for (final Entry<String, Integer> address : transportAddresses.entrySet()) {
		//		logger.info(String.format("Adding transport address:%s port:%s", address.getKey(),address.getValue()));
		//	}
		//}else{
		//	logger.error("Please Add transport address and port!");
		//}
		
		//initElasticsearchTransport();
	}

	/*private void initElasticsearchTransport() {
		final TransportClient client = new TransportClient();
		if (null != transportAddresses) {
			for (final Entry<String, Integer> address : transportAddresses.entrySet()) {
				client.addTransportAddress(new InetSocketTransportAddress(address.getKey(), address.getValue()));
				logger.info(String.format("Adding transport address:%s port:%s", address.getKey(),address.getValue()));
			}
		}else{
			logger.error("Please Add transport address and port!");
		}
		this.transportClient = client;
	}*/
/*
	@Override
	public void destroy() throws Exception {
		transportClient.close();
	}

	@Override
	public TransportClient getObject() throws Exception {
		return transportClient;
	}

	@Override
	public Class<TransportClient> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}*/

}