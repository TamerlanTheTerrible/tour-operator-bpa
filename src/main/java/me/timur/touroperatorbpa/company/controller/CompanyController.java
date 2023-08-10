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

    @PostMapping(value = {"/create", })
    public BaseResponse<CompanyDto> create(@Valid @RequestBody CompanyCreateDto createDto) {
        return BaseResponse.ok(companyService.create(createDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<CompanyDto> get(@PathVariable Long id) {
        return BaseResponse.ok(companyService.get(id));
    }

    @PutMapping(value = {"/update"})
    public BaseResponse<CompanyDto> update(@RequestBody CompanyDto updateDto) {
        return BaseResponse.ok(companyService.update(updateDto));
    }

    @GetMapping(value = {"/", ""})
    public BaseResponse<List<CompanyDto>> getAll() {
        return BaseResponse.ok(companyService.getAll());
    }
}
