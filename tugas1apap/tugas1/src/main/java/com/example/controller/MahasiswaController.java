package com.example.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.service.MahasiswaService;
import com.example.service.ProgramStudiService;

@Controller
public class MahasiswaController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    
    @Autowired
    ProgramStudiService programStudiDAO;


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
        MahasiswaModel student = mahasiswaDAO.selectMahasiswa (npm);
        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute ("title","Lihat Mahasiswa By NPM");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute ("title","Mahasiswa Tidak Ditemukan");
            return "not-found";
        }
    }

    @RequestMapping("/mahasiswa/tambah")
    public String add (Model model)
    {
    		List<ProgramStudiModel> listProdi = programStudiDAO.selectAllProgramStudi();
    		model.addAttribute("title", "Tambah Mahasiswa");
    		model.addAttribute("listProdi", listProdi);
        return "form-add";
    }

    @RequestMapping(value="/mahasiswa/tambah/submit")
    public String addSubmit(
            @RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
            @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
            @RequestParam(value = "agama", required = false) String agama,
            @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
            @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
            @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
            @RequestParam(value = "id_prodi", required = false) int id_prodi)
    {
    		String jalur="";
    		if(jalur_masuk.equals("Undangan Olimpiade"))
    			jalur="53";
    		else if(jalur_masuk.equals("Undangan Reguler/SNMPTN"))
    			jalur="54";
    		else if(jalur_masuk.equals("Undangan Paralel/PPKB"))
    			jalur="55";
    		else if(jalur_masuk.equals("Ujian Tulis Bersama/SBMPTN"))
    			jalur="56";
    		else if(jalur_masuk.equals("Ujian Tulis Mandiri"))
    			jalur="62";
    		
    		Random rand = new Random();
    		int randNum = rand.nextInt((999-100)+1)+100;
    		String npm = tahun_masuk.substring(tahun_masuk.length()-2, tahun_masuk.length()) + 
			String.valueOf(programStudiDAO.selectProgramStudi(id_prodi).getFakultas().getUniversitas().getKode_univ()) +
    			String.valueOf(programStudiDAO.selectProgramStudi(id_prodi).getKode_prodi()) + jalur + String.format("%03d",randNum);
    		
    		MahasiswaModel mhs = new MahasiswaModel (npm,nama, tempat_lahir, tanggal_lahir,jenis_kelamin,
    				agama,golongan_darah,tahun_masuk,jalur_masuk,id_prodi);
    		mahasiswaDAO.addMahasiswa(mhs);
    		return "success-add";
    }

//    @RequestMapping("/student/viewall")
//    public String view (Model model)
//    {
//        List<MahasiswaModel> students = studentDAO.selectAllStudents ();
//        model.addAttribute ("students", students);
//        model.addAttribute("title", "View All Student");
//        return "viewall";
//    }
//
//
//    @RequestMapping("/student/delete/{npm}")
//    public String delete (Model model, @PathVariable(value = "npm") String npm)
//    {
//    		MahasiswaModel student = studentDAO.selectStudent (npm);
//
//        if (student != null) {
//            studentDAO.deleteStudent (npm);
//            model.addAttribute("title", "Delete");
//            return "delete";
//        } else {
//            model.addAttribute ("npm", npm);
//            model.addAttribute ("title","Student Not Found");
//            return "not-found";
//        }
//    }
//    
//    @RequestMapping("/student/update/{npm}")
//    public String updatePath (Model model,
//            @PathVariable(value = "npm") String npm)
//    {
//        MahasiswaModel student = studentDAO.selectStudent (npm);
//
//        if (student != null) {
//            model.addAttribute ("student", student);
//            model.addAttribute("title", "Update Student");
//            return "form-update";
//        } else {
//            model.addAttribute ("npm", npm);
//            model.addAttribute ("title","Student Not Found");
//            return "not-found";
//        }
//    }
//    
//    @RequestMapping(value="/student/update/submit", method= RequestMethod.POST)
//    public String updateSubmit(@ModelAttribute MahasiswaModel student) {
//    		studentDAO.updateStudent (student);
//    		return "success-update";
//    }

}
