package hu.prooktatas.huszonegy

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import hu.prooktatas.huszonegy.model.Card
import java.util.*

class ImprovementsActivity : AppCompatActivity() {

    lateinit var linearLayout: LinearLayout

    private val deck = Card.buildDeck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_improvements)

        linearLayout = findViewById(R.id.linearLayout)

        Log.d("Improvements", "onCreate")

        loadCards()
    }


    private fun loadCards() {
        Collections.shuffle(deck)

        Log.d("Improvements", "Deck: $deck")

        for(c: Card in deck) {
            val resourceId = resources.getIdentifier(c.imageName, "drawable", packageName)
            val image = ImageView(this)
            image.setImageResource(resourceId)
            linearLayout.addView(image)
        }

    }

}