package hu.prooktatas.huszonegy.model

import java.util.*

class Card(val color: Color, val value: NumericValue) {

    enum class Color(val title: String) {
        PIROS("Piros"),
        ZOLD("Zöld"),
        TOK("Tök"),
        MAKK("Makk");

        override fun toString(): String {
            return title
        }

        val imageNamePart: String
            get() {
                return when(this) {
                    PIROS -> "p"
                    ZOLD -> "z"
                    TOK -> "t"
                    MAKK -> "m"
                }
            }
    }

    enum class NumericValue(val num: Int) {
        ALSO(2),
        FELSO(3),
        KIRALY(4),
        ASZ(11),
        HETES(7),
        NYOLCAS(8),
        KILENCES(9),
        TIZES(10);

        val imageNamePart: String
            get() {
                return when(this) {
                    ALSO -> "_also"
                    FELSO -> "_felso"
                    KIRALY -> "_kiraly"
                    ASZ -> "_asz"
                    HETES -> "_7"
                    NYOLCAS -> "_8"
                    KILENCES -> "_9"
                    TIZES -> "_10"
                }
            }

        override fun toString(): String {
            return when(this) {
                ALSO -> "alsó"
                FELSO -> "felső"
                KIRALY -> "király"
                ASZ -> "ász"
                HETES -> "VII"
                NYOLCAS -> "VIII"
                KILENCES -> "IX"
                TIZES -> "X"
            }
        }
    }

    val imageName = "${color.imageNamePart}${value.imageNamePart}"

    override fun toString(): String {
        return "$color $value"
    }

    companion object {
        fun buildDeck(): List<Card> {

            val deck = mutableListOf<Card>()

            for (c: Color in Color.values()) {
                for (num: NumericValue in NumericValue.values()) {
                    val card = Card(c, num)
                    deck.add(card)
                }
            }

            return deck
        }
    }
}