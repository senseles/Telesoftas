package lt.telesoft;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
	{
		return application.sources(Application.class);
	}

	@RequestMapping("/")
	public String getIndexPage(final Map<String, Object> model)
	{
		return "index";
	}

	@RequestMapping("/info")
	public String getInfoPage(final Map<String, Object> model)
	{
		return "info";
	}
}
