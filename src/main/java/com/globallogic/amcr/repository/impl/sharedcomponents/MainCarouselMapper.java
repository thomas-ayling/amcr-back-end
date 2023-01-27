package com.globallogic.amcr.repository.impl.sharedcomponents;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.sharedcomponents.MainCarousel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface MainCarouselMapper {

    @Insert("INSERT INTO main_carousel(id, titles, location, descriptions, imageids) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{titles}, #{location}, #{descriptions}, #{imageIds, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler})")
    void save(MainCarousel mainCarousel);

    @Select("SELECT * FROM main_carousel WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    MainCarousel get(@Param("id") UUID id);

    @ResultMap("mainCarouselResults")
    @Select("SELECT * FROM main_carousel")
    List<MainCarousel> getAll();

    @Select("SELECT * FROM main_carousel WHERE location = #{location}")
    MainCarousel getByLocation(@Param("location") String location);

    @Update("UPDATE main_carousel SET titles = #{mainCarousel.titles}, location = #{mainCarousel.overview}, imageids = #{mainCarousel.imageids}, location = #{mainCarousel.location}, descriptions = #{mainCarousel.descriptions} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID id, MainCarousel mainCarousel);

    @Delete("DELETE from main_carousel WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
