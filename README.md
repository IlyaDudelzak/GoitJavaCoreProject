# Cities Game

A simple desktop "Cities" word-chain game built in Java using Swing. Play against the computer by naming world cities!

---

## How to Play

1. **Start the game**: Enter any valid world city name (in English).
2. **Take turns**: The computer responds with a city starting with the last letter of your city.
3. **Follow the chain**: Your next city must start with the last letter of the computer's city.
4. **No repeats**: Each city can only be used once per game.
5. **Winning**: You win if the computer runs out of cities for that letter!

### In-Game Buttons
- **Submit** (or press <kbd>Enter</kbd>): Confirm your input.
- **Auto-move (Test)**: Automatically picks a valid city for you (useful for testing).
- **Surrender**: Forfeit the current match.

### Scoring
- Each move gives you points based on word length: `length * length`.
- The winner gets a `+100` bonus.

---

## Requirements

- **Java (JDK) 17** or higher.

---

## How to Run

### Option 1: Terminal (Gradle)

1. **Build the executable JAR**:
   ```bash
   ./gradlew jar
   ```
   *(On Windows use `gradlew.bat jar`)*

2. **Launch the game**:
   ```bash
   java -jar build/libs/GoitJavaCoreProject-1.0-SNAPSHOT.jar
   ```

### Option 2: From IDE (IntelliJ IDEA, Eclipse, etc.)

1. Open the project folder in your IDE as a Gradle project.
2. Locate and run:
   ```
   src/main/java/com/dudelzak/goit/java/coreproject/AppLauncher.java
   ```

---

## Project Files

- **`AppLauncher.java`** — Starts the application.
- **`StartingWindow.java`** & **`GameWindow.java`** — Swing GUI screens.
- **`UITheme.java`** — Custom color palette, typography, and component styling.
- **`GameManager.java`** — Game rules, turn processing, and score calculation.
- **`CityNameStorage.java`** & **`CityReader.java`** — City pool management and JSON reading.
- **`Utils.java`** — Helper methods for valid letter extraction and icon loading.
- **`src/main/resources/cities.json`** — List of recognized cities.
