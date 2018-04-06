package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.UniversitasModel;
import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.service.FakultasService;
import com.example.service.MahasiswaService;
import com.example.service.ProgramStudiService;
import com.example.service.UniversitasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
public class KelulusanController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    
    @Autowired
    ProgramStudiService programStudiDAO;
    
    @Autowired
    FakultasService fakultasDAO;
    
    @Autowired
    UniversitasService universitasDAO;
    
    @RequestMapping(value="/kelulusan", method=RequestMethod.GET)
    public String percentage (Model model,
    		@RequestParam(value = "tahun", required = false) String tahun,
    		@RequestParam(value = "id_prodi", required = false) String id_prodi)
    {
    	
    		if(tahun == null && id_prodi == null) {
    			return "form-graduation";
    		}else {
    			int idProdi = Integer.valueOf(id_prodi);
    			
    			ProgramStudiModel prodi = programStudiDAO.selectProgramStudi(idProdi);
        		prodi.setFakultas(fakultasDAO.selectFakultas(prodi.getId_fakultas()));
        		UniversitasModel univ = universitasDAO.selectUniversitas(prodi.getFakultas().getId_univ());
        		
        		int totalMhs = mahasiswaDAO.getTotalMahasiswa(tahun, idProdi).size();
        		int mhsLulus = mahasiswaDAO.getMahasiswaLulus(tahun, idProdi).size();
        		float presentasi = mhsLulus/totalMhs;
        		//log.info(totalMhs + "--"+mhsLulus+"--"+presentasi);
        		
        		model.addAttribute("title", "Presentasi");
        		model.addAttribute("tahun", tahun);
        		model.addAttribute("prodi",prodi.getNama_prodi());
        		model.addAttribute("fakultas", prodi.getFakultas().getNama_fakultas());
        		model.addAttribute("univ", univ.getNama_univ());
        		model.addAttribute("totalMhs", Integer.toString(totalMhs));
        		model.addAttribute("mhsLulus", Integer.toString(mhsLulus));
        		model.addAttribute("presentasi", Double.toString(presentasi));
        		model.addAttribute("message", Integer.toString(mhsLulus) +" dari "+
        				Integer.toString(totalMhs)+" Mahasiswa Telah Lulus");
        		
        		return "graduation";
    		}
    }
    
}
