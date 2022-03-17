package ru.msu.cmc.webprak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.utils.DAOFactory;

import java.util.List;

@SpringBootApplication
public class WebPrakApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPrakApplication.class, args);

		List<Person> foltestName = DAOFactory.getInstance().getPersonDAO().getPersonByName("Фольтест");
		System.out.println(foltestName);
	}

}
