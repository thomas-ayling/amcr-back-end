package com.globallogic.amcr.repository.impl.pagecontent;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DiagramMapper {
    @Insert("INSERT INTO diagram (id, nodes) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{nodes, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler})")
    void save(Diagram diagram);

    @Results(id = "diagramResponse")
        @ConstructorArgs({
                @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
                @Arg(column = "nodes", javaType = List.class, typeHandler = JSONListMapHandler.class)
        })
    @Select("SELECT * FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Diagram get(@Param("id") UUID id);

    @ResultMap("diagramResponse")
    @Select("SELECT * FROM diagram")
    List<Diagram> getAll();

    @Update("UPDATE diagram SET nodes = #{diagram.nodes, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(@Param("id")UUID id, Diagram diagram);

    @Delete("DELETE FROM diagram WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void delete(@Param("id") UUID id);
}
