package com.globallogic.amcr.mapper.pagecontent;

import com.globallogic.amcr.persistence.model.pagecontent.Markdown;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface MarkdownMapper {
    @Insert("INSERT INTO markdown (id, order_number, name, content) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{orderNumber}, #{name}, #{content})")
    public void save(Markdown markdown);

    @Results(id = "getByIdMarkdown")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "order_number", javaType = int.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content", javaType = String.class)
    })
    @Select("SELECT * FROM markdown WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public Markdown get(@Param("id") UUID id);

    @Results(id = "getAllMarkdown")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "order_number", javaType = int.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content", javaType = String.class)
    })
    @Select("SELECT * FROM markdown")
    public List<Markdown> getAll();

    @Results(id = "getLatestMarkdown")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "order_number", javaType = int.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content", javaType = String.class)
    })
    @Select("SELECT * FROM markdown ORDER BY order_number DESC LIMIT 1")
    public Markdown getLatest();

    @Results(id = "getByNameMarkdown")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "order_number", javaType = int.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content", javaType = String.class)
    })
    @Select("SELECT * FROM markdown WHERE name = #{name}")
    public Markdown getByName(@Param("name") String name);

    @Update("UPDATE markdown SET name = #{name}, content = #{content} WHERE order_number = #{orderNumber}")
    public void update(Markdown markdown);

    @Delete("DELETE FROM markdown WHERE order_number = #{orderNumber}")
    public void delete(@Param("orderNumber") int orderNumber);
}