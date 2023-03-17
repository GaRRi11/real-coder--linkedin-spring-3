package com.realcoders.realcoderlinkedinspring3.storage.repository.company;

import com.realcoders.realcoderlinkedinspring3.storage.model.Company;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRowMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Company(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("company_name"),
                rs.getInt("founding_year"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("website")
        );
    }
}
