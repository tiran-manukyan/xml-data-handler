package main.data;

import main.SearchInput;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataFilter {

    public static List<String> filterDataBySearchInput(List<String> strings, SearchInput searchInput) {
        if (searchInput.getSearchRegex() != null) {
            return filterDataByRegex(strings, searchInput.getSearchRegex());
        } else if (searchInput.getSearchTemplate() != null) {
            return filterDataByTemplate(strings, searchInput.getSearchTemplate());
        } else {
            return strings;
        }
    }

    private static List<String> filterDataByTemplate(List<String> strings, String searchTemplate) {
        if (strings == null || strings.isEmpty()) return Collections.emptyList();

        String template;
        if (searchTemplate.charAt(0) == '*') {
            template = searchTemplate.substring(1);
        } else {
            template = searchTemplate;
        }
        return strings.stream().filter(s -> s.endsWith(template)).collect(Collectors.toList());
    }

    private static List<String> filterDataByRegex(List<String> strings, String searchRegex) {
        if (strings == null || strings.isEmpty()) return Collections.emptyList();

        Pattern pattern = Pattern.compile(searchRegex);

        return strings.stream().filter(pattern.asPredicate()).collect(Collectors.toList());
    }
}
