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


    @Insert("INSERT INTO layout(id, element_name, x_position, y_position, width, height, movable, page) values " +
            "(#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler},#{elementName}, #{xPosition}, " +
            "#{yPosition},#{width}, #{height}, #{movable}, #{page});")
    public void save(Layout layout);

//    @Results(id = "layoutByProfileId")
//    @ConstructorArgs({
//            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
//            @Arg(column = "element_name", javaType = String.class),
//            @Arg(column = "x_Position", javaType = int.class),
//            @Arg(column = "y_Position", javaType = int.class),
//            @Arg(column = "width", javaType = int.class),
//            @Arg(column = "height", javaType = int.class),
//            @Arg(column = "movable", javaType = boolean.class),
//            @Arg(column = "page", javaType = String.class),
//            @Arg(column = "profile_id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true)
//    })
//    @Select("SELECT * FROM layout WHERE profileId = #{profileId}")
//    public Layout getLayoutByProfileById(@Param("profileId") UUID profileId);
@Results(id = "layoutByProfileId")
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
    @Select("SELECT * FROM layout WHERE page = #{page}")
    public Layout getByPage(@Param("page") String page);

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
    })    @Select("SELECT * FROM layout WHERE element_name = #{element_name}")
    public Layout getByElementName(@Param("element_name") String elementName);

    @Results(id = "getAll")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "element_name", javaType = String.class),
            @Arg(column = "x_Position", javaType = int.class),
            @Arg(column = "y_Position", javaType = int.class),
            @Arg(column = "width", javaType = int.class),
            @Arg(column = "height", javaType = int.class),
            @Arg(column = "movable", javaType = boolean.class),
            @Arg(column = "page", javaType = String.class),
    })@Select("SELECT * FROM layout")
    public List<Layout> getAll();

    @Update("update layout set x_position=#{x_position}, y_position=#{y_position}, width=#{width}, height=#{height}, where id=#{id}")
    void update(UUID id, Layout layout);

    @Update("update layout set movable={movable} where profile_id ")
    void updateIsMovable(UUID id, Layout layout);



    @Delete("DELETE * FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public Layout deleteLayoutById(@Param("id") UUID id);

    @Delete("delete * from layout where element_name=#{element_name}")
    void deleteByElementName(@Param("element_name") String elementName);


}
