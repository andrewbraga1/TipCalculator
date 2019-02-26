package br.unifor.ads.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {


    private lateinit var mEditTextBill: EditText
    private lateinit var mSeekBarPercentage: SeekBar
    private lateinit var mTextViewFixedPercentage: TextView
    private lateinit var mTextViewFixedPercentageValue: TextView
    private lateinit var mTextViewFixedPercentageResult: TextView
    private lateinit var mTextViewVariablePercentage: TextView
    private lateinit var mTextViewVariablePercentageValue: TextView
    private lateinit var mTextViewVariablePercentageResult: TextView
    var billValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var perc = "%"
        mSeekBarPercentage = findViewById(R.id.main_seekbar_percentage)
        mSeekBarPercentage.setOnSeekBarChangeListener(this)

        //Atribuicao do limite inferior

        //em string
        mTextViewFixedPercentage = findViewById(R.id.main_edittext_fixed_percentage)
        val fixed_percentage_string = 10.toString()
        mTextViewFixedPercentage.setText("$fixed_percentage_string$perc")





        mTextViewVariablePercentage = findViewById(R.id.main_edittext_variable_percentage)
        mTextViewVariablePercentageValue = findViewById(R.id.main_edittext_variable_percentage_value)
        mTextViewVariablePercentageResult = findViewById(R.id.main_edittext_percentage_variable_result)



        mEditTextBill = findViewById(R.id.main_edittext_bill)
        mEditTextBill.setOnEditorActionListener { view, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE){
                val billValue = mEditTextBill.text.toString().toDouble()
                var aux = casas(billValue);
                val auxString = aux.toString()
                val cifrao="R$"
                val billValueConcat = "$cifrao $auxString"
                mEditTextBill.setText(billValueConcat);

                //em valor
                mTextViewFixedPercentageValue = findViewById(R.id.main_edittext_fixed_percentage_value)
                mTextViewFixedPercentageValue.text = minTipValue(billValue).toString()
                mTextViewFixedPercentageResult = findViewById(R.id.main_edittext_fixed_percentage_result)
                mTextViewFixedPercentageResult.text = minTipResult(billValue).toString()
                //mTextViewFixedPercentageResult.text = billValueConcat
            }

            false
        }



    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        mTextViewVariablePercentage.text = progress.toString()+"%"
        mTextViewVariablePercentageValue.text = "R$"+ (billValue.toString().toDouble()*(progress.toDouble()/100))
        mTextViewVariablePercentageResult.text = "R$" + (billValue.toString().toDouble()+ billValue.toString().toDouble()*(progress.toDouble()/100))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        
    }
    fun casas(v:Double ):Double{
        val value = v/100
        return value
    }
    fun minTipValue(v:Double):Double{
       val minTip = v/10
        return minTip
    }
    fun minTipResult(v:Double):Double{
        val Tip = v/10 + v
        return Tip
    }

}
