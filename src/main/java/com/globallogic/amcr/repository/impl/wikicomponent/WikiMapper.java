package com.globallogic.amcr.repository.impl.wikicomponent;


import com.globallogic.amcr.model.wiki.Wiki;
import com.globallogic.amcr.typehandler.UUIDArrayTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface WikiMapper {

    @Insert("INSERT INTO wiki (id, title, overview, sub_image, sub_title, sub_overview, diagram) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{title}, #{overview}, #{subImage, javaType=object[], jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler}, #{subTitle}, #{subOverview}, #{diagram, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} )")
    void save(Wiki wiki);

    @Results(id = "wikiResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "sub_image", javaType = UUID[].class, typeHandler = UUIDArrayTypeHandler.class),
            @Arg(column = "sub_title", javaType = String.class),
            @Arg(column = "sub_overview", javaType = String.class),
            @Arg(column = "diagram", javaType = UUID.class, typeHandler = UUIDTypeHandler.class),

    })

    @Select("SELECT * FROM wiki WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Wiki get(@Param("id") UUID id);

    @ResultMap("wikiResults")
    @Select("SELECT * FROM wiki")
    List<Wiki> getAll();

    @Update("UPDATE wiki SET title = #{wiki.title}, overview = #{wiki.overview}, sub_image = #{wiki.subImage, javaType=object[], jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler}, sub_title = #{wiki.subTitle}, sub_overview = #{wiki.subOverview}, diagram = #{wiki.diagram, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}  WHERE id = #{wiki.id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID wikiId, Wiki wiki);

    @Delete("DELETE from wiki WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}

