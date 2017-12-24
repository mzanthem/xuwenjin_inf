package cn.com.alo7.inf.service;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.alo7.inf.AloApplicationStarterTests;
import cn.com.alo7.inf.entity.Work;

/**
 * 作品service单元测试
 * @author mazan
 *
 */
public class WorkServiceTest extends AloApplicationStarterTests {

    private final static Logger logger = LoggerFactory.getLogger(WorkServiceTest.class);
    
    @Autowired
    private IWorkService workService;
    
    @Test
    public void getAllWorkTest(){
    	Long id = 1L;
    	Integer size = 20;
        List<Work> list = this.workService.findWorkByVideoId(id, size);
        logger.info(" "+list.size());
        list.forEach( e -> {
            logger.info(" "+ e);
        });
        assertTrue(!list.isEmpty());
    }
}
