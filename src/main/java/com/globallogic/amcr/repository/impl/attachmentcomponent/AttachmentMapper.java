package com.globallogic.amcr.repository.impl.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface AttachmentMapper {

    @Insert("INSERT INTO attachments(id, name, type, size, crc, metadata, download_uri) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{type}, #{size}, #{crc}, #{metadata, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{downloadUri})")
    void save(Attachment attachment);

    @Results(id = "binaryObjectResults")
    @ConstructorArgs({
            @Arg(column = "content", javaType = byte[].class)
    })
    @Select("SELECT content FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    byte[] getBinary(@Param("id") UUID id);

    @Update("UPDATE attachments SET content = #{content} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(byte[] content, UUID id);

    @Results(id = "response")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "content", javaType = byte[].class),
            @Arg(column = "download_uri", javaType = String.class)
    })
    @Select("SELECT * FROM attachments WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Attachment get(@Param("id") UUID id);

    @Delete("DELETE FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, " +
            "typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);

    @Results(id = "attachmentResponse")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class)
    })
    @Select("SELECT id, name, size, type FROM attachments ORDER BY attachment_sequence DESC LIMIT 20")
    List<AttachmentResponse> getAll();

    @Results(id = "responseMetadata")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "download_uri", javaType = String.class)
    })
    @Select("SELECT id, name, size, type, crc, metadata, download_uri FROM attachments WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    AttachmentMetadata getMetadata(@Param("id") UUID id);
}