import com.example.models.Shortlist
import java.lang.Error
import java.sql.DriverManager

fun fetchData () {
    try {
        //create your db string and replace below
        val jdbcUrl = "jdbc:postgresql://[DbString]"
        //replace username password with your db credential
        val connection = DriverManager.getConnection(jdbcUrl, "user_name", "password")
        println(connection.isValid(0))
        if(connection.isValid(0)){
            // create you query
            val query = connection.prepareStatement("SELECT * FROM reviewer_finder.reviewer_shortlist LIMIT 10")
            val result = query.executeQuery()
            println("RESULT $result")
            val shortlist = mutableListOf<Shortlist>()
            while(result.next()){
                val firstname= result.getString("given_name")
                val lastName = result.getString("family_name")
                val email= result.getString("email_address")
                val adgId = result.getString("adg_person_id")
                shortlist.add(Shortlist(firstname,lastName,email,adgId))
            }
            println(shortlist)
        }
    } catch (e: Error) {
        println(e.stackTrace)
    }
}