package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person2place")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person2Place implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "node_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person")
    @ToString.Exclude
    @NonNull
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place")
    @ToString.Exclude
    @NonNull
    private Place place;
}
