package me.timur.touroperatorbpa.company.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.company.model.CompanyCreateDto;
import me.timur.touroperatorbpa.company.model.CompanyDto;
import me.timur.touroperatorbpa.company.service.CompanyService;
import me.timur.touroperatorbpa.domain.entity.Company;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.exception.OperatorBpaException;
import me.timur.touroperatorbpa.exception.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto create(CompanyCreateDto createDto) {
        log.info("Creating company: {}", createDto);
        var company = new Company(createDto);
        companyRepository.save(company);
        return new CompanyDto(company);
    }

    @Override
    public CompanyDto get(Long id) {
        return new CompanyDto(getCompanyEntity(id));
    }

    @Override
    public CompanyDto update(CompanyDto updateDto) {
        log.info("Updating company: {}", updateDto);

        var company = getCompanyEntity(updateDto.getId());
        if (updateDto.getName() != null) {
            company.setName(updateDto.getName());
        }
        if (updateDto.getCountry() != null) {
            company.setCountry(updateDto.getCountry());
        }
        companyRepository.save(company);

        return new CompanyDto(company);
    }

    @Override
    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyDto::new)
                .toList();
    }

    private Company getCompanyEntity(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new OperatorBpaException(ResponseCode.RESOURCE_NOT_FOUND, "Could not find company with id: " + id));
    }
}
