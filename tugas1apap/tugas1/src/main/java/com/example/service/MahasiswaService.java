package com.example.service;

import com.example.model.MahasiswaModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);
    
    MahasiswaModel selectMahasiswaById (int id);
    
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    void updateMahasiswa (MahasiswaModel mahasiswa);

//    List<MahasiswaModel> selectAllStudents ();
//
//    void addStudent (MahasiswaModel student);
//
//    void deleteStudent (String npm);

}
