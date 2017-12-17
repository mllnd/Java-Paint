# java-paint

[![Build Status](https://travis-ci.org/mllnd/java-paint.svg?branch=master)](https://travis-ci.org/mllnd/java-paint)

A personal project for Java Fundamentals course @ TTÜ.

This project is a Java approach to MS Paint using vectors and points for drawing objects.

## How do I run this thing?

First things first - clone this repository with `git clone`.

A very easy way to build this project is to run Gradle build task with `./gradlew` inside the project's root directory.

The generated `.jar` artifact will be placed under `build/libs/` folder.

You can run the `.jar` by using `java -jar` command or you could just open it as any other program.

## Documentation

JavaDoc is viewable at https://mllnd.github.io/java-paint/

## To-do

- [x] Utilize "drawing" status to immediately update frame
- [x] A better OOP approach to overall structure
- [x] Add color and brush size options
- [x] Implement Gradle for building project
- [x] Implement Travis CI integration
- [x] Generate JavaDoc
