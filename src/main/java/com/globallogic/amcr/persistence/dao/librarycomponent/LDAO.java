package com.globallogic.amcr.persistence.dao.librarycomponent;

import java.util.List;
import java.util.UUID;

public interface LDAO<T, R> {
    void save(R t);
    R get(UUID id);
    List<R> getAll();
}
