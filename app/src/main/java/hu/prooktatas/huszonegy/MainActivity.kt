package hu.prooktatas.huszonegy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import hu.prooktatas.huszonegy.model.Card
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Jatekszabaly: https://www.kartya-jatek.hu/huszonegy/
 */

class MainActivity : AppCompatActivity() {

    lateinit var popButton: Button
    lateinit var frameLayout: FrameLayout
    lateinit var tvSum: TextView

    private var sum = 0
    private val deck = Card.buildDeck()

    private val cardOnTop: Card
        get() = deck[frameLayout.childCount - 1]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popButton = findViewById(R.id.button)
        frameLayout = findViewById(R.id.frameLayout)
        tvSum = findViewById(R.id.tvSum)

        popButton.setOnClickListener {
            popView()
            peek()
        }

        loadCards()
    }



    private fun loadCards() {
        Collections.shuffle(deck)

        for(c: Card in deck) {
            val resourceId = resources.getIdentifier(c.imageName, "drawable", packageName)
            val image = ImageView(this)
            image.setImageResource(resourceId)
            frameLayout.addView(image)
        }

        sum += cardOnTop.value.num
        tvSum.text = sum.toString()

        Log.d(TAG, "First card: $cardOnTop")
    }

    private fun popView() {
        val index = frameLayout.childCount - 1

        if (index >= 0) {
            frameLayout.removeViewAt(index)
        } else {
            popButton.isEnabled = false
        }
    }

    private fun peek() {
        Log.d(TAG, "New card on top: $cardOnTop")
        sum += cardOnTop.value.num
        tvSum.text = sum.toString()

        when(sum) {
            21 -> hasEnded("Pont 21! Nyertél!")
            in 22..Int.MAX_VALUE -> hasEnded("A játék itt véget ért: $sum")
        }

    }

    private fun hasEnded(endMessage: String) {
        tvSum.text = endMessage
        button.isEnabled = false
    }

}

const val TAG = "KZs"
