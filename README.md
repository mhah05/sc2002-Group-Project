# sc2002-Group-Project

# SC2002 Turn-Based Combat Arena

This is a Java-based turn-based combat game developed as part of the SC2002 Object-Oriented Design & Programming project.

The game allows players to select a character class, choose a level, and engage in battle against enemies using actions, items, and special skills.

## Features

- Multiple player classes (Warrior, Wizard)
- Turn-based combat system with speed-based ordering
- Action system (Basic Attack, Defend, Special Skills, Item usage)
- Status effects (Stun, Defend, Smoke Bomb)
- Inventory system with consumable items
- Multiple levels with enemy waves and backup spawn logic
- Console-based user interface

## Project Structure

The project is organized into the following packages:

- `combatants` – Player and enemy classes
- `actions` – All combat actions and skills
- `items` – Consumable items
- `statuseffects` – Buffs and debuffs applied during battle
- `engine` – Core game logic (BattleEngine, Level, TurnOrderStrategy)
- `ui` – User interface (ConsoleUI)

Refer to the UML diagrams in `/diagrams` for detailed class relationships.

## How to Run

1. Open the project in an IDE (e.g., IntelliJ or VS Code)
2. Navigate to:
   ui.ConsoleUI
3. Run the `main` method

The game will start in the console.

## Gameplay Flow

1. Player selects a class (Warrior / Wizard)
2. Player selects a level
3. Player chooses starting items
4. Battle begins:
   - Turn order is determined by speed
   - Player selects actions (attack, defend, item, special skill)
   - Enemies act automatically
5. Game ends when:
   - Player defeats all enemies (Victory)
   - Player HP reaches 0 (Defeat)
