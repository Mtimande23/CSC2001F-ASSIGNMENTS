# Makefile for compiling Java project

# Compiler
JAVAC = javac

# Directories
srcDir= src
binDir= bin

# Java source files
SOURCES = $(wildcard $(srcDir)/*.java)

# Default target: Compile all Java files at once
all:
	@mkdir -p $(binDir)
	$(JAVAC) -d $(binDir) $(SOURCES)

# Run the main program
run: all
	java -cp $(binDir) mainInterface

# Clean target to remove compiled files
clean:
	rm -rf $(binDir)/*.class
