package aoc2022.day25

import java.math.BigInteger

private const val TEST = """1=-0-2
12111
2=0=
21
2=01
111
20012
112
1=-1=
1-12
12
1=
122"""
private const val INPUT = """1-11=21=22-0=22
12-0-2022-=1
11101-0---0=-1
2-
20
1-=2=-021=21211-1-01
1==0122002210-00
2=2122011=-=020-=0
2--0
1=1--=2
1-0
22==020-
1=1=1
221-2201=--
2=
1=21101=--1=1=-
112=-1
1---21-0-00==-=00=0
1-022-0=1
2=12=20
1222-000=-01-02
222=0
1010=21-01-20
1=1=2220210=11-210
1--2=1=0=00--0=
2102-1-==2-==-0
211=-==00
2=--=-1-=10=-2-21-
10-2=1-1
1=22-2=-0-1-101
102-00=0-1=1=022
11-=--
1=222
1==1--2210-=
22-01-1=-1010120
2==2-2=0-2--2
101
2-22-=20121
20-0=22
1-10020
1-==01
1-111-010=
1-
1022021=10210
2=102===1-0112
1--0-120=21-
101-2-0102-=10==22
11011=-12=1
1=22=-11=-002=1
1=-0=21102=0-=-
1=1=2--=2
21=12112
1111
2=2=-10201=-12
10--1=2-=012010=0
102=-=-1-
21
1-0-=-21221=11
1=1-=-1112=2
22
1=
1-=212-0
12-=200--1=11
10-=100=221=1-10=
2-2-02--0-0-1-0-02
10-011--20=0
1--10211--=
1--1=-=
1-2=0012==1=2
22-20=212
1=100-
12=001211
222012
2-21-=-01---212
11=002=-0=2022020
10==1=-=202
1====0020=2000001=
1=1--=0=2=2--2
222=2-1-002
2--====-00--2=02=0
1-==21111-11-
2211=1==0221
2=0002--
200-
1-1=1=-110==0
1-0-
22001=-01=1-
1--221===2-221212
1=2-
1=1
1=2==-0=10=02211=-
10=--0-212-
1-0=
11-=-=11=22=00=
11---
110201110-
2-0=-
2=12=12----121-0-
1==120==012220=-
1=00-0
102-=-2
122
20020
1=---1-1==0=1=
22=1110-=02-
120=20=020=0-=-11-
1-2-0
21=2==0-11==---2--0
112=
12222=20-000
1=110
2222=10-
1-2
111211-=
2=2=1--2220==2=02
111022=21100100121
20--==2-21=1
1000=-1=2202
21==--==11-1==0-=
1=0-2=21-1=2==2
111
21-0=11-2-220=-="""

val five = BigInteger.valueOf(5)
val conversion = mapOf("2" to 2, "1" to 1, "0" to 0, "-" to -1, "=" to -2)
fun main() {
    val sum = INPUT.split("\n").sumOf { it.toDecimal() }
    println(sum.toSnafu())
}

private fun String.toDecimal(): Long {
    return this.toList().reversed().mapIndexed { index, char ->
        char.toDecimal().toBigInteger().times(five.pow(index))
    }.sumOf { it.toLong() }
}

private fun Long.toSnafu(): String {
    val highestPower = IntRange(0, 20).first {
        (this + 1).toBigInteger() < IntRange(0, it).sumOf { powers ->
            five.pow(powers).times(BigInteger.valueOf(2))
        }
    }
    return convert(this, highestPower, "")
}

private fun convert(remaining: Long, power: Int, snafu: String): String {
    if (remaining == 0L) return snafu + IntRange(0, power).joinToString("") { "0" }
    if (remaining > leadingOne(power)) {
        return convert(remaining - 2 * five.pow(power).toLong(), power - 1, snafu + "2")
    }
    if (remaining < -leadingOne(power)) {
        return convert(remaining + 2 * five.pow(power).toLong(), power - 1, "$snafu=")
    }
    if (remaining > leadingTwo(power - 1)) {
        return convert(remaining - 1 * five.pow(power).toLong(), power - 1, snafu + "1")
    }
    if (remaining < -leadingTwo(power - 1)) {
        return convert(remaining + 1 * five.pow(power).toLong(), power - 1, "$snafu-")
    }
    return convert(remaining, power - 1, snafu + "0")
}

private fun leadingOne(power: Int): Long {
    return five.pow(power).toLong() + IntRange(0, power - 1).sumOf { five.pow(it).times(BigInteger.valueOf(2)) }
        .toLong()
}

private fun leadingTwo(power: Int): Long {
    return IntRange(0, power).sumOf { five.pow(it).times(BigInteger.valueOf(2)) }
        .toLong()
}

private fun Char.toDecimal(): Long {
    return conversion[this.toString()]!!.toLong()
}