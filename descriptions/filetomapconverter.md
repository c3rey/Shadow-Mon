## FileToMapConverter

Converts .txt files to [Maps](map.md).

| Variable |  Type   | Description                          |
|:--------:|:-------:|--------------------------------------|
| scanner  | Scanner | The scanner used to read file input. |
\
\
\
\
__convertToMap()__

Converts a .txt file that consists of a 16x12 table of numbers that represent tiles
on the map. Loops through the numbers and sets them to their respective positions in a
2d array, and returns that array.