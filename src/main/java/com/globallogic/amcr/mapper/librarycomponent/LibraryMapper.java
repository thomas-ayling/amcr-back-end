package com.globallogic.amcr.mapper.librarycomponent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import com.globallogic.amcr.persistence.model.librarycomponent.Library;
import com.globallogic.amcr.persistence.payload.librarycomponent.LibraryResponse;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface LibraryMapper {

    @Insert("INSERT INTO library (id, title, genre, author, cover) VALUES (#{id}, #{title}, #{genre}, #{author}, #{cover}")
    public void save(Library book);

    @Select("SELECT * FROM library WHERE id = #{id}")
    public LibraryResponse get(@Param("id") UUID id);

    @Select("SELECT * FROM library WHERE title = #{title}")
    public LibraryResponse getByTitle(@Param("title") String title);

    @Select("SELECT * FROM library ORDER BY id ASC")
    public List<LibraryResponse> getAll();

    @Update("UPDATE library SET available = true, reader = #{reader} WHERE id = #{id}")
    public void reserve(Library library);

    @Update("UPDATE library SET title = #{title}, genre = #{genre}, author = #{author}, reader = #{reader}, cover = #{cover}")
    public void update(Library library);
    
}
