package de.alpharogroup.lottery;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.lottery.config.ApplicationConfiguration;
import de.alpharogroup.lottery.config.SwaggerConfiguration;
import de.alpharogroup.spring.boot.application.ApplicationHooks;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = { "de.alpharogroup.lottery.service",
		"de.alpharogroup.lottery.jpa.entities", "de.alpharogroup.lottery.mapper" })
@EnableJpaRepositories(basePackages = {"de.alpharogroup.lottery.jpa.repositories"})
@EntityScan(basePackages = {"de.alpharogroup.lottery.jpa.entities"})
@EnableTransactionManagement
@Import({ ApplicationConfiguration.class, SwaggerConfiguration.class })
public class LotteryApplication
{

	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(LotteryApplication.class);
		ApplicationHooks.INSTANCE.addDatabaseIfNotExists(application, PathFinder.getSrcMainResourcesDir(),
			"application-dev.yml");
		application.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx)
	{
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames)
			{
				System.out.println(beanName);
			}

		};
	}

}