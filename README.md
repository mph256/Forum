# Forum

A simple forum without moderation.

## Description

This forum allows you to consult discussion threads and their posts.
If you have an account, you can create new threads, new posts, edit and delete them.
It is also possible to manage your account.

## Internationalization

Available languages:
* English
* French

## Build With
* Struts2
* Jquery
* Bootstrap
* CSS
* Log4j2
* Hibernate
* JPA
* MariaDB
* Maven
* Tomcat

## Getting Started

### Dependencies

* MariaDB 11.0.2
* JDK 17.0.6 (Java 17.0.6)
* Maven 3.9.1
* Tomcat 8.5.88

### Installing

1. Clone the repository on your machine: ```git clone https://github.com/mph256/Forum.git```
2. Run the sql script using the following command from your MariaDB command prompt once you are logged in: ```source PATH\database.sql``` (replace PATH with the appropriate file path)
3. Create the .war file from the project source directory with: ```mvn package```
4. Move the generated file from the target directory to the webapps directory of your Tomcat server

### Executing

1. Start the server using the following command from the bin directory of your Tomcat server: ```startup.bat```
2. The application is accessible at the following url: http://localhost:8080/Forum/home

## Authors

[mph256](https://github.com/mph256)

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details