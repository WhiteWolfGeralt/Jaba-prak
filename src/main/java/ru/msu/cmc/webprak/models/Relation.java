package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "relation")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    public enum RelType {
        CHILD_IN_LAW,
        SPOUSE_IN_LAW,
        BASTARD,
        ADOPTED_CHILD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "relation_id")
    private long relation_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_person")
    @ToString.Exclude
    @NonNull
    private Person target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perform_person")
    @ToString.Exclude
    @NonNull
    private Person perform;

    @Column(nullable = false, name = "type_of_relation")
    @NonNull
    private RelType type;

    @Column(name = "start_of_relation")
    private Date start;

    @Column(name = "end_of_relation")
    private Date end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation other = (Relation) o;
        return Objects.equals(relation_id, other.relation_id)
                && Objects.equals(target, other.target)
                && Objects.equals(perform, other.perform)
                && Objects.equals(type, other.type)
                && Objects.equals(start, other.start)
                && Objects.equals(end, other.end);
    }
}
