package com.jpahibernate.example.student_management_db.service;

import com.jpahibernate.example.student_management_db.model.Student;
import com.jpahibernate.example.student_management_db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // it contains the business logic
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student studentRequest){
        studentRepository.save(studentRequest);
        return "Student saved successfully!";
    }

    public Student getStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else{
            return null;
        }
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public String countStudents(){
       long totalCount = studentRepository.count();
       return "Total students present are : "+totalCount;
    }

    public String updateStudentUsingPut(int id, Student newStudentRequest){
        // find student with id
        // if student is present, update it
        // else we cannot update

        Student exisitingStudent = getStudentById(id);
        if(exisitingStudent!=null){
            // proceed to update
            studentRepository.save(newStudentRequest);
            return "Student updated successfully!";
        }else{
            // cannot update
            return "Student not found, hence cannot update!";
        }
    }


    public String updateStudentEmailUsingPatch(int id, String newEmail){
        // find student with id
        // if student is present, update it
        // else we cannot update

        Student exisitingStudent = getStudentById(id);
        if(exisitingStudent!=null){
            // proceed to update
            exisitingStudent.setEmail(newEmail);
            studentRepository.save(exisitingStudent);
            return "Student updated successfully!";
        }else{
            // cannot update
            return "Student not found, hence cannot update!";
        }
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id : "+id+" got deleted successfully!";
    }
}
