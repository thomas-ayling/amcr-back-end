package com.globallogic.amcr.repository.impl.contactcomponent;

import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.UUID;

@Mapper
@Alias(value = "UUIDTypeHandler")
public interface FeedbackMapper {

    @Insert("INSERT INTO feedback(id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{feedbackType}, #{firstName}, #{lastName}, #{emailAddress}, #{feedbackBody}, #{bookName}, #{bookLink})")
    void save(Feedback feedback);

    @Results(id="feedbackResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "feedback_type", javaType = String.class),
            @Arg(column = "first_name", javaType = String.class),
            @Arg(column = "last_name", javaType = String.class),
            @Arg(column = "email_address", javaType = String.class),
            @Arg(column = "feedback_body", javaType = String.class),
            @Arg(column = "book_name", javaType = String.class),
            @Arg(column = "book_link", javaType = String.class),
            @Arg(column = "download_uri", javaType = String.class),
    })
    @Select("SELECT feedback.id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id WHERE feedback.id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Feedback get(@Param("id") UUID id);

    @ResultMap("feedbackResults")
    @Select("SELECT feedback.id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id")
    List<Feedback> getAll();

    @ResultMap("feedbackResults")
    @Select("SELECT feedback.id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id ORDER BY feedback_order DESC LIMIT 10")
    List<Feedback> getLatest();

    @ResultMap("feedbackResults")
    @Select("SELECT feedback.id, feedback_type, first_name, last_name, email_address, feedback_body, book_name, book_link, download_uri FROM feedback LEFT OUTER JOIN feedback_attachments ON feedback.id = feedback_attachments.feedback_id WHERE feedback_order < #{last} ORDER BY feedback_order DESC LIMIT 10")
    List<Feedback> getOlder(int last);

    @Select("SELECT COUNT(*) FROM feedback")
    int getCount();
}
