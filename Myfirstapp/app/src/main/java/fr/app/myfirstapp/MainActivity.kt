package fr.app.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// Ue activité est un écran
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // La ci-dessous est un "render" qui connecte le fichier ".kt" au ".xml"
        setContentView(R.layout.activity_main)

        val mytext = findViewById<TextView>(R.id.mytext)
        val btnClickOnMe = findViewById<Button>(R.id.mybutton)
        val resetBtn = findViewById<Button>(R.id.rstBtn)

        var timesClicked = 0
        // Déclaration d'une variable = "doc.query(getElementById.id)
        // btnClickOnMe.text = "haha"

        // Ecouteur d'evenement qui ajoute "1" au compteur
        btnClickOnMe.setOnClickListener {
            // btnClickOnMe.text = "Haha you clicked one me"
            mytext.text = timesClicked++.toString()
            // "timesClicked" est du type "number". On utilise "toString()"
            // pour le passer en "string" afin qu'il coïncide avec le type d'affichage
            if (timesClicked > 10){
                Toast.makeText(this, "t'es content ?!!", Toast.LENGTH_LONG).show()
            }
        }

        // Ecouteur d'evenement qui reset le tout
        resetBtn.setOnClickListener {
            timesClicked = 0
            mytext.text = "0"
            // Widget qui permet l'affichage d'un "POP-UP". Methode chaînée
            Toast.makeText(this, "Maintenant dégage !", Toast.LENGTH_LONG).show()
            // "Toast()" prend 3 parametres: 1) le contexte : "this" pour dire "cette application",
            // 2) La chaine de caractere,
            // 3) La durée d'affichage (LONG_LENGTH || SHORT_LENGTH)
            // "show()", methode de Toast, affiche a l'écran le "POP-UP"
        }



    }
}