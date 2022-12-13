package com.globallogic.amcr.mapper.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.pagecontent.Markdown;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface DiagramMapper {
    @Insert("INSERT INTO diagram (id, node_id, title, body) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{node_id}, #{title}, #{body}")
    public void save(Diagram diagram);

    @Select("SELECT * FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public Diagram getById(@Param("id") UUID id);

    @Select("SELECT * FROM diagram WHERE node_id = #{node_id}")
    public Diagram getByNode(@Param("node_id") int nodeId);

    @Select("SELECT * FROM diagram ORDER BY node_id ASC")
    public List<Diagram> getAll();

    @Update("UPDATE diagram SET title = #{title}, body = #{body} WHERE node_id = #{node_id}")
    public void update(Diagram diagram);

}
