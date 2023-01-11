package com.globallogic.amcr.mapper.librarycomponent;
import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface BookMapper {

     @Insert("INSERT INTO library(id, title, genre, author, cover, email, reader, available) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{title}, #{genre}, #{author}, #{cover}, #{email}, #{reader}, #{available})")
     void save(Book book);

    @Select("SELECT title, genre, author, cover, email, reader, available FROM library WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    BookResponse get(@Param("id") UUID id);

    @Select("SELECT title, genre, author, cover, email, reader, available FROM library WHERE title = #{title}")
    BookResponse getByTitle(@Param("title") String title);

    @Select("SELECT title, genre, author, cover, email, reader, available FROM library")
    List<BookResponse> getAll();

    @Update("UPDATE library SET available = true, reader = #{reader}, email = #{email} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void reserve(Book book);

    @Update("UPDATE library SET title = #{title}, genre = #{genre}, author = #{author}, reader = #{reader}, cover = #{cover}")
    void update(Book book);

}
