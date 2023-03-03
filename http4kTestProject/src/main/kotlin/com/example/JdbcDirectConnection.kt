import com.example.models.Shortlist
import org.http4k.core.Response
import org.http4k.core.Request
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import java.lang.Error
import java.sql.DriverManager

fun fetchData ():Response {
    try {
        //create your db string and replace below
        val jdbcUrl = "jdbc:postgresql://[DbString]"
        //replace username password with your db credential
        val connection = DriverManager.getConnection(jdbcUrl, "user_name", "password")
        val shortlist =mutableListOf<Shortlist>()
        println(connection.isValid(0))
        if(connection.isValid(0)){
            // create you query
            val query = connection.prepareStatement("SELECT * FROM reviewer_finder.reviewer_shortlist ")
            val result = query.executeQuery()
            println("RESULT $result")
            while(result.next()){
                val firstname= result.getString("given_name")
                val lastName = result.getString("family_name")
                val email= result.getString("email_address")
                val adgId = result.getString("adg_person_id")
                shortlist.add(Shortlist(firstname,lastName,email,adgId))
            }
            println(shortlist)
        }
        return Response(OK).body("Hello There")
    } catch (e: Error) {
        println(e.stackTrace)
        return Response(BAD_REQUEST).body("ERROR")
    }
}