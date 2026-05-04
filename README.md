# Totem of the Storm ⚡

*Summon Lightning. Gain Power. Control the Storm.*

[![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C?style=for-the-badge&logo=modrinth)](https://modrinth.com/mod/totem-of-the-storm)
[![CurseForge](https://img.shields.io/badge/CurseForge-Download-F16436?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/totem-of-the-storm)
[![GitHub](https://img.shields.io/badge/GitHub-Source-181717?style=for-the-badge&logo=github)](https://github.com/wqeww001/totem-of-the-storm)
[![Stars](https://img.shields.io/github/stars/wqeww001/totem-of-the-storm?style=for-the-badge&labelColor=0d1117&color=ffd700)](https://github.com/wqeww001/totem-of-the-storm/stargazers)

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62B47A?style=flat-square&logo=minecraft)
![Forge](https://img.shields.io/badge/Loader-Forge-D4A444?style=flat-square)
![Version](https://img.shields.io/badge/Version-1.2.0-blue?style=flat-square)

---

My first Minecraft mod for **Forge 1.20.1**. Adds a **Storm Totem**, **Storm Wand**, and **Storm Ore**!

> I'm still learning. Code may be messy, textures are not perfect. Be kind 🙏

---

## ⚡ Features

- 🏗️ Build a **Storm Totem** (2 Storm Blocks + 1 Storm Rod)
- 🪄 **Storm Wand** — ranged lightning weapon (5 charges, 15 blocks)
- ⛏️ **Storm Ore** — generates underground, drops Charged Copper Nuggets
- 🔥 Smelt Nuggets → Charged Copper Ingots
- ⚡ Activate totem with Storm Flint
- 🏃 **Storm Rush** buff (Speed III + Jump Boost II)
- 🌾 Crops grow instantly in radius
- 🐄 Animals glow and drop experience
- 📶 Redstone signal from Storm Rod
- ⏳ Configurable cooldown
- 🔧 Enhanced structure (add copper blocks for +50% radius)

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

---

## 🔄 Gameplay Loop
Mine Storm Ore → Nuggets → Furnace → Ingots → Craft Totem → Activate → Profit!

---

## ⚙️ Config

Located in `config/stormtotemmod-common.toml`:
- `totemCooldownSeconds` (default: 300)
- `totemEffectRadius` (default: 20)
- `stormRushDuration` (default: 12)
- `chargedAnimalsDropDust` (default: true)

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