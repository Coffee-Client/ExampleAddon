# Example addon for coffee
An example addon for coffee
## Setup
It is very important to download the latest SDK and keep it up to date, otherwise the game will crash if a breaking change was made since the last update
1. Clone this repository
2. Download the latest SDK and SDK sources
   ![](https://media.discordapp.net/attachments/999666284499308636/1000741318898757632/rea.png)
3. Put them into the `libs/` folder and replace the ones currently there
4. Reload gradle
5. Modify the addon to your hearts content

The SDK sources are technically not needed, but they are useful to have while developing.
## Building
To build the finished addon, just run the gradle build task. The jar will be in `build/libs`, the one without the `sources` suffix
## Installing
1. Open the pause menu
2. Click the "Addons" button
3. Click the "Open folder" button
4. Drag the addon jar into the folder
5. Wait up to 10 seconds for coffee to reload the addon list
6. Click "enable" on the addon entry
## Some notes
- The addon is technically not a fabric mod, but it's useful to have it set up to be one, since you have the minecraft game decompiled. You can modify fabric.mod.json entirely, it's not going to be used anywhere.
- Mixins are not available for addons, because they aren't seen as mods, and can be reloaded while in-game. Mixin's dont work like that and won't ever either, so mixin's cant be used with addons. Similarly, the fabric ecosystem, too, is entirely capped off from the addons system.