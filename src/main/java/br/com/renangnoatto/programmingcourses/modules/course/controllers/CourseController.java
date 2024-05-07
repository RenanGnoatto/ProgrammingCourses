package br.com.renangnoatto.programmingcourses.modules.course.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.renangnoatto.programmingcourses.modules.course.entities.CourseEntity;
import br.com.renangnoatto.programmingcourses.modules.course.services.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var result = this.courseService.saveCourse(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<Object> listAllCoursesByNameAndCategory(@RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        try {
            if (name == null && category == null) {
                var result = this.courseService.listAllCourses();
                return ResponseEntity.ok().body(result);
            } else {
                var result = this.courseService.listAllCoursesByNameAndCategory(name, category);
                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable int id, @RequestBody CourseEntity courseEntity) {
        try {
            CourseEntity course = new CourseEntity();
            course = courseService.findById(id);

            if (course != null) {

                if (courseEntity.getName() != null) {
                    course.setName(courseEntity.getName());
                }

                if (courseEntity.getCategory() != null) {
                    course.setCategory(courseEntity.getCategory());
                }

                var result = this.courseService.saveCourse(course);
                return ResponseEntity.ok().body(result);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Object> deleteCourse (@PathVariable int id) {
        try {
            this.courseService.deleteCourse(id);
            return ResponseEntity.ok().body("Curso excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}/active")
    public ResponseEntity<Object> activeCourse (@PathVariable int id, @RequestBody CourseEntity courseEntity) {
        try {
            CourseEntity course;
            course = this.courseService.findById(id);
            if (courseEntity.isActive() == true) {
                course.setActive(true);
                var result = this.courseService.saveCourse(course);
                return ResponseEntity.ok().body(result);
            }
            else {
                course.setActive(false);
                var result = this.courseService.saveCourse(course);
                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
