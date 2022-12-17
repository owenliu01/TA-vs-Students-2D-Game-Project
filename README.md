# TAs vs Students
---

TAs vs Students:
    - Owen
    - Gurnoor
    - Thomas
    - Yoobin

# Gameplay Instructions

**Build**
`Maven` is required to build, test, and run the game. Please follow the instructions to setup Maven:
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

Build the Maven project with:
`mvn compile`

**Run**
To build and then run the game, please run:
`mvn clean compile exec:java`
The game will automatically open to the start game menu.

**Jar File of Game**
To create the jar file of the game, run: `mvn clean install`. This will delete any old compiled Java files, then compile,test, and package the project. As well as create the jar file in the project directory.

**Javadocs**
In order to create the javadoc files of the game run: `mvn javadoc:javadoc`. This will be created in the target location of the project directory.

**Rules**
You must use the arrow keys to move your character. The character will remain within bounds of the map and must collect all rewards in order to finish the game.
The game ends when all rewards (excluding bonus rewards which appear sporadically) are collected and the player walks to the exit tile on the top right.
Collecting rewards increases your sanity or health. Hitting an obstacle, such as paper, will reduce your sanity. If you hit a moving student, the game ends immediately. 

For additional information, please refer to the start game menu upon running the game.

**Video:** https://www.youtube.com/watch?v=JDgBeJZ_SJ0

**Test**
Tests are included to verify robust functionality. To test the game, please run:
`mvn test`
If all tests pass, there should be no error message displayed. If you use VsCode, the tests will all be marked in green.

**Package** 
To package the game into a .jar file, please run: 
`mvn package` 
This will also execute all tests to ensure correct functionality. 
