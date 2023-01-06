package com.globallogic.amcr.mapper.casestudies;

import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.payload.casestudies.CaseStudyOverview;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Mapper
@Alias(value = "UUIDTypeHandler, JSONMapHandler")
public interface CaseStudyMapper {

    @Insert("INSERT INTO case_studies(id, spotlight, title, overview, cover_image_link, body, pdf_link, pptx_link) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{spotlight}, #{title}, #{overview}, #{coverImageLink}, #{body, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{pdfLink}, #{pptxLink})")
    void save(CaseStudy caseStudy);

    @Results(id = "caseStudySingleResult")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class),
            @Arg(column = "body", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "pdf_link", javaType = String.class),
            @Arg(column = "pptx_link", javaType = String.class)
    })
    @Select("SELECT * FROM case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    CaseStudy get(@Param("id") UUID id);

    @Results(id = "caseStudyAllResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class),
            @Arg(column = "body", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "pdf_link", javaType = String.class),
            @Arg(column = "pptx_link", javaType = String.class)
    })
    @Select("SELECT * FROM case_studies")
    List<CaseStudy> getAll();

    @Results(id = "caseStudyAllOverviews")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class)
    })
    @Select("SELECT id, spotlight, title, overview, cover_image_link FROM case_studies")
    List<CaseStudyOverview> getAllOverviews();

    @Results(id = "spotlitCaseStudyOverviews")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "overview", javaType = String.class),
            @Arg(column = "cover_image_link", javaType = String.class)
    })
    @Select("SELECT id, spotlight, title, overview, cover_image_link FROM case_studies WHERE spotlight = true")
    List<CaseStudyOverview> getSpotlitOverviews();

    @Update("UPDATE case_studies SET spotlight = #{caseStudy.spotlight}, title = #{caseStudy.title}, overview = #{caseStudy.overview}, cover_image_link = #{caseStudy.coverImageLink}, body = #{caseStudy.body, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, pdf_link = #{caseStudy.pdfLink}, pptx_link = #{caseStudy.pptxLink} WHERE id = #{caseStudyId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID caseStudyId, CaseStudy caseStudy);

    @Delete("DELETE from case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
