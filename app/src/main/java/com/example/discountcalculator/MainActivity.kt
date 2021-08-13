package com.example.discountcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.discountcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateDiscount()}
    }

    private fun calculateDiscount() {
        val stringValue1 = binding.costOfItem.text.toString()
        val stringValue2 = binding.otherInput.text.toString()
        val cost = stringValue1.toDoubleOrNull()
        val otherDiscount = stringValue2.toDoubleOrNull()
        if (cost == null) {
            binding.discountResult.text = ""
            return
        }
        val discountPercentage = when (binding.discountOptions.checkedRadioButtonId) {
            R.id.ten_percent -> 0.10
            R.id.twenty_percent -> 0.20
            R.id.fifty_percent -> 0.50
            R.id.other -> otherDiscount?.div(100)
            else -> return

        }
        var discount = discountPercentage?.times(cost)
        NumberFormat.getCurrencyInstance()
        val formattedDiscount = NumberFormat.getCurrencyInstance().format(discount)
        binding.discountResult.text = getString(R.string.discount_amount, formattedDiscount)

        var newPrice = cost - discount!!
        NumberFormat.getCurrencyInstance()
        val updatedPrice = NumberFormat.getCurrencyInstance().format(newPrice)
        binding.discountedPrice.text = getString(R.string.discounted_price, updatedPrice)


    }
}