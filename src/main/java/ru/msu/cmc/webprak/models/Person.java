package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "person_id")
    private long person_id;

    @Column(nullable = false, name = "person_name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "gender")
    @NonNull
    private String gender;

    @Column(name = "date_of_birth")
    private Date birth;

    @Column(name = "date_of_death")
    private Date death;

    @Column(name = "characteristic")
    private String character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person other = (Person) o;
        return Objects.equals(person_id, other.person_id)
                && name.equals(other.name)
                && gender.equals(other.gender)
                && birth.equals(other.birth)
                && death.equals(other.death)
                && character.equals(other.character);
    }
}
