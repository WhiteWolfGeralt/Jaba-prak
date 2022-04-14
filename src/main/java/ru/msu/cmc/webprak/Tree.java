package ru.msu.cmc.webprak;

import lombok.NonNull;
import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.RelationDAOImpl;
import ru.msu.cmc.webprak.models.Person;
import ru.msu.cmc.webprak.models.Relation;

import java.util.List;

public class Tree {

    private final Person person;
    private Person spouse;
    private Tree parent;
    private List<Tree> children;

    public Tree(Person root) {
        this.person = root;
    }

    public void addAllAncestors(Tree person) {
        PersonDAOImpl personDAO;
        RelationDAOImpl relationDAO = new RelationDAOImpl();
        List<Person> firstAncestors = relationDAO.getTargetByRelType(person.person, Relation.RelType.CHILD_IN_LAW);
        if (firstAncestors == null) {
            return;
        }
        for (Person ancestor : firstAncestors) {

        }
    }

    private void printPersonsTree(Tree person, String offset) {
        System.out.print(offset + person.person.getName() + "<--");
        offset += "\t";
        for (var ancestor : person.children) {
            System.out.println(offset + ancestor.person.getName());
            printPersonsTree(ancestor, offset);
        }
    }

    private void addPersonToChildren(@NonNull Tree parent, @NonNull Tree child) {
        parent.children.add(child);
        child.parent = parent;
    }
}
