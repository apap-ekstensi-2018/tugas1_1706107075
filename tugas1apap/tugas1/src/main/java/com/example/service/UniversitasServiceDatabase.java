package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.UniversitasMapper;
import com.example.model.UniversitasModel;


@Service
public class UniversitasServiceDatabase implements UniversitasService
{
    @Autowired
    private UniversitasMapper universitasMapper;

	@Override
	public UniversitasModel selectUniversitas(int id) {
		// TODO Auto-generated method stub
		return universitasMapper.selectUniversitas(id);
	}

}
