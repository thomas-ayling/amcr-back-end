package com.globallogic.amcr.repository.impl.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AttachmentMapper {

    @Insert("INSERT INTO attachments(id, name, type, size, crc) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{type}, #{size}, #{crc})")
    void save(Attachment attachment);

    @Results(id = "attachmentContentResponse")
    @ConstructorArgs({
            @Arg(column = "content", javaType = byte[].class)
    })
    @Select("SELECT content FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    byte[] getContent(@Param("id") UUID id);

    @Update("UPDATE attachments SET content = #{content} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(byte[] content, UUID id);

    @Results(id = "attachmentUploadResponse")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "content", javaType = byte[].class)
    })
    @Select("SELECT * FROM attachments WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Attachment get(@Param("id") UUID id);

    @Delete("DELETE FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, " +
            "typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

    @Results(id = "attachmentGetAllMetadataResponse")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class)
    })
    @Select("SELECT id, name, size, type, crc FROM attachments ORDER BY attachment_sequence DESC LIMIT 20")
    List<Attachment> getAll();

    @Results(id = "attachmentGetMetadataByID")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class)
    })
    @Select("SELECT id, name, size, type, crc FROM attachments WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Attachment getMetadata(@Param("id") UUID id);
}