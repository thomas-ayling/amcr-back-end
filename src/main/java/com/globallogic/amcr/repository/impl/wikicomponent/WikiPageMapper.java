package com.globallogic.amcr.repository.impl.wikicomponent;


import com.globallogic.amcr.model.wiki.Wiki;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.UUID;

@Mapper
public interface WikiPageMapper {

    @Insert("INSERT INTO wikipage (id, title, overview, sub_image, sub_title, sub_overview, body) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{title}, #{overview}, #{sub_image}, #{sub_title}, #{sub_overview}, #{body, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler})")
    void save(Wiki wikiPage);

    @Results(id = "wikiPageResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "sub_image", javaType = String.class),
            @Arg(column = "sub_title", javaType = String.class),
            @Arg(column = "sub_overview", javaType = String.class),
            @Arg(column = "body", javaType = List.class, typeHandler = JSONListMapHandler.class)
    })

    @Select("SELECT * FROM wikipage WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Wiki get(@Param("id") UUID id);

    @ResultMap("wikiPageResults")
    @Select("SELECT * FROM wikipage")
    List<Wiki> getAll();
    @Update("UPDATE wikipage SET title = #{wikiPage.title}, overview = #{wikiPage.overview}, sub_image = #{wikiPage.subImage}, sub_title = #{wikiPage.subTitle}, sub_overview = #{wikiPage.subOverview} body = #{wikiPage.body, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler} WHERE id = #{wikiPageId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID wikiPageId, Wiki wikiPage);

    @Delete("DELETE from wikipage WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}

