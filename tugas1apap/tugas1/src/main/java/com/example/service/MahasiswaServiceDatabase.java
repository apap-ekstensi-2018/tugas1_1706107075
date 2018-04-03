package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;


@Service
public class MahasiswaServiceDatabase implements MahasiswaService
{
    @Autowired
    private MahasiswaMapper mahasiswaMapper;
    
    @Autowired
    private ProgramStudiService prodiService;

	@Override
	public MahasiswaModel selectMahasiswa(String npm) {
		// TODO Auto-generated method stub
		MahasiswaModel mhs = mahasiswaMapper.selectMahasiswa(npm);
		if(mhs!=null) {
			mhs.setProgram_studi(prodiService.selectProgramStudi(mhs.getId_prodi()));
		}
		return mhs;
	}

	@Override
	public void addMahasiswa(MahasiswaModel mahasiswa) {
		// TODO Auto-generated method stub
		mahasiswaMapper.addMahasiswa(mahasiswa);
	}

}
