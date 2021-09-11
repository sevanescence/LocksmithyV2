# LocksmithyV2

<p style="display: block;">
    <a href="https://www.spigotmc.org/">
        <img src="https://img.shields.io/badge/Spigot-1.16.5-orange.svg" />
    </a>
    <img src="https://img.shields.io/badge/Version-0.3--ALPHA-red.svg" />
    <br />
    <img src="https://tokei.rs/b1/github/MakotoMiyamoto/LocksmithyV2?category=code" />
    <a href="https://discord.gg/6TNYmfVKMD">
        <img src="https://img.shields.io/badge/Discord-MakotoMiyamoto%230215-blue.svg" />
    </a>
</p>

Second edition of Locksmithy, with performance fixes and a later game version.

# Changelog

## 0.4-ALPHA

### Added

- Proper block pairing
  - Double chests and doors form pair lockable containers,
    which contain a reference to one-another.
- Separated library, core, and implementations into separate packages.
  - Only lib and core are actually required for Locksmithy to work, so if you want
    to use the library yourself at some point you can omit impl and make your own
    implementation! :)
- Blocks are actually locked with keys now, however no implementation to evaluate said
keys while attempting to interact with lockable containers has been added yet.

### Upcoming
- Padlocks and lockable interaction evaluation

## 0.2-ALPHA

### Added

-   Implemented proper serialization and deserialization on lockable containers
-   Simple Key crafting recipe added
    -   Key functionality is not yet added, will be implemented in next patch.

#### Note: Secure lockable containers will be added in a future patch

## 0.1-ALPHA

### Added

-   Initial project structure
-   Locksmithy standard library
-   A couple debugging tools, will add more
    and some admin tools in future patches.

### Upcoming Features

-   More debug and admin tools
-   Custom key crafting
-   Accessible API
