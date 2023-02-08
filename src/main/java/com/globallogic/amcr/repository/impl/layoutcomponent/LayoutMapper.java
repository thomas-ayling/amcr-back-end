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

    /**
     * Method for saving new layout into the database.
     *
     * @param layout - Layout object containing data for the new layout entry into the database.
     */
    @Insert("INSERT INTO layout(id, name, components) values " +
            "(#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler},#{name},#{components, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler});")
    void save(Layout layout);

    /**
     * Method that allows you to all layouts.
     *
     * @return - Layout list containing all the information of the layouts.
     */
    @Results(id = "getAll")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),

    })
    @Select("SELECT * FROM layout")
    List<Layout> getAll();

    /**
     * Method that retrieves one layout.
     *
     * @param id - UUID layout id for identifying layout.
     * @return - Layout object containing all the information for selected layout.
     */
    @Results(id = "getById")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),

    })
    @Select("SELECT * FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler};")
    Layout get(@Param("id") UUID id);

    /**
     * Method that retrieves one layout.
     *
     * @param name - String book named for identifying layout.
     * @return layout - Layout Object containing all the information for selected layout.
     */
    @Results(id = "getPage")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "components", javaType = List.class, typeHandler = JSONListMapHandler.class),

    })
    @Select("SELECT * FROM layout WHERE name = #{name};")
    Layout getPage(@Param("name") String name);

    /**
     * Method that allows you to update a layout.
     *
     * @param id        - UUID for identifying which book is being updated.
     * @param newLayout - Layout Object containing data that is being updated.
     */
    @Update("UPDATE layout SET name = #{newLayout.name}, components = #{newLayout.components, javaType=java.util.List, jdbcType=OTHER, typeHandler=JSONListMapHandler} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(UUID id, Layout newLayout);

    /**
     * Method for deleting a Layout from the database.
     *
     * @param id - UUID for identifying which layout to remove.
     */
    @Delete("DELETE FROM layout WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void deleteById(@Param("id") UUID id);


}



