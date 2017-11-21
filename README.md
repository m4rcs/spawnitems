[![Travis-CI](https://travis-ci.org/m4rcs/spawnitems.svg?branch=master)](https://travis-ci.org/m4rcs/spawnitems/)

# Spawn Items
A minecraft forge mod that gives items to players on spawn _once_. Besides a list of predefined items, players can receive a random item.

This mod is _server-only_.

## Usage

This mod requires [Minecraft Forge](http://files.minecraftforge.net/). Be sure it is installed. Otherwise this mod won't work.

### Installation & Configuration

To install the mod just place the downloaded `.jar` file in the mods-folder like any other mod and create a `spawnitems.cfg` file in the `config/` folder of your server with the following contents:

```
# Configuration file

general {
    B:giveRandomItem=true
    S:items <
        minecraft:dirt
     >
}
```

#### Configurations

Name             | Type         | Description
---------------- | ------------ | -----------
*giveRandomItem* | boolean      | Give a random item to a spawning player.
*items*          | List[String] | The list of items that a player should receive on spawn. Not only vanilla minecraft blocks and items are supported. A list of valid vanilla name can be found [here](https://minecraft.gamepedia.com/Java_Edition_data_values#Block_IDs).
