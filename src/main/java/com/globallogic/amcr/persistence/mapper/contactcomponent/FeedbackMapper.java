package com.globallogic.amcr.persistence.mapper.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface FeedbackMapper {

    @Insert("INSERT INTO feedback(id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{feedbackType}, #{firstName}, #{lastName}, #{emailAddress}, #{feedbackBody}, #{bookName}, #{bookLink})")
    void save(Feedback feedback);

    @Select("SELECT id, feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Feedback get(@Param("id") UUID id);

    @Select("SELECT id, feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id")
    List<Feedback> getAll();

    @Select("SELECT id, feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id ORDER BY feedback_order DESC LIMIT 10")
    List<Feedback> getLatest();

    @Select("SELECT id, feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id WHERE feedback_order < #{last} ORDER BY feedback_order DESC LIMIT 10")
    List<Feedback> getOlder(int last);
}
