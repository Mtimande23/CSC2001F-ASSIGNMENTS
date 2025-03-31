JAVAC = javac
JFLAGS = -g -d .

SRC = src
BIN_DIR = bin

# Java source files
SOURCES = $(wildcard $(SRC)/*.java)
CLASS_FILES = $(patsubst $(SRC)/%.java,$(BIN_DIR)/%.class,$(SOURCES))

all: $(CLASS_FILES)
	@echo "Compilation complete"

$(BIN_DIR)/%.class: $(SRC)/%.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) $(JFLAGS) -cp $(SRC) -d $(BIN_DIR) $<

run-part1: all
	java -cp $(BIN_DIR) GenericsKbAVLAppPart1

run-part2: all
	java -cp $(BIN_DIR) GenericsKbAVLApp

run-all: run-part1 run-part2 

clean:
	rm -rf $(BIN_DIR)
	@echo "Cleaned up compiled classes"

.PHONY: all clean run-part1 run-part2 run-plotter run-all