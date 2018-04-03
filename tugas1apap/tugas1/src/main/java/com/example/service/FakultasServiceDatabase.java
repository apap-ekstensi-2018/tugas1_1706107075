package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.model.FakultasModel;


@Service
public class FakultasServiceDatabase implements FakultasService
{
    @Autowired
    private FakultasMapper fakultasMapper;
    
    @Autowired
    private UniversitasService universitasService;

	@Override
	public FakultasModel selectFakultas(int id) {
		// TODO Auto-generated method stub
		FakultasModel fakultas = fakultasMapper.selectFakultas(id);
		if(fakultas!=null) {
			fakultas.setUniversitas(universitasService.selectUniversitas(fakultas.getId_univ()));
		}
		return fakultas;
	}

}
