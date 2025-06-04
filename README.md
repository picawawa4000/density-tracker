This project does not deserve a README.

Basically, just create a JSON file in a datapack overriding your target density function. Then, copy-and-paste the old density function, but put a `minecraft:log` density function somewhere in there.

Example using `sloped_cheese.json`:

```JSON
{
"type": "minecraft:log",
"argument": {
  "type": "minecraft:add",
  "argument1": {
    // rest of the logic (ignore the JSON comment)...
  },
  "argument2": "minecraft:overworld/base_3d_noise"
}}
```

Outputs are logged to `$GAME_DIRECTORY/density_tracker_log.log`. They are all in the form `{LOGGER_NAME} [X, Y, Z] OUTPUT`, where `{LOGGER_NAME}` should be the name of the logger but is really always `???` because I don't know how to do serialization properly, and you can guess what the rest means.
