package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j

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
	public MahasiswaModel selectMahasiswaById(int id) {
		// TODO Auto-generated method stub
		MahasiswaModel mhs = mahasiswaMapper.selectMahasiswaById(id);
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

	@Override
	public void updateMahasiswa(MahasiswaModel mahasiswa) {
		// TODO Auto-generated method stub
		mahasiswaMapper.updateMahasiswa(mahasiswa);
	}

	@Override
	public List<MahasiswaModel> getMahasiswaLulus(String tahun, int id_prodi) {
		// TODO Auto-generated method stub
		return mahasiswaMapper.getMahasiswaLulus(tahun, id_prodi);
	}

	@Override
	public List<MahasiswaModel> getTotalMahasiswa (String tahun, int id_prodi) {
		// TODO Auto-generated method stub
		return mahasiswaMapper.getTotalMahasiswa(tahun, id_prodi);
	}

}
