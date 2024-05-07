package br.com.renangnoatto.programmingcourses.modules.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renangnoatto.programmingcourses.modules.course.entities.CourseEntity;
import br.com.renangnoatto.programmingcourses.modules.course.repositories.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity saveCourse(CourseEntity courseEntity) {
        return this.courseRepository.save(courseEntity);
    }

    public List<CourseEntity> listAllCourses() {
        return this.courseRepository.findAll();
    }

    public List<CourseEntity> listAllCoursesByNameAndCategory(String name, String category) {
        return this.courseRepository.findByNameOrCategory(name, category);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public CourseEntity findById(int id) {
        return this.courseRepository.findById(id);
    }
}
