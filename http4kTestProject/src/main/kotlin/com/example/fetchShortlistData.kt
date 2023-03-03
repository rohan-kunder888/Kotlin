package com.example

import com.example.models.Shortlist
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import java.lang.Error

val submissionIdLens = Path.of("submissionId")

fun fetchShortlistData(request:Request): Response {
     try {
         val submissionId = submissionIdLens(request);
         val shortlist = helpers.fetchShortlistHelper(submissionId);
         // how we can pass the shortlist.
         return Response(OK).body("")
    } catch (error: Error) {
        println(error.stackTrace)
        return Response(Status.BAD_REQUEST).body("ERROR")
    }
}