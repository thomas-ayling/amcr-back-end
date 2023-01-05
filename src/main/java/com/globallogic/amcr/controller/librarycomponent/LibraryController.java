package com.globallogic.amcr.controller.librarycomponent;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.amcr.persistence.payload.librarycomponent.LibraryResponse;
import com.globallogic.amcr.service.librarycomponent.LibraryService;

@RestController
@RequestMapping("/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping("/get-all")
    public List<LibraryResponse> getMany() {
        return libraryService.getAll();
    }

}
