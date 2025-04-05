package com.ggyool.graphql_study.application

import org.springframework.stereotype.Component

@Component
class AuthorRepository {

    fun findById(id: String): Author? = DUMMIES.firstOrNull { it.id == id }

    companion object {
        val DUMMIES = listOf(
            Author("author-1", "Joshua", "Bloch"),
            Author("author-2", "Douglas", "Adams"),
            Author("author-3", "Bill", "Bryson")
        )
    }
}