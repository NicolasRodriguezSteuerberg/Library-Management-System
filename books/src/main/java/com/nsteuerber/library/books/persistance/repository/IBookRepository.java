package com.nsteuerber.library.books.persistance.repository;

import com.nsteuerber.library.books.persistance.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {
    Optional<List<BookEntity>> findBookEntityByTitleContainingIgnoreCase(String title);
}
