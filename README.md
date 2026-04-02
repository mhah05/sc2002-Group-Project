# sc2002-Group-Project

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

## Responsibility Map

| Class/Interface | Type | Responsibility |
|---|---|---|
| `BattleEngine` | Class | Manages round flow, turn order, action execution, win/loss checks |
| `GameUI` | Interface | Contract for all UI input/output operations |
| `ConsoleUI` | Class | CLI implementation of GameUI, handles all user input/output |
| `TurnOrderStrategy` | Interface | Contract for determining turn order each round |
| `SpeedBasedOrder` | Class | Implements turn order by descending speed stat |
| `Combatant` | Abstract Class | Base class for all battle participants, holds shared stats and status effects |
| `Player` | Abstract Class | Player-specific logic, inventory, and special skill cooldown |
| `Enemy` | Abstract Class | Base for all enemy types, extensible for future AI behaviour |
| `Warrior` | Class | Concrete player with Shield Bash special skill |
| `Wizard` | Class | Concrete player with Arcane Blast special skill |
| `Goblin` | Class | Concrete enemy with low HP, medium stats |
| `Wolf` | Class | Concrete enemy with high speed and attack |
| `Action` | Interface | Contract for all executable combat actions |
| `PlayerAction` | Interface | Contract for player-only actions |
| `AbstractAction` | Abstract Class | Shared base for all actions, holds name |
| `BasicAttack` | Class | Deals damage based on attacker ATK minus target DEF |
| `Defend` | Class | Applies DefendEffect to increase defense for 2 turns |
| `ItemAction` | Class | Executes a selected item's use() method |
| `SpecialSkill` | Class | Triggers the player's class-specific special ability |
| `StatusEffect` | Interface | Contract for all persistent turn-based effects |
| `AbstractStatusEffect` | Abstract Class | Shared base for all effects, holds name and duration |
| `StunEffect` | Class | Prevents target from acting for 2 turns |
| `DefendEffect` | Class | Increases target defense by 10 for 2 turns |
| `SmokeBombEffect` | Class | Makes all incoming enemy attacks deal 0 damage for 2 turns |
| `ArcaneBlastEffect` | Class | Increases Wizard ATK by 10 per enemy killed by Arcane Blast |
| `Item` | Interface | Contract for all usable items |
| `AbstractItem` | Abstract Class | Shared base for all items, holds name and consumed state |
| `Potion` | Class | Heals player by 100 HP capped at maxHp |
| `PowerStone` | Class | Triggers special skill once without affecting cooldown |
| `SmokeBomb` | Class | Applies SmokeBombEffect on use |
| `Level` | Class | Holds difficulty, initial enemies and backup enemy data |
| `GameConfig` | Class | Holds player selection, level selection and item selection |

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
