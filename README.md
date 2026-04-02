# sc2002-Group-Project

# SC2002 Turn-Based Combat Arena

## Naming Conventions

All code in this project follows standard Java naming conventions:

| Type | Convention | Example |
|---|---|---|
| Classes | PascalCase | `BattleEngine`, `Combatant`, `SmokeBomb` |
| Interfaces | PascalCase | `Action`, `StatusEffect`, `GameUI` |
| Abstract Classes | PascalCase | `AbstractItem`, `AbstractAction` |
| Methods | camelCase | `takeDamage()`, `getAvailableActions()` |
| Variables | camelCase | `currentHp`, `specialSkillCooldown` |
| Constants | UPPER_CASE | `MAX_HP`, `BASE_ATTACK` |
| Packages | lowercase | `engine`, `domain`, `ui` ||

## Responsibility Map

| Class/Interface | Type | Responsibility |
|---|---|---|
| `BattleEngine` | Class | Runs the battle: handles rounds, turns, and checks if the game is over |
| `GameUI` | Interface | Defines what the UI must be able to show and ask |
| `ConsoleUI` | Class | The actual CLI that prints to screen and reads player input |
| `TurnOrderStrategy` | Interface | Defines how turn order is decided each round |
| `SpeedBasedOrder` | Class | Orders combatants by speed, fastest goes first |
| `Combatant` | Abstract Class | Base for every fighter in the game — holds HP, stats, and status effects |
| `Player` | Abstract Class | Adds player stuff like items and special skill cooldown |
| `Enemy` | Abstract Class | Base for all enemies, currently always does BasicAttack |
| `Warrior` | Class | Tank-type player, uses Shield Bash to stun enemies |
| `Wizard` | Class | Glass cannon player, uses Arcane Blast to hit all enemies and gain attack |
| `Goblin` | Class | Weak enemy, slow but balanced stats |
| `Wolf` | Class | Fast enemy, high attack but low defense |
| `Action` | Interface | Defines what an action needs to do |
| `PlayerAction` | Interface | Same as Action but specifically for player-only actions |
| `AbstractAction` | Abstract Class | Holds the name shared by all actions |
| `BasicAttack` | Class | Hits one target, damage is ATK minus DEF |
| `Defend` | Class | Boosts defense for 2 turns |
| `ItemAction` | Class | Uses one of the player's items |
| `SpecialSkill` | Class | Triggers the player's special ability |
| `StatusEffect` | Interface | Defines what a status effect needs to do |
| `AbstractStatusEffect` | Abstract Class | Holds name and duration shared by all effects |
| `StunEffect` | Class | Stops the target from acting for 2 turns |
| `DefendEffect` | Class | Adds 10 defense for 2 turns |
| `SmokeBombEffect` | Class | Blocks all enemy damage for 2 turns |
| `ArcaneBlastEffect` | Class | Gives Wizard +10 ATK for each enemy killed by Arcane Blast |
| `Item` | Interface | Defines what every item needs to do |
| `AbstractItem` | Abstract Class | Tracks name and whether the item has been used |
| `Potion` | Class | Heals 100 HP |
| `PowerStone` | Class | Uses special skill for free without touching the cooldown |
| `SmokeBomb` | Class | Blocks enemy attacks for 2 turns |
| `Level` | Class | Stores which enemies spawn and whether there's a backup wave |
| `GameConfig` | Class | Stores what the player picked at the start — character, level, items |

## Key Attributes and Methods

| Class | Key Attributes | Key Methods |
|---|---|---|
| `Combatant` | `name`, `maxHp`, `currentHp`, `attack`, `defense`, `speed`, `statusEffects` | `takeDamage()`, `heal()`, `isAlive()`, `addStatusEffect()`, `removeExpiredEffects()` |
| `Player` | `items`, `specialSkillCooldown` | `canUseSpecialSkill()`, `resetCooldown()`, `tickCooldown()`, `useSpecialSkill()` |
| `Enemy` | — | `getAvailableActions()` |
| `Warrior` | — | `getAvailableActions()`, `useSpecialSkill()` |
| `Wizard` | — | `getAvailableActions()`, `useSpecialSkill()` |
| `BattleEngine` | `player`, `enemies`, `turnOrderStrategy`, `ui` | `startBattle()`, `processTurn()`, `isGameOver()`, `getWinner()` |
| `AbstractItem` | `name`, `consumed` | `getName()`, `isConsumed()`, `markConsumed()` |
| `AbstractStatusEffect` | `name`, `duration` | `getName()`, `isExpired()`, `decrementDuration()` |
| `AbstractAction` | `name` | `getName()` |
| `Level` | `levelNumber`, `difficulty`, `initialEnemies`, `backupEnemies` | `getInitialEnemies()`, `getBackupEnemies()`, `hasBackupSpawn()` |
| `GameConfig` | `selectedPlayer`, `selectedLevel`, `selectedItems` | `getPlayer()`, `getLevel()`, `getItems()` |
