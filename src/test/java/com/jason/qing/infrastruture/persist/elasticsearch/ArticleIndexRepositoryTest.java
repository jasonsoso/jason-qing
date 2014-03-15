package com.jason.qing.infrastruture.persist.elasticsearch;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;


public class ArticleIndexRepositoryTest  extends AbstractTestBase {
	
	private static Logger logger = LoggerFactory.getLogger(ArticleIndexRepositoryTest.class);

    @Autowired
    private ArticleIndexRepository articleIndexRepository;

    @Before
    public void deleteAll(){
    	logger.info("test deleteAll.......");
    	articleIndexRepository.deleteAll();
    }

    @Test
    public void testIndex(){

        Article article = new Article();
        article.setId(1);
        article.setTitle("骇人听闻的给幼儿喂药事件如何发生");
        article.setSummary("这个说法让不少网友感到疑惑，为什么幼儿缺勤，幼儿园就要给家长退费呢？从多数人自身上学的经验来看，旷课缺勤是从来没有退费这一说法的。");

        articleIndexRepository.index(article);
        Article indexedArticle = articleIndexRepository.get(1);
        Assert.assertEquals(article.getTitle(), indexedArticle.getTitle());
    }
    @Test
    public void testIndexList(){
    	List<Article> list = new ArrayList<Article>();
    	
        Article article1 = new Article();
        article1.setId(1);
        article1.setTitle("骇人听闻的给幼儿喂药事件如何发生");
        article1.setSummary("这个说法让不少网友感到疑惑，为什么幼儿缺勤，幼儿园就要给家长退费呢？从多数人自身上学的经验来看，旷课缺勤是从来没有退费这一说法的。");
        list.add(article1);
        
        Article article2 = new Article();
        article2.setId(2);
        article2.setTitle("CNN：马航失联客机可能在孟加拉湾或印度洋坠毁");
        article2.setSummary("人民网纽约3月14日电 据CNN报道，一份机密的电子卫星数据分析报告显示，马来西亚航空公司航班MH370有可能在孟加拉湾或印度洋海域坠毁。");
        list.add(article2);
        
        Article article3 = new Article();
        article3.setId(3);
        article3.setTitle("北京正式申办2022年冬奥会");
        article3.setSummary("国际在线消息：马航客机MH370失联进入第八天，搜寻区域继续扩大至印度洋安达曼海；叙利亚危机爆发三周年，巴沙尔探访大马士革郊区难民，叙局势恢复稳定仍需时日；克里米亚公投在即，美俄外长谈判未取得实质进展；报告称近5成民众怕麻烦不索赔，专家建议从五个方面维护金融消费者权益；北京正式申办2022年冬奥会，环球资讯评论员分析申奥前景。详情请收听本期《第一资讯》！");
        list.add(article3);
        
        Article article4 = new Article();
        article4.setId(4);
        article4.setTitle("央行叫停二维码支付业务 将会影响谁");
        article4.setSummary("央行叫停二维码支付业务，暂停二维码支付影响谁？砍掉O2O关键一环，O2O如今已是阿里、腾讯、京东等公司重要的战略，暂停二维码支付主要影响线下业务，包括：线下扫码、线下收款及付款环节。线上二维码支付不受影响；牵涉企业：腾讯+阿里。");
        list.add(article4);
        
        articleIndexRepository.index(list);
        
        Page<Article> page = new Page<Article>();
        articleIndexRepository.queryPage(page, "*");
        Assert.assertEquals(4L, page.getTotalCount());
    }
    /*
    @Test
    public void testSearch(){
    	Page<ArticleIndex> page = articleIndexRepository.findAll(new PageRequest(0, 10));
    	for (ArticleIndex  ai : page) {
			logger.info("--1------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    	
    	
    	
    	QueryBuilder queryBuilder = QueryBuilders.termQuery("title","中国人");
    	
    	Page<ArticleIndex> ArticleIndexs =  articleIndexRepository.search(queryBuilder,new PageRequest(0,20));
    	for (ArticleIndex  ai : ArticleIndexs) {
			logger.info("--2------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    	
    	QueryBuilder queryBuilder2 = QueryBuilders.queryString("中国人");
    	
    	Page<ArticleIndex> ArticleIndexss =  articleIndexRepository.search(queryBuilder2,new PageRequest(0,20));
    	for (ArticleIndex  ai : ArticleIndexss) {
			logger.info("--3------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    }*/
}
