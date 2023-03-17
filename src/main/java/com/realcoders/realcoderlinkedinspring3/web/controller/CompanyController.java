package com.realcoders.realcoderlinkedinspring3.web.controller;

import com.realcoders.realcoderlinkedinspring3.core.exceptions.CompanyNotFoundException;
import com.realcoders.realcoderlinkedinspring3.core.exceptions.NullPointerException;
import com.realcoders.realcoderlinkedinspring3.service.company.CompanyService;
import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import com.realcoders.realcoderlinkedinspring3.web.dto.CompanyDTOMapper;
import com.realcoders.realcoderlinkedinspring3.web.dto.CompanyRegistrationRequest;
import com.realcoders.realcoderlinkedinspring3.web.security.filter.UserContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyDTOMapper companyDTOMapper;

    public CompanyController(CompanyService companyService, CompanyDTOMapper companyDTOMapper) {
        this.companyService = companyService;
        this.companyDTOMapper = companyDTOMapper;
    }

    @PostMapping("/companies/dummy")
    public String createCompany(@RequestBody CompanyRegistrationRequest companyRegistrationRequest) {
        if (companyRegistrationRequest.getName() == null ||
                companyRegistrationRequest.getFounding_year() == null
        ) {
            throw new NullPointerException("The request was malformed or missing required fields");
        }

        companyService.save(companyDTOMapper.fromDTO(companyRegistrationRequest, UserContext.getUser().getId()));
        return "The company was successfully created.";
    }

    @GetMapping("/companies/{id}")
    public Company company_id(@PathVariable ("id") Integer id){
        return companyService.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found."));
    }



}
