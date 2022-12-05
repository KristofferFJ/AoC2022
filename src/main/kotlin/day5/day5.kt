package day5

import utils.ListUtils.Companion.removeSlice
import utils.ListUtils.Companion.transpose

val instructions = "" +
        "move 3 from 9 to 4\n" +
        "move 2 from 5 to 2\n" +
        "move 8 from 1 to 9\n" +
        "move 4 from 7 to 1\n" +
        "move 5 from 3 to 8\n" +
        "move 3 from 3 to 7\n" +
        "move 11 from 8 to 3\n" +
        "move 7 from 3 to 6\n" +
        "move 2 from 5 to 9\n" +
        "move 3 from 1 to 6\n" +
        "move 6 from 2 to 4\n" +
        "move 6 from 7 to 5\n" +
        "move 1 from 6 to 1\n" +
        "move 1 from 9 to 4\n" +
        "move 16 from 4 to 9\n" +
        "move 2 from 1 to 2\n" +
        "move 1 from 4 to 6\n" +
        "move 1 from 3 to 7\n" +
        "move 2 from 2 to 4\n" +
        "move 1 from 7 to 9\n" +
        "move 22 from 9 to 8\n" +
        "move 1 from 6 to 3\n" +
        "move 18 from 8 to 5\n" +
        "move 3 from 8 to 2\n" +
        "move 3 from 2 to 9\n" +
        "move 13 from 6 to 7\n" +
        "move 1 from 6 to 7\n" +
        "move 4 from 3 to 6\n" +
        "move 2 from 6 to 3\n" +
        "move 2 from 3 to 8\n" +
        "move 3 from 7 to 8\n" +
        "move 14 from 5 to 2\n" +
        "move 3 from 2 to 5\n" +
        "move 2 from 8 to 4\n" +
        "move 4 from 8 to 6\n" +
        "move 4 from 6 to 4\n" +
        "move 11 from 5 to 2\n" +
        "move 3 from 9 to 2\n" +
        "move 7 from 2 to 3\n" +
        "move 11 from 7 to 2\n" +
        "move 1 from 5 to 7\n" +
        "move 5 from 6 to 8\n" +
        "move 30 from 2 to 7\n" +
        "move 23 from 7 to 2\n" +
        "move 4 from 3 to 4\n" +
        "move 3 from 9 to 6\n" +
        "move 4 from 8 to 2\n" +
        "move 1 from 8 to 2\n" +
        "move 2 from 7 to 9\n" +
        "move 4 from 2 to 3\n" +
        "move 1 from 5 to 9\n" +
        "move 6 from 4 to 7\n" +
        "move 5 from 3 to 6\n" +
        "move 1 from 3 to 6\n" +
        "move 1 from 9 to 2\n" +
        "move 16 from 2 to 5\n" +
        "move 7 from 7 to 6\n" +
        "move 9 from 2 to 1\n" +
        "move 2 from 1 to 4\n" +
        "move 8 from 5 to 3\n" +
        "move 5 from 7 to 4\n" +
        "move 1 from 9 to 8\n" +
        "move 9 from 3 to 6\n" +
        "move 25 from 6 to 8\n" +
        "move 2 from 9 to 5\n" +
        "move 3 from 4 to 2\n" +
        "move 7 from 4 to 1\n" +
        "move 1 from 8 to 7\n" +
        "move 6 from 5 to 2\n" +
        "move 11 from 8 to 5\n" +
        "move 1 from 7 to 9\n" +
        "move 10 from 1 to 2\n" +
        "move 6 from 5 to 1\n" +
        "move 1 from 4 to 2\n" +
        "move 13 from 8 to 1\n" +
        "move 17 from 1 to 2\n" +
        "move 5 from 1 to 9\n" +
        "move 1 from 8 to 4\n" +
        "move 1 from 1 to 3\n" +
        "move 1 from 3 to 6\n" +
        "move 1 from 9 to 3\n" +
        "move 2 from 4 to 5\n" +
        "move 1 from 4 to 8\n" +
        "move 1 from 9 to 1\n" +
        "move 8 from 5 to 7\n" +
        "move 1 from 8 to 1\n" +
        "move 7 from 7 to 6\n" +
        "move 2 from 1 to 2\n" +
        "move 1 from 3 to 6\n" +
        "move 2 from 5 to 4\n" +
        "move 8 from 2 to 1\n" +
        "move 1 from 9 to 7\n" +
        "move 1 from 5 to 1\n" +
        "move 2 from 7 to 3\n" +
        "move 2 from 3 to 7\n" +
        "move 2 from 7 to 8\n" +
        "move 2 from 1 to 5\n" +
        "move 3 from 9 to 2\n" +
        "move 2 from 8 to 9\n" +
        "move 1 from 9 to 2\n" +
        "move 1 from 9 to 8\n" +
        "move 1 from 8 to 7\n" +
        "move 6 from 6 to 5\n" +
        "move 1 from 6 to 2\n" +
        "move 2 from 4 to 5\n" +
        "move 2 from 6 to 8\n" +
        "move 1 from 7 to 1\n" +
        "move 2 from 8 to 4\n" +
        "move 11 from 2 to 5\n" +
        "move 18 from 5 to 6\n" +
        "move 6 from 2 to 6\n" +
        "move 10 from 2 to 7\n" +
        "move 1 from 4 to 3\n" +
        "move 7 from 2 to 8\n" +
        "move 7 from 1 to 4\n" +
        "move 6 from 7 to 8\n" +
        "move 2 from 7 to 3\n" +
        "move 8 from 4 to 7\n" +
        "move 1 from 1 to 3\n" +
        "move 1 from 2 to 1\n" +
        "move 4 from 7 to 1\n" +
        "move 4 from 1 to 3\n" +
        "move 2 from 3 to 9\n" +
        "move 2 from 5 to 4\n" +
        "move 1 from 2 to 1\n" +
        "move 2 from 1 to 5\n" +
        "move 1 from 3 to 1\n" +
        "move 2 from 5 to 2\n" +
        "move 1 from 2 to 6\n" +
        "move 5 from 7 to 4\n" +
        "move 1 from 1 to 2\n" +
        "move 10 from 8 to 1\n" +
        "move 2 from 2 to 7\n" +
        "move 2 from 7 to 1\n" +
        "move 1 from 7 to 9\n" +
        "move 1 from 5 to 7\n" +
        "move 3 from 8 to 7\n" +
        "move 3 from 3 to 6\n" +
        "move 3 from 7 to 1\n" +
        "move 5 from 1 to 4\n" +
        "move 1 from 7 to 6\n" +
        "move 22 from 6 to 3\n" +
        "move 2 from 6 to 2\n" +
        "move 19 from 3 to 4\n" +
        "move 15 from 4 to 8\n" +
        "move 9 from 8 to 4\n" +
        "move 5 from 6 to 8\n" +
        "move 2 from 2 to 8\n" +
        "move 2 from 9 to 4\n" +
        "move 7 from 1 to 5\n" +
        "move 1 from 1 to 3\n" +
        "move 1 from 9 to 7\n" +
        "move 5 from 8 to 3\n" +
        "move 4 from 8 to 1\n" +
        "move 5 from 1 to 5\n" +
        "move 10 from 4 to 3\n" +
        "move 3 from 4 to 2\n" +
        "move 2 from 8 to 3\n" +
        "move 12 from 4 to 8\n" +
        "move 1 from 7 to 6\n" +
        "move 3 from 2 to 9\n" +
        "move 2 from 4 to 5\n" +
        "move 5 from 3 to 7\n" +
        "move 1 from 7 to 2\n" +
        "move 1 from 1 to 6\n" +
        "move 1 from 7 to 2\n" +
        "move 15 from 3 to 8\n" +
        "move 10 from 5 to 6\n" +
        "move 3 from 7 to 8\n" +
        "move 1 from 5 to 8\n" +
        "move 1 from 2 to 3\n" +
        "move 7 from 6 to 1\n" +
        "move 3 from 5 to 3\n" +
        "move 5 from 3 to 5\n" +
        "move 3 from 5 to 4\n" +
        "move 2 from 4 to 9\n" +
        "move 2 from 3 to 5\n" +
        "move 14 from 8 to 5\n" +
        "move 1 from 9 to 1\n" +
        "move 16 from 5 to 3\n" +
        "move 16 from 3 to 6\n" +
        "move 2 from 9 to 8\n" +
        "move 21 from 6 to 7\n" +
        "move 2 from 1 to 7\n" +
        "move 1 from 2 to 7\n" +
        "move 4 from 1 to 7\n" +
        "move 1 from 4 to 7\n" +
        "move 16 from 8 to 5\n" +
        "move 20 from 7 to 4\n" +
        "move 1 from 9 to 8\n" +
        "move 1 from 7 to 4\n" +
        "move 3 from 8 to 6\n" +
        "move 1 from 9 to 1\n" +
        "move 2 from 1 to 4\n" +
        "move 2 from 5 to 2\n" +
        "move 5 from 4 to 7\n" +
        "move 1 from 6 to 9\n" +
        "move 11 from 7 to 6\n" +
        "move 2 from 7 to 5\n" +
        "move 12 from 6 to 2\n" +
        "move 13 from 2 to 1\n" +
        "move 1 from 2 to 3\n" +
        "move 1 from 8 to 4\n" +
        "move 6 from 4 to 1\n" +
        "move 1 from 6 to 7\n" +
        "move 7 from 4 to 9\n" +
        "move 8 from 9 to 3\n" +
        "move 2 from 8 to 3\n" +
        "move 10 from 5 to 4\n" +
        "move 11 from 1 to 8\n" +
        "move 1 from 1 to 3\n" +
        "move 5 from 1 to 8\n" +
        "move 8 from 5 to 6\n" +
        "move 13 from 8 to 9\n" +
        "move 12 from 3 to 5\n" +
        "move 12 from 5 to 9\n" +
        "move 1 from 7 to 9\n" +
        "move 1 from 1 to 2\n" +
        "move 1 from 1 to 4\n" +
        "move 3 from 8 to 5\n" +
        "move 1 from 2 to 5\n" +
        "move 1 from 4 to 8\n" +
        "move 5 from 6 to 3\n" +
        "move 1 from 8 to 4\n" +
        "move 13 from 4 to 7\n" +
        "move 3 from 7 to 6\n" +
        "move 1 from 1 to 4\n" +
        "move 4 from 4 to 2\n" +
        "move 1 from 6 to 3\n" +
        "move 2 from 5 to 9\n" +
        "move 2 from 5 to 9\n" +
        "move 1 from 4 to 8\n" +
        "move 6 from 9 to 4\n" +
        "move 22 from 9 to 2\n" +
        "move 8 from 7 to 4\n" +
        "move 7 from 2 to 1\n" +
        "move 3 from 3 to 8\n" +
        "move 2 from 6 to 7\n" +
        "move 14 from 4 to 2\n" +
        "move 2 from 6 to 1\n" +
        "move 1 from 8 to 7\n" +
        "move 3 from 3 to 9\n" +
        "move 1 from 8 to 4\n" +
        "move 3 from 1 to 9\n" +
        "move 3 from 9 to 3\n" +
        "move 31 from 2 to 8\n" +
        "move 8 from 8 to 4\n" +
        "move 1 from 9 to 1\n" +
        "move 9 from 4 to 5\n" +
        "move 7 from 5 to 6\n" +
        "move 2 from 5 to 1\n" +
        "move 1 from 2 to 1\n" +
        "move 1 from 7 to 9\n" +
        "move 1 from 2 to 9\n" +
        "move 2 from 6 to 4\n" +
        "move 4 from 7 to 4\n" +
        "move 4 from 9 to 8\n" +
        "move 6 from 4 to 1\n" +
        "move 1 from 3 to 2\n" +
        "move 1 from 3 to 6\n" +
        "move 1 from 9 to 2\n" +
        "move 2 from 2 to 4\n" +
        "move 1 from 9 to 1\n" +
        "move 1 from 3 to 1\n" +
        "move 17 from 1 to 9\n" +
        "move 4 from 6 to 2\n" +
        "move 1 from 9 to 7\n" +
        "move 4 from 9 to 7\n" +
        "move 1 from 8 to 4\n" +
        "move 3 from 7 to 6\n" +
        "move 1 from 4 to 9\n" +
        "move 10 from 8 to 5\n" +
        "move 6 from 6 to 5\n" +
        "move 1 from 7 to 2\n" +
        "move 1 from 1 to 4\n" +
        "move 1 from 4 to 5\n" +
        "move 9 from 8 to 3\n" +
        "move 4 from 3 to 9\n" +
        "move 1 from 4 to 6\n" +
        "move 1 from 6 to 5\n" +
        "move 1 from 4 to 8\n" +
        "move 2 from 3 to 8\n" +
        "move 1 from 3 to 8\n" +
        "move 3 from 8 to 9\n" +
        "move 5 from 2 to 9\n" +
        "move 1 from 7 to 9\n" +
        "move 5 from 8 to 7\n" +
        "move 3 from 8 to 4\n" +
        "move 2 from 8 to 5\n" +
        "move 24 from 9 to 7\n" +
        "move 1 from 3 to 5\n" +
        "move 2 from 9 to 4\n" +
        "move 22 from 7 to 9\n" +
        "move 1 from 3 to 4\n" +
        "move 6 from 4 to 6\n" +
        "move 4 from 6 to 7\n" +
        "move 10 from 5 to 3\n" +
        "move 8 from 3 to 5\n" +
        "move 2 from 3 to 4\n" +
        "move 2 from 4 to 6\n" +
        "move 10 from 7 to 3\n" +
        "move 21 from 9 to 1\n" +
        "move 2 from 3 to 4\n" +
        "move 4 from 3 to 8\n" +
        "move 2 from 4 to 8\n" +
        "move 1 from 7 to 8\n" +
        "move 4 from 6 to 8\n" +
        "move 3 from 5 to 4\n" +
        "move 8 from 8 to 2\n" +
        "move 18 from 1 to 6\n" +
        "move 3 from 4 to 1\n" +
        "move 1 from 2 to 8\n" +
        "move 5 from 1 to 8\n" +
        "move 3 from 3 to 6\n" +
        "move 3 from 2 to 9\n" +
        "move 3 from 8 to 1\n" +
        "move 11 from 5 to 2\n" +
        "move 3 from 8 to 7\n" +
        "move 10 from 2 to 9\n" +
        "move 1 from 7 to 9\n" +
        "move 3 from 8 to 1\n" +
        "move 2 from 7 to 8\n" +
        "move 6 from 9 to 5\n" +
        "move 4 from 2 to 8\n" +
        "move 8 from 5 to 8\n" +
        "move 1 from 3 to 7\n" +
        "move 2 from 5 to 6\n" +
        "move 3 from 1 to 6\n" +
        "move 2 from 1 to 6\n" +
        "move 4 from 9 to 8\n" +
        "move 4 from 9 to 8\n" +
        "move 1 from 9 to 4\n" +
        "move 9 from 6 to 9\n" +
        "move 16 from 6 to 9\n" +
        "move 1 from 4 to 7\n" +
        "move 1 from 2 to 9\n" +
        "move 5 from 8 to 5\n" +
        "move 4 from 5 to 1\n" +
        "move 6 from 1 to 7\n" +
        "move 12 from 8 to 4\n" +
        "move 5 from 8 to 1\n" +
        "move 6 from 9 to 3\n" +
        "move 1 from 1 to 6\n" +
        "move 2 from 5 to 8\n" +
        "move 12 from 4 to 7\n" +
        "move 2 from 8 to 4\n" +
        "move 1 from 4 to 8\n" +
        "move 1 from 7 to 6\n" +
        "move 1 from 4 to 6\n" +
        "move 14 from 7 to 1\n" +
        "move 3 from 3 to 2\n" +
        "move 7 from 9 to 7\n" +
        "move 3 from 3 to 5\n" +
        "move 15 from 1 to 2\n" +
        "move 2 from 5 to 9\n" +
        "move 1 from 8 to 9\n" +
        "move 16 from 9 to 1\n" +
        "move 1 from 5 to 9\n" +
        "move 5 from 6 to 2\n" +
        "move 12 from 7 to 2\n" +
        "move 20 from 2 to 6\n" +
        "move 10 from 2 to 6\n" +
        "move 11 from 1 to 7\n" +
        "move 2 from 7 to 4\n" +
        "move 2 from 2 to 5\n" +
        "move 1 from 2 to 3\n" +
        "move 2 from 5 to 6\n" +
        "move 1 from 9 to 5\n" +
        "move 1 from 5 to 9\n" +
        "move 25 from 6 to 7\n" +
        "move 25 from 7 to 6\n" +
        "move 1 from 3 to 1\n" +
        "move 1 from 2 to 5\n" +
        "move 1 from 4 to 3\n" +
        "move 33 from 6 to 3\n" +
        "move 1 from 9 to 5\n" +
        "move 2 from 3 to 5\n" +
        "move 28 from 3 to 9\n" +
        "move 5 from 1 to 9\n" +
        "move 4 from 1 to 8\n" +
        "move 2 from 3 to 2\n" +
        "move 2 from 8 to 1\n" +
        "move 1 from 4 to 6\n" +
        "move 3 from 5 to 3\n" +
        "move 1 from 2 to 4\n" +
        "move 2 from 2 to 8\n" +
        "move 1 from 6 to 5\n" +
        "move 30 from 9 to 2\n" +
        "move 7 from 2 to 6\n" +
        "move 1 from 1 to 3\n" +
        "move 1 from 1 to 7\n" +
        "move 1 from 5 to 6\n" +
        "move 1 from 5 to 4\n" +
        "move 5 from 7 to 4\n" +
        "move 4 from 7 to 3\n" +
        "move 1 from 3 to 7\n" +
        "move 3 from 8 to 7\n" +
        "move 8 from 3 to 1\n" +
        "move 3 from 1 to 7\n" +
        "move 4 from 1 to 4\n" +
        "move 3 from 9 to 8\n" +
        "move 8 from 6 to 2\n" +
        "move 18 from 2 to 6\n" +
        "move 6 from 7 to 2\n" +
        "move 1 from 1 to 7\n" +
        "move 3 from 4 to 7\n" +
        "move 5 from 4 to 8\n" +
        "move 2 from 8 to 7\n" +
        "move 7 from 2 to 5\n" +
        "move 5 from 2 to 7\n" +
        "move 10 from 7 to 9\n" +
        "move 5 from 5 to 9\n" +
        "move 1 from 3 to 9\n" +
        "move 5 from 2 to 6\n" +
        "move 3 from 7 to 9\n" +
        "move 2 from 5 to 6\n" +
        "move 2 from 2 to 9\n" +
        "move 2 from 8 to 7\n" +
        "move 1 from 4 to 5\n" +
        "move 8 from 9 to 2\n" +
        "move 5 from 6 to 7\n" +
        "move 4 from 9 to 1\n" +
        "move 4 from 8 to 9\n" +
        "move 12 from 9 to 1\n" +
        "move 16 from 1 to 4\n" +
        "move 12 from 6 to 2\n" +
        "move 3 from 7 to 6\n" +
        "move 3 from 7 to 3\n" +
        "move 1 from 9 to 4\n" +
        "move 12 from 4 to 5\n" +
        "move 2 from 4 to 3\n" +
        "move 1 from 7 to 1\n" +
        "move 4 from 4 to 3\n" +
        "move 1 from 8 to 2\n" +
        "move 6 from 3 to 1\n" +
        "move 1 from 1 to 8\n" +
        "move 7 from 2 to 5\n" +
        "move 1 from 8 to 1\n" +
        "move 4 from 5 to 4\n" +
        "move 5 from 5 to 9\n" +
        "move 1 from 3 to 8\n" +
        "move 1 from 9 to 7\n" +
        "move 1 from 8 to 1\n" +
        "move 4 from 5 to 6\n" +
        "move 5 from 5 to 9\n" +
        "move 7 from 9 to 5\n" +
        "move 11 from 6 to 4\n" +
        "move 1 from 9 to 4\n" +
        "move 1 from 9 to 1\n" +
        "move 1 from 7 to 2\n" +
        "move 9 from 4 to 3\n" +
        "move 5 from 1 to 9\n" +
        "move 3 from 5 to 1\n" +
        "move 5 from 9 to 8\n" +
        "move 8 from 3 to 1\n" +
        "move 2 from 5 to 3\n" +
        "move 7 from 2 to 5\n" +
        "move 1 from 6 to 4\n" +
        "move 3 from 5 to 9\n" +
        "move 3 from 6 to 9\n" +
        "move 3 from 2 to 9\n" +
        "move 5 from 3 to 6\n" +
        "move 1 from 9 to 5\n" +
        "move 4 from 8 to 3\n" +
        "move 1 from 8 to 4\n" +
        "move 8 from 1 to 3\n" +
        "move 12 from 3 to 7\n" +
        "move 1 from 2 to 4\n" +
        "move 3 from 2 to 8\n" +
        "move 6 from 7 to 6\n" +
        "move 4 from 5 to 7\n" +
        "move 5 from 9 to 7\n" +
        "move 2 from 9 to 2\n" +
        "move 1 from 9 to 5\n" +
        "move 4 from 5 to 1\n" +
        "move 1 from 5 to 4\n" +
        "move 14 from 7 to 6\n" +
        "move 1 from 1 to 7\n" +
        "move 10 from 4 to 5\n" +
        "move 4 from 1 to 2\n" +
        "move 1 from 4 to 6\n" +
        "move 1 from 7 to 4\n" +
        "move 17 from 6 to 8\n" +
        "move 1 from 5 to 7\n" +
        "move 10 from 5 to 4\n" +
        "move 1 from 2 to 6\n" +
        "move 4 from 2 to 6\n" +
        "move 13 from 6 to 1\n" +
        "move 9 from 4 to 3\n" +
        "move 2 from 2 to 4\n" +
        "move 1 from 6 to 7\n" +
        "move 1 from 4 to 3\n" +
        "move 8 from 3 to 5\n" +
        "move 1 from 3 to 4\n" +
        "move 17 from 1 to 3\n" +
        "move 15 from 3 to 7\n" +
        "move 3 from 4 to 1\n" +
        "move 6 from 8 to 9\n" +
        "move 6 from 9 to 1\n" +
        "move 2 from 3 to 1\n" +
        "move 2 from 5 to 2\n" +
        "move 6 from 7 to 6\n" +
        "move 3 from 6 to 9"

fun getTowers(): List<MutableList<String>> {
    val towers = "" +
            "[N]     [Q]         [N]            \n" +
            "[R]     [F] [Q]     [G] [M]        \n" +
            "[J]     [Z] [T]     [R] [H] [J]    \n" +
            "[T] [H] [G] [R]     [B] [N] [T]    \n" +
            "[Z] [J] [J] [G] [F] [Z] [S] [M]    \n" +
            "[B] [N] [N] [N] [Q] [W] [L] [Q] [S]\n" +
            "[D] [S] [R] [V] [T] [C] [C] [N] [G]\n" +
            "[F] [R] [C] [F] [L] [Q] [F] [D] [P]"

    val transposed = towers.replace("[", "").replace("]", "").replace("    ", ",").replace(" ",",").split("\n").map { it.split(",") }.transpose()
    transposed.map { it.reverse() }
    transposed.forEach { it.removeIf { it.isBlank() } }
    return transposed
}

data class Instruction(val amount: Int, val fromTower: Int, val toTower: Int)

fun getInstructions(): List<Instruction> {
    return instructions.split("\n").map { it.substring(5).split(" from | to ".toRegex()) }.map { Instruction(it[0].toInt(), it[1].toInt() - 1, it[2].toInt() - 1) }
}

fun doInstruction(instruction: Instruction, towers: List<MutableList<String>>) {
    val removedSlice = towers[instruction.fromTower].removeSlice(instruction.amount)
    towers[instruction.toTower].addAll(removedSlice)
}

fun doInstructionMaintainingOrder(instruction: Instruction, towers: List<MutableList<String>>) {
    val removedSlice = towers[instruction.fromTower].removeSlice(instruction.amount)
    removedSlice.reverse()
    towers[instruction.toTower].addAll(removedSlice)
}

fun partOne(): String {
    val towers = getTowers()
    val instructions = getInstructions()
    instructions.forEach { doInstruction(it, towers) }
    return towers.joinToString("") { it.last() }
}

fun partTwo(): String {
    val towers = getTowers()
    val instructions = getInstructions()
    instructions.forEach { doInstructionMaintainingOrder(it, towers) }
    return towers.joinToString("") { it.last() }
}

fun main() {
    println(partOne())
    println(partTwo())
}
