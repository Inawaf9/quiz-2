package com.nawaf.quiz2.Controller;

import com.nawaf.quiz2.Api.ApiResponse;
import com.nawaf.quiz2.Model.Student;
import com.nawaf.quiz2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get-all")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/new")
    public ResponseEntity<?> newStudent(@Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()) return ResponseEntity
                .status(400)
                .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        studentService.newStudent(student);

        return ResponseEntity
                .status(201)
                .body(new ApiResponse("Create new student successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()) return ResponseEntity
                .status(400)
                .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isUpdated = studentService.updateStudent(id, student);

        if(!isUpdated) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Student not found"));

        return ResponseEntity
                .status(200)
                .body(new ApiResponse("Update student successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Student not found"));

        return ResponseEntity
                .status(200)
                .body(new ApiResponse("Deleted student successfully"));
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable String name){

        Student studentByName = studentService.getStudentByName(name);

        if(studentByName == null) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Student not found"));

        return ResponseEntity
                .status(200)
                .body(studentByName);
    }

    @GetMapping("/get-all-by-major/{major}")
    public ResponseEntity<?> getAllStudentByMajor(@PathVariable String major){
        List<Student> studentListByMajor = studentService.getAllStudentsByMajor(major);

        if(studentListByMajor.isEmpty()) return ResponseEntity
                .status(400)
                .body(new ApiResponse("Not found any student has " + major + " major"));

        return ResponseEntity
                .status(200)
                .body(studentListByMajor);
    }
}
