package com.globallogic.amcr.mapper.attachmentcomponent;

import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface AttachmentMapper {

    @Insert("INSERT INTO attachments(id, name, download_uri, content_type, size, crc, metadata, data) VALUES (#{id, " +
            "javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{downloadUri}, " +
            "#{contentType}, #{size}, #{crc}, #{metadata, javaType=java.util.Map, jdbcType=OTHER, " +
            "typeHandler=JSONMapHandler}, #{data})")
     void save(Attachment file);

    @Results(id = "attachmentResponse")
    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content_type", javaType = String.class),
            @Arg(column = "data", javaType = byte[].class)
    })

    @Select("SELECT * FROM attachments WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER," +
            " typeHandler=UUIDTypeHandler}")
    AttachmentResponse get(@Param("id") UUID id);

    @Results(id = "attachmentMetadataResponse")
    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "download_uri", javaType = String.class),
            @Arg(column = "size", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class)
    })
    @Select("SELECT name, download_uri, size, crc, metadata FROM attachments")
    List<AttachmentMetadata> getAllMetadata();

    @Results(id = "attachmentResponse")
    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "content_type", javaType = String.class),
            @Arg(column = "data", javaType = byte[].class),
            @Arg(column = "size", javaType = String.class),
            @Arg(column = "readable_size", javaType = String.class)
    })

    @Select("SELECT * FROM attachments")
    List<AttachmentResponse> getAll();

    @Delete("DELETE FROM attachments WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, " +
            "typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
