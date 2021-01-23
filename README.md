# XML Data Handler

**A console utility program that allows to find data from XML file.**

**Initial Data Example:**
```xml
<node is-file="false">
    <name>/</name>
    <children>
        <child is-file="true">
            <name>file-776194140.xml</name>
        </child>
        <child is-file="false">
            <name>dir-2096609034</name>
        </child>
        <child is-file="false">
            <name>dir-721263753</name>
            <children>
                <child is-file="true">
                    <name>file-2033711217.xhtml</name>
                </child>
                <child is-file="false">
                    <name>dir-927762094</name>
                </child>
                <child is-file="true">
                    <name>file-25645735.java</name>
                </child>
            </children>
        </child>
    </children>
</node>
```

## How to use the program

### 1. Run main.Main

#### Pass the following arguments:
__-f__ <xml_file_path><br>
__-s__ <search_input_template><br>
__-S__ <search_input_regex><br>

Example:
```arguments
 -f test-files.xml -s *.java
 -f xml-file.xml -S ".*?[a-z]{4}-\d+\.[a-z]+"
```

### 2. You can also build executable jar file using the following maven command.
```console
mvn clean compile assembly:single
```

An example of running an executable jar file:
```console
java -jar xml_data_handler.jar -f xml-file.xml -S ".*?[a-z]{4}-\d+\.[a-z]+"
java -jar xml_data_handler.jar -f xml-file.xml -s \".*xml\"
```