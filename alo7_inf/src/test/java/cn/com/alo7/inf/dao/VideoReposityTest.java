package cn.com.alo7.inf.dao;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.alo7.inf.AloApplicationStarterTests;
import cn.com.alo7.inf.repository.VideoFullViewRepository;

/**
 * 作品service单元测试
 * @author mazan
 *
 */
public class VideoReposityTest extends AloApplicationStarterTests {

    private final static Logger logger = LoggerFactory.getLogger(VideoReposityTest.class);
    
    @Autowired
    private VideoFullViewRepository videoFullDao;
    /**
     * object[] o; -->List,
     * --> List<Long> list = o[0];
     */
    @Test
    public void getVideoCountTest(){
    	Long albumId = 1L;
    	List<Map<String, Object>> list = this.videoFullDao.findVideoAndWorkCount(albumId);
    	System.out.println("--------------");
    	
    	
    	System.out.println(list.size());
    	
    	System.out.println(list.get(0));
    	
    }
}
