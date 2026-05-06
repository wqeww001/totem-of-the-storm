# Totem of the Storm ⚡

*Summon Lightning. Gain Power. Control the Storm.*

[![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C?style=for-the-badge&logo=modrinth)](https://modrinth.com/mod/totem-of-the-storm)
[![CurseForge](https://img.shields.io/badge/CurseForge-Download-F16436?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/totem-of-the-storm)
[![GitHub](https://img.shields.io/badge/GitHub-Source-181717?style=for-the-badge&logo=github)](https://github.com/wqeww001/totem-of-the-storm)
[![Stars](https://img.shields.io/github/stars/wqeww001/totem-of-the-storm?style=for-the-badge&labelColor=0d1117&color=ffd700)](https://github.com/wqeww001/totem-of-the-storm/stargazers)

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62B47A?style=flat-square&logo=minecraft)
![Forge](https://img.shields.io/badge/Loader-Forge-D4A444?style=flat-square)
![Version](https://img.shields.io/badge/Version-2.0.0-blue?style=flat-square)

---

My first Minecraft mod for **Forge 1.20.1**. Adds a **Storm Totem**, **Storm Dimension**, and **Storm Crystal**!

> I'm still learning. Code may be messy, textures are not perfect. Be kind 🙏

---

## ⚡ Features

- 🏗️ Build a **Storm Totem** (2 Storm Blocks + 1 Storm Rod)
- 🚪 Build a **Storm Portal** and enter the **Storm Dimension**
- 🌍 Storm Dimension — floating islands, purple sky, eternal night
- 💎 **Storm Crystal Ore** — found only in Storm Dimension
- 💎 **Storm Crystal** — new resource (1-3 from ore, Fortune works)
- 🪄 **Storm Wand** — ranged lightning weapon (5 charges, 15 blocks)
- ⛏️ **Storm Ore** — generates in Overworld, drops Charged Copper Nuggets
- 🔥 Smelt Nuggets → Charged Copper Ingots
- ⚡ Activate totem with Storm Flint
- 🏃 **Storm Rush** buff (Speed III + Jump Boost II) with particle trail
- 🛡️ **Storm Shield** — 5s invincibility on totem activation
- 🌾 Crops grow instantly in radius
- 🐄 Animals glow and drop experience
- 📶 Redstone signal from Storm Rod
- ⏳ Configurable cooldown with time remaining message
- 🔧 Enhanced structure (add copper blocks for +50% radius)
- 📖 **Storm Guide Book** — in-game guide with GUI
- 🧭 **Storm Compass** — check weather
- 💬 **Tooltips** on all items
- 🎁 **Starter Kit** — book + compass on first login

---

## 🛠️ Crafting

| Item | Recipe |
|------|--------|
| Charged Copper Ingot | Copper Ingot + Redstone + Glowstone Dust |
| Charged Copper Nugget | Smelt in furnace → Charged Copper Ingot |
| Storm Infused Block | Charged Ingot + Smooth Stone |
| Storm Rod | Charged Ingot + Storm Block |
| Storm Dust | Glowstone Dust + Redstone + Gunpowder |
| Storm Flint | Storm Dust + Charged Ingot + Flint & Steel |
| Storm Wand | 2 Charged Ingots + Storm Dust |
| Storm Compass | 4 Charged Ingots + Redstone |
| Storm Guide Book | Book + Storm Dust |
| Storm Portal | 4x5 frame of Storm Blocks, light with Storm Flint |

---

## 🌍 Storm Dimension

- Build a Nether-like portal (4x5 Storm Blocks)
- Light it with Storm Flint
- Enter to discover floating islands with Storm Crystal Ore
- Return portal spawns nearby automatically

---

## 🔄 Gameplay Loop
Overworld: Mine Storm Ore → Nuggets → Furnace → Ingots → Craft Totem
↓
Activate Totem → Buffs + Crop Growth + Animal XP
↓
Craft Portal → Enter Storm Dimension → Mine Storm Crystal

text

---

## ⚙️ Config

Located in `config/stormtotemmod-common.toml`:
- `totemCooldownSeconds` (default: 300)
- `totemEffectRadius` (default: 20)
- `stormRushDuration` (default: 12)
- `giveStarterKit` (default: true)

---

## 📦 Requirements

- Minecraft **1.20.1**
- Forge **47.x+**

---

## 🔨 Building

```bash
git clone https://github.com/wqeww001/totem-of-the-storm.git
cd totem-of-the-storm
./gradlew build