package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MahasiswaModel
{
	public static final String[] LIST_JENIS_KELAMIN  = {"Laki-laki", "Perempuan"};
	public static final String[] LIST_GOLONGAN_DARAH = {"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
	public static final String[] LIST_AGAMA  = {"Islam", "Budha", "Hindu", "Katolik", "Konghucu", "Protestan"};
	public static final String[] LIST_JALUR_MASUK  = {"Undangan Olimpiade", "Undangan Reguler/SNMPTN", "Undangan Paralel/PPKB", "Ujian Tulis Bersama/SBMPTN", "Ujian Tulis Mandiri"};
	
	private int id;
    private String npm;
    private String nama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private int jenis_kelamin;
    private String agama;
    private String golongan_darah;
    private String status;
    private String tahun_masuk;
    private String jalur_masuk;
    private int id_prodi;
    private ProgramStudiModel program_studi;

    
    public String generateNPM(String kode_univ, String input_number) {
    		String thn_masuk = tahun_masuk.substring(2, 4);
    		String kode_prodi = program_studi.getKode_prodi();
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
		return thn_masuk + kode_univ + kode_prodi + jalur + input_number;
    }
}