package com.nsteuerber.library.books.persistance.repository;

import com.nsteuerber.library.books.persistance.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends JpaRepository<TagEntity, Long> {

}
