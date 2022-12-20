package com.globallogic.amcr.mapper.layoutcomponent;

import com.globallogic.amcr.persistence.model.layoutcomponent.Layout;
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

    @Select("SELECT * FROM layout WHERE page = #{page}")
            public Layout getLayoutByPage(@Param("page") String page);

    @Select("SELECT * FROM layout")
    public List<Layout> getAll();

    @Delete("DELETE * FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
            public Layout getLayoutById(@Param("id") UUID id);


}
