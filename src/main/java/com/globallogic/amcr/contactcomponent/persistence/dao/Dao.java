package com.globallogic.amcr.contactcomponent.persistence.dao;

import java.util.List;
import java.util.UUID;

public interface Dao<T, R> {
    void save(T t, UUID id);

    R get(UUID id);

    List<R> getAll();
}
