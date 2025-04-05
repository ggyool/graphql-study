package com.ggyool.graphql_study.application

data class Book(
    val id: String,
    val name: String,
    val pageCount: Int,
    val authorId: String,
)
