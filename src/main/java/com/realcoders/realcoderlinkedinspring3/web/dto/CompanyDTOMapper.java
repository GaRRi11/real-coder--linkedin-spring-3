package com.realcoders.realcoderlinkedinspring3.web.dto;

import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyDTOMapper {

    private static Integer idCounter = 1;

    private Integer generateId(){
        return idCounter++;
    }

    public Company fromDTO(CompanyRegistrationRequest companyRegistrationRequest,Integer user_id){
        return new Company(
                generateId(),
                user_id,
                companyRegistrationRequest.getName(),
                companyRegistrationRequest.getFounding_year(),
                companyRegistrationRequest.getDescription(),
                companyRegistrationRequest.getLocation(),
                companyRegistrationRequest.getWebsite()
        );
    }
}
