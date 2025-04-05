package com.ggyool.graphql_study.application

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import org.springframework.graphql.data.method.annotation.*
import org.springframework.stereotype.Controller

@Controller
class BookController(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository,
) {

    val bookChannel = Channel<Book>(Channel.BUFFERED)

    @QueryMapping
    fun findBookById(@Argument id: String): Book? {
        return bookRepository.findById(id);
    }

    @SubscriptionMapping
    fun bookAdded(): Flow<Book> = bookChannel.receiveAsFlow()

    @MutationMapping
    suspend fun addBook(
        @Argument id: String,
        @Argument name: String,
        @Argument pageCount: Int,
        @Argument authorId: String
    ): Book {
        val book = Book(id, name, pageCount, authorId)
        bookRepository.save(book)
        bookChannel.send(book)
        return book
    }

    @SchemaMapping
    fun author(book: Book): Author? {
        return authorRepository.findById(book.authorId)
    }
}