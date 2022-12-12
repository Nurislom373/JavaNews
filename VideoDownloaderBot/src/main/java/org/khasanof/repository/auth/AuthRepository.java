package org.khasanof.repository.auth;

import org.khasanof.config.dataSource.DataSource;

import java.sql.Connection;

public class AuthRepository {
    private Connection connection = DataSource.getConnection();



}
