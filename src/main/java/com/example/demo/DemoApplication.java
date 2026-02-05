package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
    public CommandLineRunner demo(MemberRepository repository) {
        return (args) -> {
            // ここでデータを保存！
            repository.save(new Member("ケンタ"));
            repository.save(new Member("しげお"));
            System.out.println("データを保存したで！");
        };
    }

}
