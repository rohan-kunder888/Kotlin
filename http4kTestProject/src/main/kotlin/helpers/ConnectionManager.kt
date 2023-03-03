package helpers

import java.sql.Connection
import java.sql.DriverManager

fun getDirectConnection(): Connection {
        val jdbcUrl = "jdbc:postgresql://[DbString]"
        val connection = DriverManager.getConnection(jdbcUrl, "user_name", "password")
        return connection;
}