package com.globallogic.amcr.repository.impl.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;

import org.apache.ibatis.annotations.*;

import java.util.Map;
import java.util.UUID;

@Mapper
public interface AttachmentMapper {

    @Insert("INSERT INTO attachments(id, name, type, size, crc, metadata, download_uri) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{type}, #{size}, #{crc}, #{metadata, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{downloadUri})")
    void saveMetadata(Attachment attachment);

    @Results(id = "binaryObjectResults")
    @ConstructorArgs({
            @Arg(column = "content", javaType = byte[].class)
    })
    @Select("SELECT content FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    byte[] getBinary(@Param("id") UUID id);

    @Update("UPDATE attachments SET content = #{attachment.content} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void updateMedia(Attachment attachment, UUID id);

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
}