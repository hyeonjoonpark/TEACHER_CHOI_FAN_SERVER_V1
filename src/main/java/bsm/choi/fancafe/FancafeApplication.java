package bsm.choi.fancafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FancafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FancafeApplication.class, args);
	}

}
