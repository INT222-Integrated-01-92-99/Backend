package sit.int221.ppclothes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sit.int221.ppclothes.services.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class PpclothesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpclothesApplication.class, args);
	}

}
