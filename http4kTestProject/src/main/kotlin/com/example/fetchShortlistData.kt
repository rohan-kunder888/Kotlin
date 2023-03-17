package com.example

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import java.lang.Error
import com.google.gson.Gson

val submissionIdLens = Path.of("submissionId")

fun fetchShortlistData(request:Request): Response {
     try {
         val gson: Gson = Gson()
         val submissionId = submissionIdLens(request);
         val shortlist = helpers.fetchShortlistHelper(submissionId);
         val shortlistFormatted = gson.toJson(shortlist)
         return Response(OK).body(shortlistFormatted);
    } catch (error: Error) {
        println(error.stackTrace)
        return Response(Status.BAD_REQUEST).body("ERROR")
    }
}