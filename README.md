# LL-1-Parsing
For this task you will implement an LL(1) parser using pushdown automata (PDA) and predictive parsing tables. Given an input context-free grammar G = (V, Σ, R, S), along with the First and Follow sets for all rules, you need to (i) construct the predictive parsing table for G, (ii) construct the PDA equivalent to G, and (iii) implement an LL(1) parser for G which makes use of the table and the PDA to direct its decisions. Given an input string w, the parser should signal an error if w ∈/ L(G) and produce a derivation of w from S if w ∈ L(G).

Requirements:
I should implement a class constructor CfgLl1Parser and a method parse.

1. CfgLl1Parser, a class constructor, takes one parameter which is a string description of a CFG, together with First and Follow sets for its rules, and constructs a CFG instance. A string encoding a CFG is of the form V #T #R#I #O.

2. parse takes an input string w and returns a string encoding a left-most derivation of w in G; in case w ∈/ L(G), this derivation ends with “ERROR.” The parse method should construct a PDA equivalent to G and use the PDA together with the LL(1) parsing table to reach its decision. Note that we will be testing parse using only LL(1) grammars. Hence, you do not need to include a search algorithm in your implementation; w either has no derivation in G or has exactly one.
    1. A string encoding a derivation is a semicolon-separated sequence of items. Each item is a sentential form representing a step in the derivation. The first item is S. If w ∈ L(G) the last item is w; otherwise, it is ERROR. For example, given G1, on input string iiac, parse should return the following string.
