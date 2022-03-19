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

		List<Person> fionaName = DAOFactory.getInstance().getPersonDAO().getPersonByName("Фиона");
		if (fionaName != null) {
			for (var person : fionaName) {
				System.out.println(person.toString());
			}
		}
	}
}
