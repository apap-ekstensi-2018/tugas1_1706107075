package com.example.service;

import com.example.model.MahasiswaModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);
    
    void addMahasiswa (MahasiswaModel mahasiswa);

//    List<MahasiswaModel> selectAllStudents ();
//
//    void addStudent (MahasiswaModel student);
//
//    void deleteStudent (String npm);
//    
//    void updateStudent (MahasiswaModel student);
}
