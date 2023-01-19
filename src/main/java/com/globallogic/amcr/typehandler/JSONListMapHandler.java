package com.globallogic.amcr.typehandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@MappedJdbcTypes(JdbcType.OTHER)
public class JSONListMapHandler extends BaseTypeHandler<List<Map<String, Object>>> {
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<Map<String, Object>>() {};

    @Override
    public void setNonNullParameter(final PreparedStatement statement, final int columnIndex,
                                    final List<Map<String, Object>> maps, final JdbcType jdbcType)
            throws SQLException {
        final PGobject[] jsonValues = new PGobject[maps.size()];

        try {
            int i = 0;

            for (final Map<String, Object> map : maps) {
                final PGobject jsonObject = new PGobject();

                jsonObject.setType("jsonb");
                jsonObject.setValue(new ObjectMapper().writeValueAsString(map));
                jsonValues[i] = jsonObject;
                i++;
            }
        } catch (final JsonProcessingException jpe) {
            throw new SQLException("Error converting Map to JSON.", jpe);
        }
        statement.setArray(columnIndex,
                statement.getConnection().createArrayOf("jsonb", jsonValues));
    }

    @Override
    public List<Map<String, Object>> getNullableResult(final ResultSet resultSet, final String columnName) throws SQLException {
        final Array outputArray = resultSet.getArray(columnName);

        if (!resultSet.wasNull()) {
            return readMapList((String[]) outputArray.getArray());
        }

        return null;
    }

    @Override
    public List<Map<String, Object>> getNullableResult(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Array outputArray = resultSet.getArray(columnIndex);

        if (!resultSet.wasNull()) {
            return readMapList((String[]) outputArray.getArray());
        }

        return null;
    }

    @Override
    public List<Map<String, Object>> getNullableResult(final CallableStatement callableStatement,
                                                       final int columnIndex) throws SQLException {
        final Array outputArray = callableStatement.getArray(columnIndex);

        if (!callableStatement.wasNull()) {
            return readMapList((String[]) outputArray.getArray());
        }

        return null;
    }

    private List<Map<String, Object>> readMapList(final String[] array) throws SQLException {

        final List<Map<String, Object>> maps = new ArrayList<>(array.length);

        try {
            for (final String map : array) {
                maps.add(new ObjectMapper().readValue(map, MAP_TYPE));
            }
        } catch (final IOException ioe) {
            throw new SQLException("Error reading UriLink.", ioe);
        }

        return maps;
    }
}