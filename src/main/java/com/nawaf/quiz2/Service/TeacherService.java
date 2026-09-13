package com.nawaf.quiz2.Service;

import com.nawaf.quiz2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final List<Teacher> teachers = new ArrayList<>();


    public List<Teacher> getTeachers(){
        return teachers;
    }

    public void newTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id, Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equals(id)){
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equals(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacherById(String id){
        for(Teacher teacher: teachers){
            if(teacher.getId().equals(id)) return teacher;
        }
        return null;
    }

    public List<Teacher> getAllTeachersBySalaryEqualOrAbove(double salary){
        List<Teacher> teacherListBySalaryEqualOrAbove = new ArrayList<>();
        for(Teacher teacher: teachers){
            if(teacher.getSalary() >= salary) teacherListBySalaryEqualOrAbove.add(teacher);
        }
        return teacherListBySalaryEqualOrAbove;
    }
}
