package br.com.renangnoatto.programmingcourses.modules.course.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.renangnoatto.programmingcourses.modules.course.entities.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID>{
    
    List<CourseEntity> findByNameOrCategory(String name, String category);
    void deleteById(int id);
    CourseEntity findById(int id);
}
