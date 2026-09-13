package com.nawaf.quiz2.Controller;

import com.nawaf.quiz2.Api.ApiResponse;
import com.nawaf.quiz2.Model.Teacher;
import com.nawaf.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getTeachers(){
        return ResponseEntity
                .status(200)
                .body(teacherService.getTeachers());
    }

    @PostMapping("/new")
    public ResponseEntity<?> newTeacher(@Valid @RequestBody Teacher teacher, Errors errors){
        if(errors.hasErrors()) return ResponseEntity
                .status(400)
                .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        teacherService.newTeacher(teacher);

        return ResponseEntity
                .status(201)
                .body(new ApiResponse("Create new teacher successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @Valid @RequestBody Teacher teacher, Errors errors){
        if(errors.hasErrors()) return ResponseEntity
                .status(400)
                .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isUpdated = teacherService.updateTeacher(id, teacher);

        if(!isUpdated) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Teacher not found"));

        return ResponseEntity
                .status(200)
                .body(new ApiResponse("Update teacher successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id){
        boolean isDeleted = teacherService.deleteTeacher(id);

        if(!isDeleted) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Teacher not found"));

        return ResponseEntity
                .status(200)
                .body(new ApiResponse("Delete teacher successfully"));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable String id){
        Teacher teacher = teacherService.getTeacherById(id);

        if(teacher == null) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Teacher not found"));

        return ResponseEntity
                .status(200)
                .body(teacher);
    }

    @GetMapping("/get-list-salary/{salary}")
    public ResponseEntity<?> getAllTeachersBySalaryEqualOrAbove(@PathVariable double salary){
        List<Teacher> teacherListBySalaryEqualOrAbove = teacherService.getAllTeachersBySalaryEqualOrAbove(salary);

        if(teacherListBySalaryEqualOrAbove.isEmpty()) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Not found any teacher has " + salary + " salary or above"));

        return ResponseEntity
                .status(200)
                .body(teacherListBySalaryEqualOrAbove);
    }
}
