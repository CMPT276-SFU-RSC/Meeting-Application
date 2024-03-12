package group9.sfursmeetingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // Spring Framework annotation for enabling asynchronous method execution (For sending emails)
public class SfursmeetingapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfursmeetingapplicationApplication.class, args);
	}

}
