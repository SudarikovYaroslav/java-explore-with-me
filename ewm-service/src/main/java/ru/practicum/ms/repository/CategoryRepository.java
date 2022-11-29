package ru.practicum.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ms.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
