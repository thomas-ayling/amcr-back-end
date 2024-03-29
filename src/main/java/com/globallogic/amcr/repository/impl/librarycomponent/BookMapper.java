package com.globallogic.amcr.repository.impl.librarycomponent;

import com.globallogic.amcr.model.librarycomponent.Book;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface BookMapper {

    /**
     * Method for saving new book into the database.
     * @param book - Book Object containing data for the new book entry into the database.
     */
    @Insert("INSERT INTO library(id, title, genre, author, cover, email, reader, available) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{title}, #{genre}, #{author}, #{cover}, #{email}, #{reader}, #{available})")
    void save(Book book);

    /**
     * Method that allows you to retrieve one individual book.
     * @param id - UUID book id for identifying book.
     * @return - Book Object containing all the information for selected book.
     */
    @Results(id = "bookSingleResult")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "genre", javaType = String.class),
            @Arg(column = "author", javaType = String.class),
            @Arg(column = "reader", javaType = String.class),
            @Arg(column = "available", javaType = Boolean.class),
            @Arg(column = "cover", javaType = String.class),
            @Arg(column = "email", javaType = String.class)
    })
    @Select("SELECT * FROM library WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    Book get(@Param("id") UUID id);

    /**
     * Method that allows you to retrieve a list of books and content from the database.
     * @return - List of books.
     */
    @Results(id = "bookAllResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "genre", javaType = String.class),
            @Arg(column = "author", javaType = String.class),
            @Arg(column = "reader", javaType = String.class),
            @Arg(column = "available", javaType = Boolean.class),
            @Arg(column = "cover", javaType = String.class),
            @Arg(column = "email", javaType = String.class)
    })
    @Select("SELECT id, title, genre, author, cover, email, reader, available FROM library")
    List<Book> getAll();

    /**
     * Method that allows you to reserve a book.
     * @param id - UUID for identifying which book is currently being reserved.
     * @param book - Book Object containing data that is being updated (reader and email).
     */
    @Update("UPDATE library SET available = false, reader = #{book.reader}, email = #{book.email} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void reserve(UUID id, Book book);

    /**
     * Method that allows the admin to update book information.
     * @param book - Book Object containing data that is being updated (title, genre, author, reader and cover).
     */
    @Update("UPDATE library SET title = #{title}, genre = #{genre}, author = #{author}, reader = #{reader}, cover = #{cover}")
    void update(Book book);

    /**
     * Method for deleting a book from the database.
     * @param id - UUID for identifying which book to remove.
     */
    @Delete("Delete FROM library WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}
