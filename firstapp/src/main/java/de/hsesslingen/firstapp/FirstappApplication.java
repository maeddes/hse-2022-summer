package de.hsesslingen.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstappApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String mainEntrypoint(){

		return "default page";
	}

	@GetMapping("/test")
	@ResponseBody
	public String testMethod(){

		return "successful";
	}

	@GetMapping("/hello")
	public String sayHello(){

		return "Hello Esslingen, Summer Semester 22";
	}

	@GetMapping("/echo/{value}")
	public String echoValue(@PathVariable String value){

		return "echo "+value;
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstappApplication.class, args);
	}

}
