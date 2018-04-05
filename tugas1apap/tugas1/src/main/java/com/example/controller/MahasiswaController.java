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
public class MahasiswaController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    
    @Autowired
    ProgramStudiService programStudiDAO;
    
    @Autowired
    FakultasService fakultasDAO;
    
    @Autowired
    UniversitasService universitasDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    		model.addAttribute("title", "Index");
        return "index";
    }
    
    @RequestMapping("/mahasiswa")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
        if (mahasiswa != null) {
            model.addAttribute ("title","Lihat Mahasiswa By NPM");
            model.addAttribute ("mahasiswa", mahasiswa);
            return "view";
        } else {
            model.addAttribute ("title","Gagal Cari");
            model.addAttribute ("status", "Gagal!");
            model.addAttribute ("message", "Mahasiswa dengan NPM " + npm + " Tidak Ditemukan");
            return "notif";
        }
    }

    @RequestMapping("/mahasiswa/tambah")
    public String add (Model model)
    {
    		List<ProgramStudiModel> listProdi = programStudiDAO.selectAllProgramStudi();
    		model.addAttribute("title", "Tambah Mahasiswa");
    		model.addAttribute("mahasiswa", new MahasiswaModel());
    		model.addAttribute("listJenisKelamin", MahasiswaModel.LIST_JENIS_KELAMIN);
    		model.addAttribute("listAgama", MahasiswaModel.LIST_AGAMA);
    		model.addAttribute("listGolonganDarah", MahasiswaModel.LIST_GOLONGAN_DARAH);
    		model.addAttribute("listJalurMasuk", MahasiswaModel.LIST_JALUR_MASUK);
    		model.addAttribute("listProdi", listProdi);
        return "form-add";
    }

    @RequestMapping(value="/mahasiswa/tambah", method=RequestMethod.POST)
    public String addSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa)
    {
    		mahasiswa.setStatus("Aktif");
    		mahasiswa.setNpm(this.getNPM(mahasiswa));
    		
    		mahasiswaDAO.addMahasiswa(mahasiswa);
    		
    		if (mahasiswaDAO.selectMahasiswa(mahasiswa.getNpm()) != null) {
    			model.addAttribute ("title","Berhasil Tambah");
            model.addAttribute ("status", "Sukses!");
            model.addAttribute ("message", "Mahasiswa dengan NPM " + mahasiswa.getNpm() + " berhasil ditambahkan");
        } else {
            model.addAttribute ("title","Gagal Tambah");
            model.addAttribute ("status", "Gagal!");
            model.addAttribute("message", "Mahasiswa dengan NPM " + mahasiswa.getNpm() + " gagal ditambahkan");
        }
    		return "notif";
    }
    
    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String updatePath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);

        if (mahasiswa != null) {
        		List<ProgramStudiModel> listProdi = programStudiDAO.selectAllProgramStudi();
            model.addAttribute ("mahasiswa", mahasiswa);
            model.addAttribute("title", "Ubah Mahasiswa");
	    		model.addAttribute("listJenisKelamin", MahasiswaModel.LIST_JENIS_KELAMIN);
	    		model.addAttribute("listAgama", MahasiswaModel.LIST_AGAMA);
	    		model.addAttribute("listGolonganDarah", MahasiswaModel.LIST_GOLONGAN_DARAH);
	    		model.addAttribute("listJalurMasuk", MahasiswaModel.LIST_JALUR_MASUK);
	    		model.addAttribute("listProdi", listProdi);
            return "form-update";
        } else {
            model.addAttribute ("title","Gagal Ubah");
            model.addAttribute ("status", "Gagal!");
            model.addAttribute ("message", "Mahasiswa dengan NPM " + npm + " Tidak Ditemukan");
            return "notif";
        }
    }
    
    @RequestMapping(value="/mahasiswa/ubah/submit", method=RequestMethod.POST)
    public String updateSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa)
    {
    		MahasiswaModel prevMhs = mahasiswaDAO.selectMahasiswaById(mahasiswa.getId());
    		if(mahasiswa.getTahun_masuk() != prevMhs.getTahun_masuk() ||
    				mahasiswa.getJalur_masuk() != prevMhs.getJalur_masuk() ||
    				mahasiswa.getId_prodi() != prevMhs.getId_prodi()) {
    			mahasiswa.setNpm(this.getNPM(mahasiswa));
    		}
    		
    		mahasiswaDAO.updateMahasiswa(mahasiswa);
    		model.addAttribute ("title","Berhasil Ubah");
        model.addAttribute ("status", "Sukses!");
        model.addAttribute ("message", "Mahasiswa dengan NPM " + mahasiswa.getNpm() + " berhasil diubah");
    		return "notif";
    }
    
    public String getNPM (MahasiswaModel mahasiswa) {
    		mahasiswa.setProgram_studi(programStudiDAO.selectProgramStudi(mahasiswa.getId_prodi()));
		FakultasModel fakultas = fakultasDAO.selectFakultas(mahasiswa.getProgram_studi().getId_fakultas());
		UniversitasModel univ = universitasDAO.selectUniversitas(fakultas.getId_univ());
		
		int i=1;
		String npm = mahasiswa.generateNPM(univ.getKode_univ(), String.format("%03d",i));
		while(mahasiswaDAO.selectMahasiswa(npm) != null) {
			i = i+1;
			npm = mahasiswa.generateNPM(univ.getKode_univ(), String.format("%03d",i));
		}
		return npm;
    }

}
