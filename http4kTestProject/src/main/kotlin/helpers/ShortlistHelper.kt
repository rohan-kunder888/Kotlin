package helpers

import com.example.models.ShortlistDetailed
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import java.sql.ResultSet

fun fetchShortlistHelper(submissionId: String): Any {
    try {
        val connection = helpers.getDirectConnection();
        if (connection.isValid(0)){
            val query = connection.prepareStatement("SELECT * FROM reviewer_finder.reviewer_shortlist where submission_id = '${submissionId}'")
            val result = query.executeQuery()
            return formatShortlist(result)
        } else {
            return Response(Status.CONNECTION_REFUSED).body("unable to Connect to Database")
        }
    } catch (error:Error){
        return Response(Status.BAD_REQUEST).body("Error in helper function ${error.stackTrace}")
    }
}

fun formatShortlist(results: ResultSet): MutableList<ShortlistDetailed> {
    val shortlist = mutableListOf<ShortlistDetailed>()
    while(results.next()){
        val firstname= results.getString("given_name")
        val lastName = results.getString("family_name")
        val email= results.getString("email_address")
        val adgId = results.getString("adg_person_id")
        val journalId = results.getInt("journal_id")
        val shortlistedBy = results.getString("shortlisted_by")
        val shortlistedFromNameSearch = results.getBoolean("shortlisted_from_name_search")
        val submissionRevision = results.getInt("submission_revision")
        val shortlistedManually = results.getBoolean("shortlisted_manually")
        val shortlistedFromPreviousReviewers = results.getBoolean("shortlisted_from_previous_reviewers")
        val isDeleted = results.getBoolean("is_deleted")
        val shortlistedDate = results.getDate("shortlisted_date")
        val updatedAt = results.getDate("updated_at")
        shortlist.add(
            ShortlistDetailed(firstname, lastName, email, adgId, journalId, shortlistedBy, shortlistedDate, shortlistedFromNameSearch,
                submissionRevision, shortlistedManually, shortlistedFromPreviousReviewers, isDeleted, updatedAt))
    }
    return shortlist;
}
