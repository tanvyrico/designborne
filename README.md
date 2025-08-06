# Designborne
Console-based rogue-like game in Java, focusing on creating a scalable and maintanable codebase by adhering to good coding principle.
![Figure 1.1](preview.png)


Comprehensive documentation of the code base can be found in the __docs__ folder.

# Usage
### 1. Compile Code
Open a PowerShell terminal in the root of the project and run:
```
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out/ $files
```

### 2. Run the game
Once compiled, run the game with:
```
java -cp out game.Application
```
