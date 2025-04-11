# Script Execution Guide

## Introduction
This repository contains a script that automates specific tasks. To execute the script properly, you need to run it through the `testng.xml` file. This README provides instructions to help you get started.

## Prerequisites
Make sure you have the following tools installed:
- **Java Development Kit (JDK)** (Version 8 or higher)
- **Apache Maven** (Optional, for build management)
- **TestNG** (Latest version)

## File Structure
- `src/main/java/`: Contains the Java source code.
- `src/test/java/`: Contains the test scripts and related code.
- `testng.xml`: The TestNG configuration file for running the scripts.

## How to Execute the Script
Follow these steps to run the script using TestNG:
1. Open your terminal or command prompt.
2. Navigate to the project directory where the `testng.xml` file is located.
3. Run the following command:
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
