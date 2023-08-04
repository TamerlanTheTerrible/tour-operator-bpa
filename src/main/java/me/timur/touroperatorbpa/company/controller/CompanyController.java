package me.timur.touroperatorbpa.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.company.model.CompanyCreateDto;
import me.timur.touroperatorbpa.company.model.CompanyDto;
import me.timur.touroperatorbpa.company.service.CompanyService;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 04/08/23.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(value = {"/", ""})
    public BaseResponse<CompanyDto> create(@Valid @RequestBody CompanyCreateDto createDto) {
        return BaseResponse.payload(companyService.create(createDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<CompanyDto> get(@PathVariable Long id) {
        return BaseResponse.payload(companyService.get(id));
    }

    @PutMapping(value = {"/", ""})
    public BaseResponse<CompanyDto> update(@RequestBody CompanyDto updateDto) {
        return BaseResponse.payload(companyService.update(updateDto));
    }

    @GetMapping(value = {"/", ""})
    public BaseResponse<List<CompanyDto>> getAll() {
        return BaseResponse.payload(companyService.getAll());
    }
}
