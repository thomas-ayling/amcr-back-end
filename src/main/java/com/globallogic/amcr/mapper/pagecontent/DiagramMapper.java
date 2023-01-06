package com.globallogic.amcr.mapper.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Diagram;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface DiagramMapper {
    @Insert("INSERT INTO diagram (id, node_id, title, body) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{nodeId}, #{title}, #{body})")
    public void save(Diagram diagram);

    @Results(id = "getByIdDiagram")
        @ConstructorArgs({
                @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
                @Arg(column = "node_id", javaType = int.class),
                @Arg(column = "title", javaType = String.class),
                @Arg(column = "body", javaType = String.class)
        })
    @Select("SELECT * FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public Diagram get(@Param("id") UUID id);

    @Results(id = "getByNodeDiagram")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "node_id", javaType = int.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "body", javaType = String.class)
    })
    @Select("SELECT * FROM diagram WHERE node_id = #{nodeId}")
    public Diagram getByNode(@Param("nodeId") int nodeId);

    @Results(id = "getAllDiagram")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "node_id", javaType = int.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "body", javaType = String.class)
    })
    @Select("SELECT * FROM diagram ORDER BY node_id ASC")
    public List<Diagram> getAll();

    @Update("UPDATE diagram SET node_id = #{nodeId}, title = #{title}, body = #{body} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public void update(Diagram diagram);

    @Update("UPDATE diagram SET node_id = #{nodeId}, title = #{title}, body = #{body} WHERE node_id = #{nodeId}")
    public void updateByNode(Diagram diagram);

    @Delete("DELETE FROM diagram WHERE node_id = #{nodeId}")
    public void delete(@Param("nodeId") int nodeId);
}
