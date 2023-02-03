package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface MainCarouselMapper {

    @Insert("INSERT INTO main_carousel(id, titles, location, descriptions, image_ids) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{titles, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, #{location}, #{descriptions, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, #{imageIds, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler})")
    void save(MainCarousel mainCarousel);

    @Select("SELECT * FROM main_carousel WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    MainCarousel get(@Param("id") UUID id);

    @Results(id = "mainCarouselResults")
    @Select("SELECT * FROM main_carousel")
    List<MainCarousel> getAll();

    @Select("SELECT * FROM main_carousel WHERE location = #{location}")
    MainCarousel getByLocation(@Param("location") String location);

    @Update("UPDATE main_carousel SET titles = #{titles, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler}, location = #{mainCarousel.overview}, imageids = #{imageids, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler}, location = #{mainCarousel.location}, descriptions = #{descriptions, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID id, MainCarousel mainCarousel);

    @Delete("DELETE from main_carousel WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
