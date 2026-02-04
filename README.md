<p align="center">
  <img src="./docs/icon.png" alt="HoYoDaily Logo" width="128"/>
</p>

## 📋 Overview
LocatorToggle is a lightweight Spigot plugin that allows players to locally toggle the newly introduced locator bar. 
When toggled off, the player is also hidden from other players’ locator bars, providing a more private gameplay experience.

❗ **Gamerule 'locatorBar' must be set to 'true'**

## 🎥 Demonstration
<img src="./docs/demo.gif" width="650"/>

## 🛠️ Commands
- **/locator** - Toggles the locator bar functionality on/off for the player.
- **/locator radius \<value>** - Sets the locator bar radius (1-60,000,000 blocks).
- **/locator reload** - Reloads the plugin configuration and language files.

## 🔐 Permissions
| Permission node | Description                                      | Default setting |
|-----------------|--------------------------------------------------|-----------------|
| locator.toggle  | Controls usage of the /locator command           | Everyone        |
| locator.radius  | Controls usage of the /locator radius subcommand | Everyone        |
| locator.reload  | Controls usage of the /locator reload subcommand | Operators       |

## ⚙️ Configuration
<details>
<summary>Click to view config.yml</summary>

```yaml
# Whether new users will have their locator disabled by default.
locator-disabled-by-default: false

# Maximum radius that players can set using /locator radius.
locator-max-radius: 60000000

# Default radius when players enable the locator.
# This only applies when the player hasn't set a custom radius yet.
default-locator-radius: 60000000
```
</details>

## 🔌 PlaceholderAPI Support
This plugin provides the following placeholders:
- `%locatortoggle_status%` - Returns "enabled" or "disabled" based on the player's preference
- `%locatortoggle_radius%` - Returns the player's custom locator bar radius (if set)

## 📄 License
This project is licensed under the [MIT license](LICENSE).
