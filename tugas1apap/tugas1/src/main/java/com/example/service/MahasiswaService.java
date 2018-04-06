package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);
    
    MahasiswaModel selectMahasiswaById (int id);
    
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    void updateMahasiswa (MahasiswaModel mahasiswa);
    
    List<MahasiswaModel> getMahasiswaLulus (String tahun_masuk, int id_prodi);
    
    List<MahasiswaModel> getTotalMahasiswa (String tahun_masuk, int id_prodi);


//    List<MahasiswaModel> selectAllStudents ();
//
//    void addStudent (MahasiswaModel student);
//
//    void deleteStudent (String npm);

}
