package com.globallogic.amcr.model.wikipage;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public class WikiPage extends WikiPageOverview {

    @NotNull
    private List<Map<?, ?>> body;


    /**
     * @param id             the id of the object
     * @param title          the title of the wiki page
     * @param overview       the text intro to be displayed in the main wiki page
     * @param subImage     image to be used in each wiki page
     * @param subTitle       the secondary title for the body section
     * @param subOverview    the secondary overview in the body section
     * @param body           the main data to be displayed on the single page stored as JSON
     * @param diagram        using the diagram for each page
     */

    public WikiPage(UUID id, String title, String overview, String subImage, String subTitle, String subOverview, List<Map<?, ?>> body, String diagram) {
        super(id, title, overview, subImage, subTitle, subOverview);
        setBody(body);
    }

    public List<Map<?, ?>> getBody() {
        return body;
    }

    public WikiPage(){
        super();
    }

    public void setBody(List<Map<?, ?>> body) {
        this.body = Assert.assertNotNull(body, "Wiki page body cannot be null");
    }


    @Override
    public String toString(){
        return "WikiPage{" +
                "body=" + body + super.toString();
    }


}
