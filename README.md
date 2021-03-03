# User Guide
## Prerequisite
JDK 11.0+

## Build
This project is structured as a Maven project. The provided wrapper scripts can be used to built the Mars Rover tool as follows:

**Note**: <em>If the user environment does not have the Maven installed, it will be downloaded for the user first, installed and then used.
Subsequent uses of mvn wrapper script use the downloaded version.</em>

#### Unix Systems
1. Extract the solution zip file
2. cd \[projectDir\]
3. ./mvnw clean install

#### Windows
1. Extract the solution zip file
2. cd \[projectDir\]
3. .\mvnw.cmd clean install

After a successful build, a jar file will be created in the target directory.

## Execution
The tool accepts command line input as well as file input.

**Note**: <em>Make sure Java is set in your PATH</em>

- Command line input
  
    1. cd %projectDir%  
    2. java -cp target\marsRover-0.0.1-SNAPSHOT.jar com.prajkta.marsRover.Operator

- File input
  1. cd %projectDir%
  2. java -cp target\marsRover-0.0.1-SNAPSHOT.jar com.prajkta.marsRover.Operator \<%InputFilePath%\>

## Testing
The unit tests will be executed during the build process.