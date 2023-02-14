package br.com.xavecoding.regescweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RegescwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegescwebApplication.class, args);

//		RestTemplate restTemplate = new RestTemplate(); //1
//		String url = "http://mockbin.org/bin/fd9103a1-81dc-4a5d-88c3-dd9f8eaf2661"; //2
//		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); //3
//
//		System.out.println(response);




	}

}
