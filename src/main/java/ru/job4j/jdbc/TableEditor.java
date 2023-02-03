package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    private void createStatement(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws Exception {
        createStatement(String.format("create table if not exists %s();", tableName));
        System.out.println(getTableScheme(tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        createStatement(String.format("drop table if exists %s;", tableName));
        System.out.println("drop has been successfully");
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        createStatement(String.format("alter table %s add column if not exists %s %s;",
                tableName, columnName, type));
        System.out.println(getTableScheme(tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        createStatement(String.format("alter table %s drop column %s;",
                tableName, columnName));
        System.out.println(getTableScheme(tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        createStatement(String.format("alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName));
        System.out.println(getTableScheme(tableName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("src/main/resources/app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.createTable("products");
                tableEditor.addColumn("products", "products_name", "varchar(20)");
                tableEditor.renameColumn("products", "products_name", "p_name");
                tableEditor.dropColumn("products", "p_name");
                tableEditor.addColumn("products", "products_name", "varchar(20)");
                tableEditor.dropTable("products");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
