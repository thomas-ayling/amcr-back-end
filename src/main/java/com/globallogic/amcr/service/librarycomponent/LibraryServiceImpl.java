package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.globallogic.amcr.persistence.dao.librarycomponent.LibraryDao;
import com.globallogic.amcr.persistence.model.librarycomponent.Library;
import com.globallogic.amcr.persistence.payload.librarycomponent.LibraryResponse;

public class LibraryServiceImpl implements LibraryService {
    private final LibraryDao libraryDao;

    public LibraryServiceImpl(LibraryDao libraryDao){
        this.libraryDao = libraryDao;
    }

    @Transactional
    public boolean save(Library book) {
        try {
            UUID libraryId = UUID.randomUUID();
            libraryDao.save(book, libraryId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error saving book to library database", e);
        }
    }

    @Transactional(readOnly = true)
    public List<LibraryResponse> getAll() {
        return libraryDao.getAll();
    }

    @Transactional(readOnly = true)
    public LibraryResponse get(UUID id) {
        return libraryDao.get(id);
    }

}
