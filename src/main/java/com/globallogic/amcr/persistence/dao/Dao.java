package com.globallogic.amcr.persistence.dao;

import java.util.List;
import java.util.UUID;

public interface Dao<T, R> {
    T save(T t, UUID uuid);
    R get(UUID id);
    List<R> getAll();
}
