package com.globallogic.amcr.service.sharedcomponents;

import com.globallogic.amcr.model.pagecontent.Diagram;
import com.globallogic.amcr.model.sharedcomponents.TextIntro;

import java.util.List;
import java.util.UUID;

public interface TextIntroService {

    TextIntro save(TextIntro textIntro);

    TextIntro get(UUID id);

    TextIntro getByLocation(String location);

    List<TextIntro> getAll();

    TextIntro update(UUID id, TextIntro textIntro);


    void delete(UUID id);
}
