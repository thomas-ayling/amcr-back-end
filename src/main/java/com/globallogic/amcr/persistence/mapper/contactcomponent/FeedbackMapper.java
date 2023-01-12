package com.globallogic.amcr.persistence.mapper.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface FeedbackMapper {

    @Insert("INSERT INTO feedback(id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{feedbackType}, #{firstName}, #{lastName}, #{emailAddress}, #{feedbackBody}, #{bookName}, #{bookLink})")
    void save(Feedback feedback);

    @Select("SELECT feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN files ON feedback.id = files.feedback_id WHERE  #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    FeedbackResponse get(@Param("id") UUID id);

    @Select("SELECT feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN files ON feedback.id = files.feedback_id limit 1")
    List<FeedbackResponse> getAll();

    @Select("SELECT feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN files ON feedback.id = files.feedback_id ORDER BY feedback_order DESC LIMIT 10")
    List<FeedbackResponse> getLatest();

    @Select("SELECT feedback_order, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN files ON feedback.id = files.feedback_id WHERE feedback_order < #{last} ORDER BY feedback_order DESC LIMIT 10")
    List<FeedbackResponse> getOlder(int last);
}
