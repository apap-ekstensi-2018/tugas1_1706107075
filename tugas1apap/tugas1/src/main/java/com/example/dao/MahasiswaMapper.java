package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper
{
    @Select("select id, npm, nama, tempat_lahir, tanggal_lahir, "
    		+ "jenis_kelamin, agama, golongan_darah, "
    		+ "tahun_masuk, jalur_masuk, status, id_prodi "
    		+ "from mahasiswa "
    		+ "where npm = #{npm}")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir,jenis_kelamin, " 
    		+ "agama, golongan_darah, tahun_masuk, jalur_masuk, id_prodi) "
    		+ "VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, "
    		+ "#{agama}, #{golongan_darah}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void addMahasiswa (MahasiswaModel mahasiswa);

//    @Select("select npm, name, gpa from student")
//    List<StudentModel> selectAllStudents ();
//    
//    @Delete("DELETE FROM student where npm = #{npm}")
//    void deleteStudent (@Param("npm") String npm);
//    
//    @Update("UPDATE student SET name=#{name}, gpa=#{gpa} where npm=#{npm}")
//    void updateStudent (StudentModel student) ;
    
}
