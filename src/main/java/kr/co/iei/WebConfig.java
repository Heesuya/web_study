package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Value("${file.root}")
	private String root;

	@Override//이제 수동 설정할꺼야
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry     // * : 모든 , ** : 몇개있더라도 모두
			.addResourceHandler("/**")
			.addResourceLocations("classpath:/templates/","classpath:/static/"); //우선순위 적용 됨, 먼저 다 찾아봐~
		
		registry
			.addResourceHandler("/board/**") 
			.addResourceLocations("file:///"+root+"/board/");
			}
	
	@Bean
	public BCryptPasswordEncoder bcrypt() { //어노테이션 쓸수 없는 라이브러리는 @Bean 이라는 어노테이션으로 강제로 객체를 만들면 된다. 
		return new BCryptPasswordEncoder();
	}


}
