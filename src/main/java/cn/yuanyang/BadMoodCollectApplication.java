package cn.yuanyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.yuanyang.mapper")
public class BadMoodCollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadMoodCollectApplication.class, args);
	}

}
