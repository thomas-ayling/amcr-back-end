package com.globallogic.amcr.repository.impl.wikicomponent;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.wikipage.WikiPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.TextArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface WikiPageMapper {

    @Insert("INSERT INTO wikipage (id,)")
    void save(WikiPage wikiPage);

    @Select("SELECT * FROM wikipage WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    WikiPage get(@Param("id") UUID id);

    @ResultMap("wikiPageResults")
    @Select("SELECT * FROM wikipage")
    List<CaseStudy> getAll();
    @Update("UPDATE wikipage SET title = #{caseStudy.title}, overview = #{caseStudy.overview}, cover_image_link = #{caseStudy.coverImageLink}, body = #{caseStudy.body, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler}, download_links = #{caseStudy.downloadLinks, javaType=java.util.Arrays, jdbcType=OTHER, typeHandler=TextArrayTypeHandler} WHERE id = #{caseStudyId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID caseStudyId, CaseStudy caseStudy);

    @Delete("DELETE from case_studies WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

}

