package com.globallogic.amcr.pagecontent.mapper;

import com.globallogic.amcr.pagecontent.model.Markdown;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface MarkdownMapper {
    @Insert("INSERT INTO markdown (id, content) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{content}")
    public void save(Markdown markdown);

    @Select("SELECT * FROM markdown WHERE id = #{id}")
    public Markdown getById(@Param("id") int id);

    @Select("SELECT * FROM markdown")
    public List<Markdown> getAll();

    @Select("SELECT * FROM markdown ORDER BY id DESC LIMIT 1")
    public Markdown getLatest();

    @Update("UPDATE markdown SET content = #{content} WHERE id = #{id}")
    public void update(Markdown markdown);

    @Delete("DELETE FROM markdown WHERE id = #{id}")
    public void delete(@Param("id") int id);
}
