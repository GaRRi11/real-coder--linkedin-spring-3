package com.realcoders.realcoderlinkedinspring3.storage.repository.company;

import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompanyRepository {

    void save (Company company);

    Optional<Company> findByName(String name);

    Optional<Company> findById(Integer id);
}
