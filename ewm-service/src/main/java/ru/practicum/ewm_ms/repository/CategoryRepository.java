package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
