package com.globallogic.amcr.mapper.layoutcomponent;

import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface LayoutMapper {


    @Insert("INSERT INTO layout(id, element_name, x_position, y_position, width, height, static, page) values " +
            "(#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler},#{elementName}, #{xPosition}, " +
            "#{yPosition},#{width}, #{height}, #{static}, #{page});")
    public void save(Layout layout);


    @Results(id = "layoutByPage")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "element_name", javaType = String.class),
            @Arg(column = "x_Position", javaType = int.class),
            @Arg(column = "y_Position", javaType = int.class),
            @Arg(column = "width", javaType = int.class),
            @Arg(column = "height", javaType = int.class),
            @Arg(column = "static", javaType = boolean.class),
            @Arg(column = "page", javaType = String.class),
    })
    @Select("SELECT * FROM layout WHERE page = #{page}")
    List<Layout> getByPage(@Param("page") String page);

    @Results(id = "layoutByElementName")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "element_name", javaType = String.class),
            @Arg(column = "x_Position", javaType = int.class),
            @Arg(column = "y_Position", javaType = int.class),
            @Arg(column = "width", javaType = int.class),
            @Arg(column = "height", javaType = int.class),
            @Arg(column = "movable", javaType = boolean.class),
            @Arg(column = "page", javaType = String.class),
    })
    @Select("SELECT * FROM layout WHERE element_name = #{element_name}")
    public Layout getByElementName(@Param("element_name") String elementName);

    @Results(id = "getAll")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "element_name", javaType = String.class),
            @Arg(column = "x_position", javaType = int.class),
            @Arg(column = "y_position", javaType = int.class),
            @Arg(column = "width", javaType = int.class),
            @Arg(column = "height", javaType = int.class),
            @Arg(column = "static", javaType = boolean.class),
            @Arg(column = "page", javaType = String.class),
    })
    @Select("SELECT * FROM layout")
    public List<Layout> getAll();

    @Results(id = "getById")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "element_name", javaType = String.class),
            @Arg(column = "x_position", javaType = int.class),
            @Arg(column = "y_position", javaType = int.class),
            @Arg(column = "width", javaType = int.class),
            @Arg(column = "height", javaType = int.class),
            @Arg(column = "static", javaType = boolean.class),
            @Arg(column = "page", javaType = String.class),
    })
    @Select("SELECT * FROM layout WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id;")
    public Layout getById(@Param("id") UUID id);

    @Update("UPDATE layout SET x_position=#{layout.xPosition}, y_position=#{layout.yPosition}, width=#{layout.width}, height=#{layout.height} WHERE id=#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler};")
    void update(UUID id, Layout layout);

    @Update("UPDATE layout SET  x_position=#{layout.xPosition}, y_position=#{layout.yPosition}, width=#{layout.width}, height=#{layout.height}, Static=#{layout.static} WHERE page=#{layout.page}")
    void updateByPage(String page, Layout layout);
//    @Update("update layout set static={static} where profile_id ")
//    void updateIsMovable(UUID id, Layout layout);


    @Delete("DELETE FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void deleteById(@Param("id") UUID id);


}
