package cn.com.alo7.inf.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
/**
 * 专辑controller测试
 * @author mazan
 *
 */
public class AlbumControllerTest extends BaseControllerTest {

	/**
	 * 测试A10
	 * @throws Exception 
	 */
	@Test
	public void testGetCommonlyAlbumWorkList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/albums/works")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("albumSize", "10")
                .param("videoSize", "6")
                .accept(MediaType.APPLICATION_JSON))
			//请求返回结果200
            .andExpect(MockMvcResultMatchers.status().isOk())
            //打印response
            .andDo(MockMvcResultHandlers.print())
            //预期body返回结果
//            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")))
//            .andReturn()
            ;
	}
}
