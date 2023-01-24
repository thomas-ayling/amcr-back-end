package com.globallogic.amcr.repository.impl.casestudies;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.TextArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CaseStudyMapper {

    @Insert("INSERT INTO case_studies(id, spotlight, title, overview, cover_image_id, body, attachment_ids) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{spotlight}, #{title}, #{overview}, #{coverImageId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}}, #{body, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler}, #{attachmentIds, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler})")
    void save(CaseStudy caseStudy);

    @Results(id = "caseStudyResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "body", javaType = List.class, typeHandler = JSONListMapHandler.class),
            @Arg(column = "attachment_ids", javaType = UUID[].class, typeHandler = UUIDArrayTypeHandler.class)
    })
    @Select("SELECT * FROM case_studies WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
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
            @Arg(column = "cover_image_id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true)
    })
    @Select("SELECT id, spotlight, title, overview, cover_image_id FROM case_studies")
    List<CaseStudyOverview> getAllOverviews();


    @ResultMap("overviewResultMap")
    @Select("SELECT id, spotlight, title, overview, cover_image_id FROM case_studies WHERE spotlight = true")
    List<CaseStudyOverview> getSpotlitOverviews();

    @ResultMap("overviewResultMap")
    @Select("SELECT id, spotlight, title, overview, cover_image_id FROM case_studies ORDER BY case_study_order DESC LIMIT #{entries}")
    List<CaseStudyOverview> getLatestOverviews(@Param("entries") int entries);

    @Update("UPDATE case_studies SET spotlight = #{caseStudy.spotlight}, title = #{caseStudy.title}, overview = #{caseStudy.overview}, cover_image_id = #{caseStudy.coverImageId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, body = #{caseStudy.body, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler}, attachmentIds = #{caseStudy.downloadLinks, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=UUIDArrayTypeHandler} WHERE id = #{caseStudyId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID caseStudyId, CaseStudy caseStudy);

    @Delete("DELETE from case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}
