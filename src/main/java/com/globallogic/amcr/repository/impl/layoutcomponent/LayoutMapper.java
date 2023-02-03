package com.globallogic.amcr.repository.impl.layoutcomponent;

import com.globallogic.amcr.model.Layout;
import com.globallogic.amcr.typehandler.JSONListMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface LayoutMapper {


    @Insert("INSERT INTO layout(id, name, components, static) values " +
            "(#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler},#{name},#{components, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler},  #{static});")
    void save(Layout layout);


    @Results(id = "getAll")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),
            @Arg(column = "static", javaType = boolean.class)
    })
    @Select("SELECT * FROM layout")
    List<Layout> getAll();

    @Results(id = "getById")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),
            @Arg(column = "static", javaType = boolean.class)
    })
    @Select("SELECT * FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler};")
    Layout get(@Param("id") UUID id);

    @Results(id = "getPage")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),
            @Arg(column = "static", javaType = boolean.class)
    })
    @Select("SELECT * FROM layout WHERE name = #{name};")
    Layout getPage(@Param("name") String name);


    @Update("UPDATE layout SET name = #{newLayout.name}, components = #{newLayout.components, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler}, static=#{newLayout.Static} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID id, Layout newLayout);


    @Delete("DELETE FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void deleteById(@Param("id") UUID id);


}



