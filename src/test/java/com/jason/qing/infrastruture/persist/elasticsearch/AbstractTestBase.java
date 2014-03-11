package com.jason.qing.infrastruture.persist.elasticsearch;



import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

	    
@ContextConfiguration(locations = {"classpath:/META-INF/spring/application-root.xml",
								   "classpath:/META-INF/spring/application-elasticsearch.xml"})
public class AbstractTestBase extends AbstractTransactionalJUnit4SpringContextTests{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static MockHttpServletRequest request;

	protected static MockHttpServletResponse response;

	@Before
	public void setUp() {
		initMockData();
	}
	protected void initMockData() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
}
