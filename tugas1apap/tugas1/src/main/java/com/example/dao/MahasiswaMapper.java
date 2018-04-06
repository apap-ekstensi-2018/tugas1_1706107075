package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    
    @Select("select id, npm, nama, tempat_lahir, tanggal_lahir, "
    		+ "jenis_kelamin, agama, golongan_darah, "
    		+ "tahun_masuk, jalur_masuk, status, id_prodi "
    		+ "from mahasiswa "
    		+ "where id = #{id}")
    MahasiswaModel selectMahasiswaById(@Param("id")int id);
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir,jenis_kelamin, " 
    		+ "agama, golongan_darah, tahun_masuk, jalur_masuk, id_prodi) "
    		+ "VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, "
    		+ "#{agama}, #{golongan_darah}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    @Update("UPDATE mahasiswa SET npm= #{npm}, nama=#{nama}, tempat_lahir=#{tempat_lahir}, "
    		+ "tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, agama=#{agama}, "
    		+ "golongan_darah=#{golongan_darah}, tahun_masuk=#{tahun_masuk}, "
    		+ "jalur_masuk=#{jalur_masuk}, id_prodi=#{id_prodi} "
    		+ "where id=#{id}")
    void updateMahasiswa(MahasiswaModel mahasiswa) ;
    
    @Select("select * from mahasiswa "
    		+ "where tahun_masuk=#{tahun} and "
    		+ "id_prodi=#{id_prodi} and status='Lulus'")
    List<MahasiswaModel> getMahasiswaLulus (@Param("tahun") String tahun, @Param("id_prodi")int id_prodi);
    
    @Select("select * from mahasiswa "
    		+ "where tahun_masuk=#{tahun} and "
    		+ "id_prodi=#{id_prodi}")
    List<MahasiswaModel> getTotalMahasiswa (@Param("tahun")String tahun_masuk, @Param("id_prodi")int id_prodi);
	

//    @Select("select npm, name, gpa from student")
//    List<StudentModel> selectAllStudents ();
//    
//    @Delete("DELETE FROM student where npm = #{npm}")
//    void deleteStudent (@Param("npm") String npm);
//    
//    @Update("UPDATE student SET name=#{name}, gpa=#{gpa} where npm=#{npm}")
//    void updateStudent (StudentModel student) ;
    
}
