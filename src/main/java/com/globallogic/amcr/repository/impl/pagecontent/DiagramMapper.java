package com.globallogic.amcr.repository.impl.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DiagramMapper {
    @Insert("INSERT INTO diagram (id, node_position, title, body) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{nodePosition}, #{title}, #{body})")
    void save(Diagram diagram);

    @Results(id = "getByIdDiagram")
        @ConstructorArgs({
                @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
                @Arg(column = "node_position", javaType = int.class),
                @Arg(column = "title", javaType = String.class),
                @Arg(column = "body", javaType = String.class)
        })
    @Select("SELECT * FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Diagram get(@Param("id") UUID id);

    @Results(id = "getByNodeDiagram")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "node_position", javaType = int.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "body", javaType = String.class)
    })
    @Select("SELECT * FROM diagram WHERE node_position = #{nodePosition}")
    Diagram getByNode(@Param("nodePosition") int nodePosition);

    @Results(id = "getAllDiagram")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "node_position", javaType = int.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "body", javaType = String.class)
    })
    @Select("SELECT * FROM diagram ORDER BY node_position ASC")
    List<Diagram> getAll();

    @Update("UPDATE diagram SET node_position = #{nodePosition}, title = #{title}, body = #{body} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID id, Diagram diagram);

    @Update("UPDATE diagram SET node_position = #{nodePosition}, title = #{diagram.title}, body = #{diagram.body} WHERE node_position = #{nodePosition}")
    void updateByNode(int nodePosition, Diagram diagram);

    @Delete("DELETE FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void delete(@Param("id") UUID id);
}
