package kr.co.iei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})//시큐리티 설정 변경
public class WebStudyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebStudyApplication.class, args);
	}

}
