package com.example.service;

import java.util.List;

import com.example.model.ProgramStudiModel;

public interface ProgramStudiService
{
    ProgramStudiModel selectProgramStudi (int id);
    List<ProgramStudiModel> selectAllProgramStudi();
}
