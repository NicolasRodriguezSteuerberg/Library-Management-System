package com.nsteuerber.library.books.persistance.repository;

import com.nsteuerber.library.books.persistance.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
