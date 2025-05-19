package com.nsteuerber.library.books.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "release_date")
    private String releaseDate;
    private String author;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "book_tags",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    private Set<TagEntity> tags;
}
