package fr.app.myapplicationcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    // Declarer les variable privé au tout début. On ne pourra y acceder que sur cette page
    private var selectedDate :TextView? = null
    private var ageInMinutes :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn :Button = findViewById<Button>(R.id.dpd)
        // On crée la variable pour le TexView et on lui dit qu'il est null par ce qu'on ne veut pas l'initialiser
        selectedDate = findViewById(R.id.tvSelectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes)

        datePickerBtn.setOnClickListener {
            clickDatePicker()
            //selectedDate = findViewById(R.id.tvSelectedDate)
            //ageInMinutes = findViewById(R.id.ageInMinutes)
        }


    }

    private fun clickDatePicker(){
        // Creation d'une nouvelle instance de Calendar. Un import est fait au-dessus
        val myCalendar = Calendar.getInstance()

        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this, "$selectedYear, ${selectedMonth+1}, $selectedDay", Toast.LENGTH_SHORT).show()

                val date = "$selectedDay/${selectedMonth+1}/$selectedYear"
                // "?" car on la declarer "null"
                selectedDate?.setText(date)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
                // Format la date passer en argument au pattern defini dans "sdf"
                val theDate = sdf.parse(date)

                // Verifie si theDate existe: si oui il execute le code
                theDate?.let{
                    // Obtentions de la date en minute
                    // Returns the number of milliseconds since January 1, 1970
                    val selectedDateInMinutes = theDate.time / 60000
                    // on Obtient le temps exact qui s'est ecouler entre la date actuelle et 1970
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    // Verifie si currentDate existe (est valide): si oui execute le code
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000
                        // Type : long
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        // Text accepte que du "string" donc on doit le convertir
                        ageInMinutes?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        // Definit une date maximale selectionnable a la veille. On recupere le temps en miliseconde actuelle depuis 1970 - 86400000 qui correspond a 24h en milliseconde
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()



    }
}