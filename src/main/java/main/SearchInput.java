package main;

public class SearchInput {

    private final String searchTemplate;

    private final String searchRegex;

    public SearchInput(String searchTemplate, String searchRegex) {
        if (searchTemplate != null && searchRegex != null) {
            throw new IllegalArgumentException("Must be specified only one filter");
        }
        this.searchTemplate = searchTemplate;
        this.searchRegex = searchRegex;
    }

    public String getSearchTemplate() {
        return searchTemplate;
    }

    public String getSearchRegex() {
        return searchRegex;
    }
}
