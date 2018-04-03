package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.model.ProgramStudiModel;

@Mapper
public interface ProgramStudiMapper
{
    @Select("select id, kode_prodi, nama_prodi, id_fakultas "
    		+ "from program_studi "
    		+ "where id = #{id}")
    ProgramStudiModel selectProgramStudi (@Param("id") int id);
    
    @Select("select id, kode_prodi, nama_prodi, id_fakultas "
    		+ "from program_studi")
    List<ProgramStudiModel> selectAllProgramStudi ();
    
}
