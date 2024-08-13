package com.example.project.Repository;

import com.example.project.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
    Category findCategoryById(Long id);
//    Category updateCategory(Category category);

//    @Query("")
    Category deleteCategoryById(Long id);
}
