package com.example.models
import java.util.*

data class ShortlistDetailed (
    val firstname:String,
    val lastName:String,
    val email:String,
    val adgId :String,
    val journalId :Number,
    val shortlistedBy :String,
    val shortlistedDate : Date,
    val shortlistedFromNameSearch :Boolean,
    val submissionRevision :Number,
    val shortlistedManually :Boolean,
    val shortlistedFromPreviousReviewers :Boolean,
    val isDeleted :Boolean,
    val updatedAt : Date,
){}