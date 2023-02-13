package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import com.globallogic.amcr.typehandler.TextArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface MainCarouselMapper {

    @Insert("INSERT INTO main_carousel(id, titles, location, descriptions, image_ids) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{titles, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, #{location}, #{descriptions, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, #{imageIds, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler})")
    void save(MainCarousel mainCarousel);

    @Results(id = "mainCarouselSingleResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "titles", javaType = String[].class, typeHandler = TextArrayTypeHandler.class),
            @Arg(column = "location", javaType = String.class),
            @Arg(column = "descriptions", javaType = String[].class, typeHandler = TextArrayTypeHandler.class),
            @Arg(column = "image_ids", javaType = UUID[].class, typeHandler = UUIDArrayTypeHandler.class)
    })
    @Select("SELECT * FROM main_carousel WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    MainCarousel get(@Param("id") UUID id);

    @Results(id = "mainCarouselResults")
    @Select("SELECT * FROM main_carousel")
    List<MainCarousel> getAll();

    @Results(id = "mainCarouselLocationResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "titles", javaType = String[].class, typeHandler = TextArrayTypeHandler.class),
            @Arg(column = "location", javaType = String.class),
            @Arg(column = "descriptions", javaType = String[].class, typeHandler = TextArrayTypeHandler.class),
            @Arg(column = "image_ids", javaType = UUID[].class, typeHandler = UUIDArrayTypeHandler.class)
    })
    @Select("SELECT * FROM main_carousel WHERE location = #{location}")
    MainCarousel getByLocation(@Param("location") String location);

    @Update("UPDATE main_carousel SET titles = #{mainCarousel.titles, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, location = #{mainCarousel.location}, descriptions = #{mainCarousel.descriptions, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, image_ids = #{mainCarousel.imageIds, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(@Param("id") UUID id, MainCarousel mainCarousel);

    @Delete("DELETE from main_carousel WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
