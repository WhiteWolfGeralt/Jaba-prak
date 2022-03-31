package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulations() {
        List<Person> personListAll = (List<Person>) personDAO.getAll();
        assertEquals(6, personListAll.size());

        List<Person> geraltQuery = personDAO.getAllPersonByName("Геральт");
        assertEquals(1, geraltQuery.size());
        assertEquals("Геральт из Ривии", geraltQuery.get(0).getName());

        Person personId3 = personDAO.getById(3L);
        assertEquals(3, personId3.getId());

        Person personNotExist = personDAO.getById(100L);
        assertNull(personNotExist);
    }

    @Test
    void testUpdate() {
        Long birth = 1220L, death = 1260L;

        Person updatePerson = personDAO.getSinglePersonByName("Вираксас");
        updatePerson.setBirth(birth);
        updatePerson.setDeath(death);
        personDAO.update(updatePerson);

        Person viraxas = personDAO.getSinglePersonByName("Вираксас");
        assertEquals(birth, viraxas.getBirth());
        assertEquals(death, viraxas.getDeath());
    }

    @Test
    void testDelete() {
        Person deletePerson = personDAO.getSinglePersonByName("Йеннифэр");
        personDAO.delete(deletePerson);

        Person yennifer = personDAO.getSinglePersonByName("Йеннифэр");
        assertNull(yennifer);
    }

    @BeforeEach
    void beforeEach() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(123L, "Король Белогун", "Муж", null, 1245L, "Король Керака"));
        personList.add(new Person(null, "Илдико Брекл", "Жен", 1220L, null, "Чародейка-недоучка, интриганка. Королева Керака"));
        personList.add(new Person("Вираксас I", "Муж", "Принц, позже - король Керака"));

        personList.add(new Person("Альзур из Марибора", "Муж", "Могущественный и известный чародей, ученик Косимо Маласпины"));
        personList.add(new Person("Геральт из Ривии", "Муж", "Легендарный ведьмак. Профессиональный охотник на монстров, один из лучших фехтовальщиков Севера."));
        personList.add(new Person(null, "Йеннифэр из Венгерберга", "Жен",  1173L, null, "Tалантливая чародейка. Входила в состав последнего Совета Чародеев, позднее — в Ложу Чародеек."));
        personDAO.saveCollection(personList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE person_person_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
