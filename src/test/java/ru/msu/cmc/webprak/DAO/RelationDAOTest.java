package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class RelationDAOTest {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private RelationDAO relationDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testTarget() {
        List<Person> personsParents = relationDAO.getTargetByRelType(
                personDAO.getSinglePersonByName("Паветта"),
                Relation.RelType.CHILD_IN_LAW
        );

        assertEquals(2, personsParents.size());

        List<Person> personSpouse = relationDAO.getTargetByRelType(
                personDAO.getSinglePersonByName("Рёгнер"),
                Relation.RelType.SPOUSE_IN_LAW
        );

        assertEquals(1, personSpouse.size());
        assertEquals(personDAO.getSinglePersonByName("Калантэ").getId(), personSpouse.get(0).getId());
    }

    @Test
    void testPerform() {
        List<Person> personSpouse = relationDAO.getPerformByRelType(
                personDAO.getSinglePersonByName("Эйст"),
                Relation.RelType.SPOUSE_IN_LAW
        );

        assertEquals(1, personSpouse.size());
        assertEquals(personDAO.getSinglePersonByName("Калантэ").getId(), personSpouse.get(0).getId());

        List<Person> personsChild = relationDAO.getPerformByRelType(
                personDAO.getSinglePersonByName("Паветта"),
                Relation.RelType.CHILD_IN_LAW
        );

        assertEquals(1, personsChild.size());
        assertEquals(personDAO.getSinglePersonByName("Цирилла").getId(), personsChild.get(0).getId());
    }

    @BeforeEach
    void beforeEach() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(null, "Калантэ Фиона Рианнон", "Жен", 1218L, 1263L, "Королева Цинтры из рода Кербинов"));
        personList.add(new Person(null, "Эйст Турсеах", "Муж", null, 1262L, "Ярл и Конунг Скеллиге, король Цинтры"));
        personList.add(new Person(123L, "Рёгнер де Сальм", "Муж", 1211L, 1250L, "Король Цинтры. Не имеет королевского происхождения"));
        personList.add(new Person(32L, "Паветта Фиона Элен", "Жен", 1236L, 1256L, "Принцесса Цинтры. Носитель гена Старшей крови"));
        personList.add(new Person(null, "Цирилла Фиона Элен Рианнон", "Жен", 1252L, null, "Дитя Старшей Крови, потомок Лары Доррен. Единственный человек, который не будучи подвергнут мутациям, всё же заслужил право называться ведьмаком за свои способности"));
        personList.add(new Person(null, "Эмгыр вар Эмрейс", "Муж", 1220L, 1290L, "Император Нильфгаарда. Белое Пламя, Пляшущее на Курганах Врагов"));
        personDAO.saveCollection(personList);

        List<Relation> relationList = new ArrayList<>();
        {
            relationList.add(new Relation(
                    null,
                    personDAO.getSinglePersonByName("Паветта"),
                    personDAO.getSinglePersonByName("Калантэ"),
                    Relation.RelType.CHILD_IN_LAW,
                    1236L,
                    null
            ));

            relationList.add(new Relation(
                    null,
                    personDAO.getSinglePersonByName("Паветта"),
                    personDAO.getSinglePersonByName("Рёгнер"),
                    Relation.RelType.CHILD_IN_LAW,
                    1236L,
                    null
            ));

            relationList.add(new Relation(
                    null,
                    personDAO.getSinglePersonByName("Рёгнер"),
                    personDAO.getSinglePersonByName("Калантэ"),
                    Relation.RelType.SPOUSE_IN_LAW,
                    1235L,
                    1250L
            ));

            relationList.add(new Relation(
                    null,
                    personDAO.getSinglePersonByName("Калантэ"),
                    personDAO.getSinglePersonByName("Эйст"),
                    Relation.RelType.SPOUSE_IN_LAW,
                    1251L,
                    null
            ));

            relationList.add(new Relation(
                    null,
                    personDAO.getSinglePersonByName("Цирилла"),
                    personDAO.getSinglePersonByName("Паветта"),
                    Relation.RelType.CHILD_IN_LAW,
                    1252L,
                    null
            ));
        }

        relationDAO.saveCollection(relationList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("TRUNCATE relation RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE person_person_id_seq RESTART WITH 1;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE relation_relation_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
