package com.example.abccafe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment

class OrderFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var coffeeSpinner: Spinner
    private lateinit var sizeRadioGroup: RadioGroup
    private lateinit var placeOrderButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        nameEditText = view.findViewById(R.id.et_name)
        coffeeSpinner = view.findViewById(R.id.spinner_coffee)
        sizeRadioGroup = view.findViewById(R.id.radioGroup)  // This ID must match the XML
        placeOrderButton = view.findViewById(R.id.btn_order)

        placeOrderButton.setOnClickListener {
            placeOrder()
        }

        return view
    }

    private fun placeOrder() {
        val name = nameEditText.text.toString()
        val coffeeType = coffeeSpinner.selectedItem.toString()
        val selectedSizeId = sizeRadioGroup.checkedRadioButtonId
        val size = when (selectedSizeId) {
            R.id.radio_small -> "Small"
            R.id.radio_medium -> "Medium"
            R.id.radio_large -> "Large"
            else -> ""
        }

        if (name.isBlank() || size.isBlank()) {
            Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
            return
        }

        val orderMessage = "Order placed for $name: $size $coffeeType"
        Toast.makeText(context, orderMessage, Toast.LENGTH_LONG).show()
        // Additional actions can be added here
    }
}
