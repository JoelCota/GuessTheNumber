package lopez.joel.GuessTheNumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue=0
    var maxValue=100
    var num: Int=0
    var won: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val guessings: TextView= findViewById(R.id.guessings)
        val up: Button = findViewById(R.id.up)
        val down: Button= findViewById(R.id.down)
        val guessed: Button= findViewById(R.id.guessed)
        val generate:Button= findViewById(R.id.generate)

        generate.setOnClickListener{
            num= Random.nextInt(minValue,maxValue)
            guessings.setText(num.toString())
            generate.visibility= View.INVISIBLE
            guessed.visibility= View.VISIBLE
        }

        fun checkLimits():Boolean{
            return minValue!=maxValue
        }

        up.setOnClickListener{
            minValue=num
            if (checkLimits()){
            num=Random.nextInt(minValue,maxValue)
            guessings.setText(num.toString())
                }else{
                guessings.setText("Damn, You Won")
            }
        }

        down.setOnClickListener{
            maxValue=num
            if (checkLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Damn, You Won")
            }
        }
        guessed.setOnClickListener{
           if (!won){
                guessings.setText("I guess your number, your number is " + num)
                guessed.setText("Play again")
                won = true
            }else{
                generate.visibility=View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility=View.GONE
                minValue=0
                maxValue=100
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}