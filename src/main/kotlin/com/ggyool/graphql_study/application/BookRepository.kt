package com.ggyool.graphql_study.application

import org.springframework.stereotype.Component

@Component
class BookRepository {

    fun findById(id: String): Book? = DUMMIES.firstOrNull { it.id == id }

    fun save(book: Book) {
        DUMMIES.add(book)
    }

    companion object {
        val DUMMIES = mutableListOf(
            Book("book-1", "Effective Java", 416, "author-1"),
            Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            Book("book-3", "Down Under", 436, "author-3"),
        )
    }
}