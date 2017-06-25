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

The application reads a URL from a config.yaml file and uses this to replace relative links.
The original file will update relative links in img, a and link tags.
The application creates a backup of the original file e.g. index.html_orig. It also outputs a log: parse_html.log. 


### Authors and Contributors
Emma Hastings (@emmahastings)