# parse_html_t4

Application that will parse through HTML files looking for relative links and replace them with absolute links.


### Building this Project

Before building make sure you have [maven](http://maven.apache.org) installed. Clone the project and build a jar as described below.

```bash
git clone https://github.com/emmahastings/parse_html_t4.git
cd parse_html_t4/
mvn clean package

```

### Usage

Application uses the current working directory as the source of HTML files. It will recursively traverse the file tree from this location.

```bash
cd my_html_files
java -jar parse_html.jar
```

### Authors and Contributors
Emma Hastings (@emmahastings)