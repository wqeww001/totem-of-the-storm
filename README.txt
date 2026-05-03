# Totem of the Storm ⚡

*Summon Lightning. Gain Power. Control the Storm.*

<p align="center">
  <a href="https://modrinth.com/mod/totem-of-the-storm"><img src="https://img.shields.io/badge/Modrinth-Download-00AF5C?style=for-the-badge&logo=modrinth" /></a>
  <a href="https://github.com/wqeww001/totem-of-the-storm"><img src="https://img.shields.io/badge/GitHub-Source-181717?style=for-the-badge&logo=github" /></a>
  <a href="https://github.com/wqeww001/totem-of-the-storm/stargazers"><img src="https://img.shields.io/github/stars/wqeww001/totem-of-the-storm?style=for-the-badge&labelColor=0d1117&color=ffd700" /></a>
  <a href="https://github.com/wqeww001/totem-of-the-storm/issues"><img src="https://img.shields.io/github/issues/wqeww001/totem-of-the-storm?style=for-the-badge&labelColor=0d1117&color=ff6b6b" /></a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.20.1-62B47A?style=flat-square&logo=minecraft" />
  <img src="https://img.shields.io/badge/Loader-Forge-D4A444?style=flat-square" />
  <img src="https://img.shields.io/badge/Version-1.0.0-blue?style=flat-square" />
</p>

---

My first Minecraft mod for **Forge 1.20.1**. Adds a **Storm Totem** — a multi-block structure that harnesses the power of lightning.

> I'm still learning. Code may be messy, textures are bad. Be kind 🙏

---

## ⚡ Features

- Build a Storm Totem (3 blocks)
- Activate it with Storm Flint
- Lightning visual + thunder sound
- Player gets **Storm Rush** buff (Speed III + Jump Boost II)
- Crops in radius grow instantly
- Animals glow and drop experience
- Configurable cooldown

---

## 🛠️ Crafting

| Item | Recipe |
|------|--------|
| Charged Copper Ingot | Copper Ingot + Redstone + Glowstone Dust |
| Storm Infused Block | Charged Ingot + Smooth Stone |
| Storm Rod | Charged Ingot + Storm Block |
| Storm Dust | Glowstone Dust + Redstone + Gunpowder |
| Storm Flint | Storm Dust + Charged Ingot + Flint & Steel |

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
