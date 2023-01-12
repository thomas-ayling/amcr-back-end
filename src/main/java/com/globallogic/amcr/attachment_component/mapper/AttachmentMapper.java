package com.globallogic.amcr.attachment_component.mapper;

import com.globallogic.amcr.attachment_component.persistance.model.Attachment;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentResponse;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface AttachmentMapper {

    @Insert("insert into attachments(id, name, download_uri, content_type, size, crc, metadata, data) values (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{downloadUri}, #{contentType}, #{size}, #{crc}, #{metadata}, #{data})")
    public void insert(Attachment file);

    @Select("select * from attachments where id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public AttachmentResponse retrieve(@Param("id") UUID id);

    @Select("select name, download_uri, size, crc, metadata from attachments")
    List<AttachmentMetadata> retrieveAll();

    @Delete("delete from attachments where #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    void delete(@Param("id") UUID id);
}
