package com.globallogic.amcr.persistence.mapper.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachmentMetadata;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachmentResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface FeedbackAttachmentMapper {

    @Insert("insert into feedback_attachments(id, attachment_name, attachment_type, attachment_size, data, download_uri, feedback_id) values (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{attachmentName}, #{attachmentType}, #{attachmentSize}, #{data}, #{downloadUri}, #{feedbackId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler})")
    void save(FeedbackAttachment feedbackAttachment);

    @Select("select * from feedback_attachments where id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    FeedbackAttachmentResponse get(@Param("id") UUID id);

    @Select("select * from feedback_attachments")
    List<FeedbackAttachmentResponse> getAll();

    @Select("select attachment_name, attachment_size, download_uri from feedback_attachments where #{feedbackId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = feedback_id")
    FeedbackAttachmentMetadata getAttachmentMetadata(@Param("feedbackId") UUID feedbackId);

}
