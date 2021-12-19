package utils.helpers.y2021

val matching = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>'
)
val scoresIllegal = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137,
)
val scoresCompletion = mapOf(
    ')' to 1,
    ']' to 2,
    '}' to 3,
    '>' to 4,
)