package CGI.Application.JokeApp;

import CGI.Application.JokeApp.Services.xmlDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:chuck-config.xml")
public class JokeAppApplication {



	public static void main(String[] args) {
		ApplicationContext cntxt = SpringApplication.run(JokeAppApplication.class, args);
		xmlDemoService xmlSer = (xmlDemoService) cntxt.getBean("xmlDemoServiceImpl");
		System.out.println(xmlSer.checkXmlConfig());
	}

}
