package bsm.choi.fancafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Auditing 기능을 위한 어노테이션
@EnableCaching // Redis 캐싱을 위해 추가, @Cacheable 같은 어노테이션을 인식함
public class FancafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FancafeApplication.class, args);
	}

}
