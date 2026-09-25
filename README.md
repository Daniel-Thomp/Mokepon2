# Mokepon 2 | Java 2D RPG Engine

A tile-based 2D Role-Playing Game built entirely from scratch in core Java. Inspired by classic monster-catching franchises, this project demonstrates custom game engine architecture, object-oriented software design, file-driven asset management, and complex game-state logic without reliance on third-party game frameworks.

---

## 🛠️ Technical Architecture & Key Systems

### 1. Game State Management & Custom Rendering
* **Modular State Pipeline:** Implements a clean state machine architecture separating game phases—such as overworld navigation (`level.java`, `movement.java`), menu systems (`menu.java`), and turn-based combat (`battle.java`).
* **Tile-Based Overworld Engine:** Features a custom rendering pipeline (`screen.java`, `images.java`) that builds interactive 2D environments dynamically using modular tile sprites (terrain, structures, paths, and obstacles).
* **Sprite & UI Animation:** Custom animation handler supporting multi-directional player movement, running states, dynamic weather overlays (e.g., rain, sun effects), and custom text crawl rendering (`textAnimator.java`).

### 2. Data-Driven Level & Content Design
To promote modularity and decouple game logic from content, external assets and datasets drive core gameplay mechanics:
* **Map Parsing via CSV:** Overworld levels (`town1.csv`, `route1.csv`, `lab.csv`) are stored as matrix representations in CSV format, parsed at runtime to generate map geometry and collision bounds without hardcoding positions.
* **Combat & Stat Balancing:** Monster stats, elemental type effectiveness charts, and move properties are read directly from external files (`Stats.txt`, `typeChart.csv`), allowing rapid iteration and game balancing without recompiling the codebase.

### 3. Combat Logic & Systems Engineering
The core battle engine (`battle.java`) handles turn-based combat math and game state transitions:
* **Damage & Move Calculations:** Supports physical vs. special attack categories, elemental type multipliers, and status modifications.
* **Status Effect Machine:** Tracks and calculates multi-turn status conditions (Burn, Freeze, Paralysis, Poison, Bad Poison, Sleep) with integrated UI indicator updates.
* **Party & Inventory Management:** Features live combat party switching (`switchOut.java`), party stat tracking (`team.java`), and interactive inventory management (`bag.java`).

---

## 💻 Tech Stack & Concepts

* **Language:** Java (Core JDK)
* **Graphics & UI:** Java AWT/Swing (Custom drawing loops and asset pipeline)
* **Software Engineering Concepts:** Object-Oriented Design (OOD), Game State Machine, Data-Driven Architecture, Custom File I/O (CSV/TXT Parsing)
* **Assets:** Custom TTF Font loading, PNG/GIF sprite manipulation

---

## 🚀 Getting Started

### Prerequisites
* Java Development Kit (JDK 8 or higher)

### Build & Run
1. Clone this repository to your local machine.
2. Open a terminal in the root project directory.
3. Compile the Java source files:
   ```bash
   javac Main.java
   ```
4. Run the compiled application:
   ```bash
   java Main
   ```
