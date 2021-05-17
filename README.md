# toJsonParser

toJsonParser is a JSON parser for CSV files.

## Usage

1. Import the project into the Java IDE of your choice (Eclipse, IntelliJ, etc).

2. Run the test class
	-	Edit src/test/resources/application-test.properties with the ABSOLUT PATHS of the input file and the resulting output file (attributes "input" and "output").
	- Run src/test/java/com/example/toJsonParser/CsvToJsonTest.java.
	- Check the output file in the selected path.
	
3. Run the application
	- Edit src/main/resources/application.properties with the ABSOLUT PATHS of the input file and the resulting output file (attributes "input" and "output") in your local machine.
	- Run src/main/java/com/example/toJsonParser/ToJsonParserApplication.java.
	- Send a GET request (for example, using Postman) to localhost:8080/ftp.
	- Check the output file in the selected path.
	- The body of the response will also be the JSON parsed text.
