package com.example.jsonapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tvDate: TextView
    lateinit var euroValueInput: EditText
    lateinit var spinnerCurrencies: Spinner
    lateinit var cur: Currency.Cur

    private lateinit var convertButton: Button
    private lateinit var convertResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = "Currency converter"

        tvDate = findViewById(R.id.tvDate)
        euroValueInput = findViewById(R.id.etEuroValueInput)
        spinnerCurrencies = findViewById(R.id.spinner)
        val adaptor = ArrayAdapter.createFromResource(
            this,
            R.array.currencies_array,
            R.layout.support_simple_spinner_dropdown_item
        )
        spinnerCurrencies.adapter = adaptor

        convertButton = findViewById(R.id.btnConverter)
        convertResult = findViewById(R.id.tvConvertResult)

        //API --------------------------------------------
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val call: Call<Currency?>? = apiInterface!!.doGetListResources()

        call?.enqueue(object : Callback<Currency?> {
            override fun onResponse(
                call: Call<Currency?>?,
                response: Response<Currency?>
            ){
                val resource: Currency? = response.body()
                val date = resource?.date
                cur = resource?.eur!!

                tvDate.text = date
            }

            override fun onFailure(call: Call<Currency?>, t: Throwable) {
                call.cancel()
            }
        })
        //---------------------------------------------------

        convertButton.setOnClickListener {
            if(euroValueInput.text.isNotEmpty()){
                when(spinnerCurrencies.selectedItemPosition){
                    0 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.ada!!).toString()
                    1 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.aed!!).toString()
                    2 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.afn!!).toString()
                    3 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.all!!).toString()
                    4 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.amd!!).toString()
                    5 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.ang!!).toString()
                    6 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.aoa!!).toString()
                    7 -> convertResult.text = (euroValueInput.text.toString().toFloat() * cur.ars!!).toString()
                }
            }else{
                convertResult.text = "Enter number first"
            }
        }

    }


}