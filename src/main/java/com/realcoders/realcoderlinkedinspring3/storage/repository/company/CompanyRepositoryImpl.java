package com.realcoders.realcoderlinkedinspring3.storage.repository.company;

import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyRepositoryImpl implements CompanyRepository{

    private final JdbcTemplate jdbcTemplate;

    public CompanyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostConstruct
    public void createTable() {
        final String sql =
                "CREATE TABLE IF NOT EXISTS companies (id SERIAL PRIMARY KEY,company_name VARCHAR(255),founding_year INT,description VARCHAR(255),location VARCHAR(255),website VARCHAR(255), user_id INT, FOREIGN KEY(user_id) REFERENCES users(id))";
        jdbcTemplate.execute(sql);
    }

    @PreDestroy
    public void dropTable(){
        jdbcTemplate.execute("DROP TABLE companies");
    }

    @Override
    public void save(Company company) {
        final String sql = "INSERT INTO companies (company_name,founding_year,description,location,website,user_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, company.getName(),company.getFounding_year(),company.getDescription(),company.getLocation(),company.getWebsite(), company.getUser_id());
    }

    @Override
    public Optional<Company> findByName(String name) {
        final String sql = "SELECT * FROM companies WHERE company_name = ?";
        return getCompany(name, sql).stream().findFirst();
    }

    private List<Company> getCompany(Object parameter, String sql) {
        return jdbcTemplate.query(sql, new Object[]{parameter}, new CompanyRowMapper());
    }

    @Override
    public Optional<Company> findById(Integer id) {
        final String sql = "SELECT * FROM companies WHERE id = ?";
        return getCompany(id, sql).stream().findFirst();
    }

}
