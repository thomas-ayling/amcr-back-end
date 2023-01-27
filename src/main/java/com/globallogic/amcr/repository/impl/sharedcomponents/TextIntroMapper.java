package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.sharedcomponents.TextIntro;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TextIntroMapper {
    @Insert("INSERT INTO text_intro (id, title, description, location) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{title}, #{description}, #{location})")
    void save(TextIntro textIntro);

    @Results(id = "TextIntroResponse")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "description", javaType = String.class),
            @Arg(column = "location", javaType = String.class)
    })
    @Select("SELECT * FROM text_intro WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    TextIntro get(@Param("id") UUID id);

    @ResultMap("TextIntroLocationResponse")
    @Select("SELECT * FROM text_intro WHERE #{location} = location")
    TextIntro getByLocation(@Param("location") String location);

    @ResultMap("TextIntroResponse")
    @Select("SELECT * FROM text_intro")
    List<TextIntro> getAll();

    @Update("UPDATE text_intro SET title = #{title}, description = #{description}, location = #{location} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(@Param("id")UUID id, TextIntro textIntro);

    @Delete("DELETE FROM text_intro WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void delete(@Param("id") UUID id);
}
