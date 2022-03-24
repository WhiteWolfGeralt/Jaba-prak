package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "person_id")
    private Long id;

    @Column(nullable = false, name = "person_name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "gender")
    @NonNull
    private String gender;

    @Column(name = "date_of_birth")
    private Long birth;

    @Column(name = "date_of_death")
    private Long death;

    @Column(name = "characteristic")
    @NonNull
    private String character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person other = (Person) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && gender.equals(other.gender)
                && Objects.equals(birth, other.birth)
                && Objects.equals(death, other.death)
                && character.equals(other.character);
    }
}
