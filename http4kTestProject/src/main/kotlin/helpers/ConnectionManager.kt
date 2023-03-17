package helpers

import java.sql.Connection
import java.sql.DriverManager

fun getDirectConnection(): Connection {
        val jdbcUrl = "jdbc:postgresql://YOUR-DB-CONNECTION-STRING"
//        val jdbcUrl = "jdbc:postgresql://YOUR-DB-CONNECTION-STRING"
        val connection = DriverManager.getConnection(jdbcUrl, "USER_NAME", "PASSWORD");
        return connection;
}