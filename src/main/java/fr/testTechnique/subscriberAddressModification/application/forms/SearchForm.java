package fr.testTechnique.subscriberAddressModification.application.forms;

import org.springframework.stereotype.Component;

@Component
public class SearchForm {
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
