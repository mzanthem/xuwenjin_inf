package cn.com.alo7.inf.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	
    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor());
		interceptorRegistration.addPathPatterns("/**");
    }
}
