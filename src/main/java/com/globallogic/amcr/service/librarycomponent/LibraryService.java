package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Library;
import com.globallogic.amcr.persistence.payload.librarycomponent.LibraryResponse;

public interface LibraryService {

    boolean save(Library book);

    List<LibraryResponse> getAll();

    LibraryResponse get(UUID id);
    
}
