package com.globallogic.amcr.persistence.mapper.casestudies;

import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.TextArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface CaseStudyMapper {

    @Insert("INSERT INTO case_studies(id, spotlight, title, overview, cover_image_link, body, download_links) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{spotlight}, #{title}, #{overview}, #{coverImageLink}, #{body, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{downloadLinks, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler})")
    void save(CaseStudy caseStudy);

    @Results(id = "caseStudyResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class),
            @Arg(column = "body", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "download_links", javaType = String[].class, typeHandler = TextArrayTypeHandler.class)
    })
    @Select("SELECT * FROM case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    CaseStudy get(@Param("id") UUID id);

    @ResultMap("caseStudyResults")
    @Select("SELECT * FROM case_studies")
    List<CaseStudy> getAll();

    @Results(id = "overviewResultMap")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class)
    })
    @Select("SELECT id, spotlight, title, overview, cover_image_link FROM case_studies")
    List<CaseStudyOverview> getAllOverviews();


    @ResultMap("overviewResultMap")
    @Select("SELECT id, spotlight, title, overview, cover_image_link FROM case_studies WHERE spotlight = true")
    List<CaseStudyOverview> getSpotlitOverviews();

    @ResultMap("overviewResultMap")
    @Select("SELECT id, spotlight, title, overview, cover_image_link FROM case_studies ORDER BY case_study_order DESC LIMIT #{entries}")
    List<CaseStudyOverview> getLatestOverviews(@Param("entries") int entries);

    @Update("UPDATE case_studies SET spotlight = #{caseStudy.spotlight}, title = #{caseStudy.title}, overview = #{caseStudy.overview}, cover_image_link = #{caseStudy.coverImageLink}, body = #{caseStudy.body, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, download_links = #{caseStudy.downloadLinks, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler} WHERE id = #{caseStudyId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID caseStudyId, CaseStudy caseStudy);

    @Delete("DELETE from case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}
