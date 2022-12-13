package com.globallogic.amcr.mapper.pagecontent;

import com.globallogic.amcr.model.pagecontent.Markdown;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface MarkdownMapper {
    @Insert("INSERT INTO markdown (id, content) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{content}")
    public void save(Markdown markdown);

    @Select("SELECT * FROM markdown WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public Markdown getById(@Param("id") UUID id);

    @Select("SELECT * FROM markdown")
    public List<Markdown> getAll();

    @Select("SELECT * FROM markdown ORDER BY id DESC LIMIT 1")
    public Markdown getLatest();

    @Update("UPDATE markdown SET content = #{content} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public void update(Markdown markdown);

    @Delete("DELETE FROM markdown WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public void delete(@Param("id") UUID id);
}
