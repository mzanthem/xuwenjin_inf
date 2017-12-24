package cn.com.alo7.inf.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 * http://localhost:8080/swagger-ui.html
 * @author mazan
 *
 */
@Configuration
@EnableSwagger2
public class AppConfiguration {
    /**
     * swagger文档生成配置
     * @return
     */
    @SuppressWarnings("unchecked")
    @Bean
    public Docket rpcApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("rpcapi")
        		
        		.genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/.*")))// 过滤的接口
                .build()
                .apiInfo(rpcApiInfo());
    }

    private ApiInfo rpcApiInfo() {
		return new ApiInfoBuilder()
				.title("ALO REST API")
				.description("爱乐奇REST API")
				.termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
//				.contact(new Contact("zan.ma", "", "mazanxzyz@163.com"))
				.version("1.0")
				.build();
	    	
    }
}
