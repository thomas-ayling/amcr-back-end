package com.globallogic.amcr.attachment_attempt_two.repository.attachment_attempt_two.impl.attachment_attempt_two;

import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.BinaryObject;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Metadata;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Response;
import com.globallogic.amcr.typehandler.JSONMapHandler;
import com.globallogic.amcr.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.Map;
import java.util.UUID;

@Mapper
public interface BinaryObjectMapper {

    @Insert("INSERT INTO media (id) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler})")
    void saveMedia(@Param("id") UUID id);

    @Insert("INSERT INTO metadata(id, name, type, size, crc, metadata, media_id) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}, #{name}, #{type}, #{size}, #{crc}, #{metadata, javaType=java.util.Map, jdbcType=OTHER, typeHandler=JSONMapHandler}, #{mediaId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler})")
    void saveMetadata(Metadata metadata);

    @Results(id = "binaryObjectResults")
    @ConstructorArgs({
            @Arg(column = "media", javaType = byte[].class)
    })
    @Select("SELECT media FROM media WHERE #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler} = id")
    BinaryObject get(@Param("id") UUID id);

    @Update("UPDATE media SET media = #{media} WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    void updateMedia(byte[] media, UUID id);

    @Results(id = "response")
    @ConstructorArgs({
            @Arg(column = "id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "size", javaType = long.class),
            @Arg(column = "type", javaType = String.class),
            @Arg(column = "crc", javaType = long.class),
            @Arg(column = "metadata", javaType = Map.class, typeHandler = JSONMapHandler.class),
            @Arg(column = "media_id", javaType = UUID.class, typeHandler = UUIDTypeHandler.class, id = true),
            @Arg(column = "media", javaType = byte[].class)
    })
    @Select("SELECT * FROM media JOIN metadata m ON media.id = m.media_id WHERE m.id = #{metadataId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UUIDTypeHandler}")
    Response getMedia(@Param("metadataId") UUID metadataId);
}
