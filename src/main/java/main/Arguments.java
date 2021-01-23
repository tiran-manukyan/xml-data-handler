package main;

import org.apache.commons.cli.*;

import java.io.File;

public class Arguments {

    private final File file;

    private final SearchInput searchInput;

    public Arguments(File file, SearchInput searchInput) {
        this.file = file;
        this.searchInput = searchInput;
    }

    public File getFile() {
        return file;
    }

    public SearchInput getSearchInput() {
        return searchInput;
    }

    public static Arguments resolve(String[] args) throws ParseException {
        Options options = new Options();

        Option filePathOption = new Option("f", "filePath", true, "file path");
        filePathOption.setRequired(true);
        options.addOption(filePathOption);

        Option searchTemplateOption = new Option("s", "searchTemplate", true, "search input by template");
        searchTemplateOption.setRequired(false);
        options.addOption(searchTemplateOption);

        Option searchRegexOption = new Option("S", "searchRegex", true, "search input by regex");
        searchRegexOption.setRequired(false);
        options.addOption(searchRegexOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String filePath = cmd.getOptionValue("filePath");
        String searchTemplate = cmd.getOptionValue("searchTemplate");
        String searchRegex = cmd.getOptionValue("searchRegex");

        File file = new File(filePath);

        if (!isXmlFile(file)) {
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }

        return new Arguments(file, new SearchInput(searchTemplate, searchRegex));
    }

    private static boolean isXmlFile(File file) {
        return file.isFile() && getExtensionFromFile(file).equalsIgnoreCase("xml");
    }

    private static String getExtensionFromFile(File file) {
        String extension = "";
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        if (i != -1) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
