package com.globallogic.amcr.repository.impl.contacts;

import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ContactsMapper {
    @Insert("INSERT INTO contacts (id, spotlight, image_id, full_name, title, description, email, order_num) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{spotlight}, #{imageId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{fullName}, #{title}, #{description}, #{email}, #{orderNum})")
    void save(Contacts contact);

    @Results(id = "contactsResults")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "spotlight", javaType = boolean.class),
            @Arg(column = "image_id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "full_name", javaType = String.class),
            @Arg(column = "title", javaType = String.class),
            @Arg(column = "description", javaType = String.class),
            @Arg(column = "email", javaType = String.class),
            @Arg(column = "order_num", javaType = int.class)
    })
    @Select("SELECT * FROM contacts WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Contacts get(@Param("id") UUID id);

    @ResultMap("contactsResults")
    @Select("SELECT * FROM contacts ORDER BY order_num")
    List<Contacts> getAll();

    @ResultMap("contactsResults")
    @Select("SELECT * FROM contacts WHERE spotlight = true ORDER BY order_num")
    List<Contacts> getSpotlitContacts();

    @Update("UPDATE contacts SET spotlight = #{contact.spotlight}, image_id=#{contact.imageId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, full_name = #{contact.fullName}, title = #{contact.title}, description = #{contact.description}, email=#{contact.email}, order_num=#{contact.orderNum} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void update(@Param("id") UUID id, Contacts contact);

    @Delete("DELETE FROM contacts WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void delete(@Param("id") UUID id);
}