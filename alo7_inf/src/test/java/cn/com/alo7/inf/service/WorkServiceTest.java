package cn.com.alo7.inf.service;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.alo7.inf.entity.Work;

/**
 * 作品service单元测试
 * @author mazan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkServiceTest {

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
