package com.nawaf.quiz2.Service;

import com.nawaf.quiz2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();


    public List<Student> getStudents(){
        return students;
    }

    public void newStudent(Student student){
        students.add(student);
    }

    public boolean updateStudent(String id, Student student){
        for (int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student getStudentByName(String name){
        for (Student student: students){
            if(student.getName().equals(name)) return student;
        }
        return null;
    }

    public List<Student> getAllStudentsByMajor(String major){
        List<Student> studentListByMajor = new ArrayList<>();
        for(Student student: students){
            if(student.getMajor().equals(major)) studentListByMajor.add(student);
        }
        return studentListByMajor;
    }
}
