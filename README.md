# spelling-bee

Dumb code I wrote to try to answer the question "How likely is it to be able to spell my dog's name, 'Clementine' in the NYT Spelling Bee puzzle?"

Based on this, there are 11,171 unique sets of letters where the following is true:

1. The set contains 7 unique letters
2. The set contains at least 1 vowel
3. The set does not contain the letter 's'
4. The set can create at least 1 pangram, using a list of all english words for reference.

However... this doesn't even account for the fact that the bee lords sometimes reuse letter sets but with a different letter in the center, so with that possibility I'm going to say there are around 78,197 possible bee puzzles.

I'm pretty sure the NYT's word list is much more restricted than this list of all english words I found, because there's some really obscure words in there. If I find a better word list I'll update it I guess.