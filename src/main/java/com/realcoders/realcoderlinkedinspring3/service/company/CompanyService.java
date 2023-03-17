package com.realcoders.realcoderlinkedinspring3.service.company;

import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;

import java.util.Optional;

public interface CompanyService {
    void save (Company company);
    Optional<Company> findById (Integer id);
    Optional<Company> findByName (String username);
}
