package com.globallogic.amcr.mapper;

import com.globallogic.amcr.model.Attachment;
import com.globallogic.amcr.payload.AttachmentMetadata;
import com.globallogic.amcr.payload.AttachmentResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface FileMapper {

    @Insert("insert into files(id, file_name, file_type, file_size, data, download_uri, feedback_id) values(#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{fileName}, #{fileType}, #{fileSize}, #{data}, #{downloadUri}, #{feedbackId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler})")
    public int save(Attachment attachment);

    @Select("select * from files where id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    public AttachmentResponse getFile(@Param("id") UUID id);

    @Select("select file_name, file_size, download_uri from files where #{feedbackId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = feedback_id")
    public AttachmentMetadata getFileMetadata(@Param("feedbackId") UUID feedbackId);
}
