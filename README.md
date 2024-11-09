# Hookah Plugin

___

Hookah is a plugin for the Minecraft server that adds hookahs to the game.
Add a twist to your server, allowing players to enjoy the wonderful taste of tobacco

![](https://raw.githubusercontent.com/d3nbr0/mc-hookah/main/media/banner.png "Banner")

## How to use

Put any glass, and add a potion maker on top, as in the picture above.
Right-click on the potion maker will allow you to smoke a hookah

## Configuration

This plugin can be configured using the `config.yml` file

Variables:

- **max-smoked** - The maximum number of hookah uses in a row before the player starts to get negative effects
- **smoke-duration** - The time it takes to reset all hookah smoking attempts
- **damage** - Damage inflicted on a player who smoked a hookah
- **positive-effect-duration** - The duration of the positive effect of restoring health in seconds
- **negative-effect-duration** - The duration of the negative deceleration effect in seconds
- **message-radius** - The radius in which other players will see effects, hear sounds, and see chat messages
- **limit-message** - A message to the user warning that he is getting negative effects
- **puff-messages** - List of hookah smoking chat messages
- **damage-messages** - List of chat messages when receiving hookah damage
- **die-messages** - List of chat messages at death from hookah

Also, users who are in creative mode or have `hookah.nonegative` permission are not subject
to the negative effects of hookah

### Enjoy your game!
