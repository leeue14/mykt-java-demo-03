package leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "leeue.repository")
public class AppSharding2 {

    public static void main(String[] args) {
        SpringApplication.run(AppSharding2.class, args);
    }

}
