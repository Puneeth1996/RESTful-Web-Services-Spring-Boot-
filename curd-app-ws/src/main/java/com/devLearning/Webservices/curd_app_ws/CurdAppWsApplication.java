package com.devLearning.Webservices.curd_app_ws;

import com.devLearning.Webservices.curd_app_ws.io.entity.UserEntity;
import com.devLearning.Webservices.curd_app_ws.io.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("package com.devLearning.Webservices.curd_app_ws.io.repository")
public class CurdAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdAppWsApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UserRepository repository) {
//		return (args) -> {
//			// save a few customers
//			repository.save(new UserEntity("12juk13h1u",
//					"pureeeee",
//					"tes",
//					"test@test.com",
//					"12juk13h1u",
//					"12juk13h1u"));
//
//		};
//	}


//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
