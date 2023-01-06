package com.example

import com.example.models.Shortlist
import com.zaxxer.hikari.HikariDataSource

fun fetchDataPooledConnection () {
    println("inside pooled connection method")
    val dataSource = HikariDataSource()
    //create your db string and add below
    dataSource.jdbcUrl = ""
    //add your db username password
    dataSource.username = ""
    dataSource.password = ""
    println(dataSource.maximumPoolSize)

    val connection = dataSource.connection
    // add your query
    val query = connection.prepareStatement("SELECT * FROM reviewer_finder.stoa_shortlist")
    val result = query.executeQuery()
    val shortlist = mutableListOf<Shortlist>()
    // iterate over your results to get all data from db in a list.
    while (result.next()) {
        val firstname= result.getString("first_name")
        val lastName = result.getString("last_name")
        val email= result.getString("email_address")
        val adgId = result.getString("adg_person_id")
        shortlist.add(Shortlist(firstname,lastName,email,adgId))
    }
    println(shortlist)
}