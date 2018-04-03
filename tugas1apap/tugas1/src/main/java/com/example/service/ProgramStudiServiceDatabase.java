package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProgramStudiMapper;
import com.example.model.ProgramStudiModel;


@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService
{
    @Autowired
    private ProgramStudiMapper programStudiMapper;
    
    @Autowired
    private FakultasService fakultasService;

	@Override
	public ProgramStudiModel selectProgramStudi(int id) {
		// TODO Auto-generated method stub
		ProgramStudiModel prodi = programStudiMapper.selectProgramStudi(id);
		if(prodi!=null) {
			prodi.setFakultas(fakultasService.selectFakultas(prodi.getId_fakultas()));
		}
		return prodi;
	}

	@Override
	public List<ProgramStudiModel> selectAllProgramStudi() {
		// TODO Auto-generated method stub
		List<ProgramStudiModel> allProdi = programStudiMapper.selectAllProgramStudi();
		return allProdi;
	}

}
