package com.jason.qing.infrastruture.persist.elasticsearch;

import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class EsTransportClientManagerFactoryBean 
	implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	private TransportClient transportClient;
	private Map<String, Integer> transportAddresses;
	
	public void setTransportAddresses(Map<String, Integer> transportAddresses) {
		this.transportAddresses = transportAddresses;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Initializing Elasticsearch TransportClient Manager");
		
		initElasticsearchTransport();
	}

	private void initElasticsearchTransport() {
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
	}

	

	@Override
	public TransportClient getObject() throws Exception {
		return this.transportClient;
	}

	@Override
	public Class<? extends TransportClient> getObjectType() {
		return (this.transportClient != null ? this.transportClient.getClass() : TransportClient.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public void destroy() throws Exception {
		logger.info("Shutting down Elasticsearch TransportClient Manager");
		transportClient.close();
	}

}