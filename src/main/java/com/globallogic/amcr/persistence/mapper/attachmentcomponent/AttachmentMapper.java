package com.globallogic.amcr.persistence.mapper.attachmentcomponent;

import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.TextArrayTypeHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface AttachmentMapper {

    @Insert("insert into attachments(id, name, download_uri, content_type, size, crc, metadata, data) values (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{downloadUri}, #{contentType}, #{size}, #{crc}, #{metadata, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{data})")
    public void insert(Attachment file);

    @Results(id = "attachmentResponse")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "download_uri", javaType = String.class),
            @Arg(column = "content_type", javaType = String.class),
            @Arg(column = "size", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "data", javaType = byte[].class)
    })
    @Select("select * from attachments where id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public AttachmentResponse retrieve(@Param("id") UUID id);

    @Results(id = "attachmentMetadataResponse")
    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "download_uri", javaType = String.class),
            @Arg(column = "size", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class)
    })
    @Select("select name, download_uri, size, crc, metadata from attachments")
    List<AttachmentMetadata> retrieveAll();

    @Delete("delete from attachments where #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
