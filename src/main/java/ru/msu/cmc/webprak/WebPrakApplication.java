package ru.msu.cmc.webprak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;
import ru.msu.cmc.webprak.utils.DAOFactory;

import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
public class WebPrakApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPrakApplication.class, args);

		var daoPerson = DAOFactory.getInstance().getPersonDAO();
		var daoRelation = DAOFactory.getInstance().getRelationDAO();
		/*
		List<Person> fionaName = daoPerson.getPersonByName("Фиона");
		if (fionaName != null) {
			for (var person : fionaName) {
				System.out.println(person.toString());
			}
		}*/
		List<Person> sonsMeve = daoRelation.getByRelType(daoPerson.getPersonByName("Мэва").get(0).getPersonId(), Relation.RelType.CHILD_IN_LAW, "target");
		if (sonsMeve != null) {
			for (var person : sonsMeve) {
				System.out.println(person.toString());
			}
		}
	}
}
