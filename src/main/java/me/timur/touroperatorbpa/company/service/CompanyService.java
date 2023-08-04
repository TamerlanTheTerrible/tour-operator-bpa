package me.timur.touroperatorbpa.company.service;

import me.timur.touroperatorbpa.company.model.CompanyCreateDto;
import me.timur.touroperatorbpa.company.model.CompanyDto;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

public interface CompanyService {
    CompanyDto create(CompanyCreateDto createDto);
    CompanyDto get(Long id);
    CompanyDto update(CompanyDto updateDto);
    List<CompanyDto> getAll();
}
