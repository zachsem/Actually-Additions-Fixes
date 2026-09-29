# Actually Additions Fixes

Unofficial fixes for **Actually Additions r152** on **Minecraft 1.12.2**.

## Fixes in 1.0.0

### BioMash stack limit

Fixes `RecipeBioMash` using a hard-coded maximum output size of 64 instead of the actual maximum stack size of Mashed Food.

Upstream issue: https://github.com/Ellpeck/ActuallyAdditions/issues/1336

### Phantom Breaker Forge events

Fixes the Phantom Breaker bypassing the Forge block break and harvest-drop event path used by the regular Actually Additions Auto-Breaker.

Upstream issue: https://github.com/Ellpeck/ActuallyAdditions/issues/1322

## Requirements

- Minecraft 1.12.2
- Forge
- Actually Additions 1.12.2-r152
- MixinBooter 11.17 or newer

Both the client and dedicated server need the mod and its required dependencies.

## Installation

1. Install Forge for Minecraft 1.12.2.
2. Install Actually Additions r152 and MixinBooter 11.17 or newer.
3. Place `ActuallyAdditionsFixes-1.0.0.jar` in the `mods` folder on the client and server.

## Validation

The included fixes were reproduced and tested in single-player and on a dedicated server. Testing included Forge 14.23.5.2859 and 14.23.5.2864 and compatibility with Universal Tweaks 1.21.0.

See [`docs/VALIDATION.md`](docs/VALIDATION.md) for the detailed test record.

## Building from source

The project uses RetroFuturaGradle and targets Java 8 bytecode. Gradle 8.8 is a suitable build version. The configured toolchain resolver can obtain a Java 8 toolchain when one is not already installed.

```text
gradle build
```

The normal release artifact is the JAR in `build/libs`.

## Reporting issues

Please use GitHub Issues and include the Minecraft version, Forge version, Actually Additions version, this mod's version, relevant optional mods, reproduction steps, and a log or crash report when applicable.

## License

This project is licensed under the MIT License. See [`LICENSE`](LICENSE).

## Disclaimer

This is an unofficial fix mod and is not maintained or endorsed by the Actually Additions developers.
