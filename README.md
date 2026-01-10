# Java Programming Labs – ID1018

This repository contains completed programming labs from the KTH course **Programming 1 (ID1018)**.  
The labs focus on Java programming, problem-solving, and working with arrays, sequences, and data processing.

## Labs Overview

### Lab A1 – Temperatures
- **Task:** Complete the `Temperatures1` and `Temperatures2` classes to process weekly temperature data.
- **Description:** For each week, determine the minimum, maximum, and average temperature, as well as for the entire measurement period. Implement your own methods instead of using standard library methods.
- **Files:** `Temperatures1.java`, `Temperatures2.java`, `TemperaturesData.txt`

### Lab A2 – Synonyms
- **Task:** Complete the `SynonymHandler` class so the `SynonymUser` program can manage synonym data correctly.
- **Description:** Read synonym lines, manipulate data without using `java.util` or `String.contains()`. Implement add, remove, and sort operations on words and synonyms.
- **Files:** `SynonymHandler.java`, `SynonymUser.java`, `SynonymData1.txt`

### Lab A3 – Number Sequences
- **Task:** Complete `ArrayNumberSequence`, `LinkedNumberSequence`, and `NumberSequenceTest`.
- **Description:** Implement number sequences with arrays and linked nodes, respecting upper/lower bounds and order (increasing/decreasing). Focus on memory and time efficiency.
- **Files:** `ArrayNumberSequence.java`, `LinkedNumberSequence.java`, `NumberSequenceTest.java`

---

## Folder Structure

Each lab is in its own folder:

java-labs-id1018/
```plaintext
java-labs-id1018/
├── LabA1_Temperatures/
│   ├── Temperatures1.java
│   ├── Temperatures2.java
│   └── TemperaturesData.txt
├── LabA2_Synonyms/
│   ├── SynonymHandler.java
│   ├── SynonymUser.java
│   └── SynonymData1.txt
└── LabA3_NumberSequences/
    ├── ArrayNumberSequence.java
    ├── LinkedNumberSequence.java
    └── NumberSequenceTest.java
```
---

## How to Run

Compile and run the Java files for each lab using the command line:

```bash
# Example for Lab A1
javac Temperatures1.java
java Temperatures1

javac Temperatures2.java
java Temperatures2
```
Repeat for each lab in its respective folder.

## Notes
- The given programs are not changed, only completed at designated add code here locations.
- All code was written to follow KTH course requirements and demonstrates basic Java programming, arrays, and data processing skills.
## License

MIT License
