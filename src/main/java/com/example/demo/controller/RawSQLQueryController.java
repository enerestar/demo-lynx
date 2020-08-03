package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RawSQLQueryController {

    @Autowired
    private Environment env;

    @PostMapping(path = "/sql",
    consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<List<String>> handleNonBrowserSubmissions(@RequestBody String stringQuery)
        throws ResourceNotFoundException {

        String clean = stringQuery
                .replace("+", " ")
                .replace("%22", "'")
                .replace("%2C", ",")
                .replace("%3B", ";");

        // MariaDB conn
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String mariaDBUrl = "jdbc:mariadb://localhost:3308/simplewiki?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "user1";
        String password = "password1";

        List<String> result = new ArrayList<>();
        try {
            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(mariaDBUrl, user, password);

            preparedStmt = conn.prepareStatement(clean);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                System.out.println(rsmd.getColumnCount());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnName(i);
                    String columnTypeName = rsmd.getColumnTypeName(i);
                    if (columnTypeName.equals("VARBINARY")) {
                        result.add(i-1, name + " : " + rs.getString(name));
                    }
                    if (columnTypeName.equals("INTEGER")) {
                        result.add(i-1, name + " : " + rs.getInt(name));
                    }
                    if (columnTypeName.equals("BIGINT")) {
                        result.add(i-1, name + " : " + rs.getInt(name));
                    }
                    if (columnTypeName.equals("DOUBLE")) {
                        result.add(i-1, name + " : " + rs.getDouble(name));
                    }
                    if (columnTypeName.equals("TINYINT")) {
                        result.add(i-1, name + " : " + rs.getInt(name));
                    }
                    if (columnTypeName.equals("TIMESTAMP")) {
                        result.add(i-1, name + " : " + rs.getTimestamp(name));
                    }
                }
            }
            // clean up environment
            rs.close();
            preparedStmt.close();
            conn.close();
        } catch (
                SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (preparedStmt != null) preparedStmt.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(result);
    }
}
