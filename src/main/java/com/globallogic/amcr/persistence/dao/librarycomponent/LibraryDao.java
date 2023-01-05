package com.globallogic.amcr.persistence.dao.librarycomponent;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.globallogic.amcr.mapper.librarycomponent.LibraryMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.librarycomponent.Library;
import com.globallogic.amcr.persistence.payload.librarycomponent.LibraryResponse;

@Repository
public class LibraryDao implements Dao<Library, LibraryResponse>{

    private final LibraryMapper libraryMapper;

    public LibraryDao (LibraryMapper libraryMapper) {
        this.libraryMapper = libraryMapper;
    }

    public void save(Library library, UUID libraryId) {
        try {
            library.setId(libraryId);
            libraryMapper.save(library);
        } catch (Exception e) {
            throw new RuntimeException("Could not save book", e);
        }
    }
    
    public LibraryResponse get(UUID id) {
        return libraryMapper.get(id);
    }

    public List<LibraryResponse> getAll() {
        return libraryMapper.getAll();
    }
}
