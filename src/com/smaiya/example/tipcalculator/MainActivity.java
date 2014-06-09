package com.smaiya.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText etBill;
	private EditText etTipPercentage;
	private EditText etNumOfPersons;
	private TextView tvTipAmount;
	private TextView tvTotalAmount;
	private TextView tvTotalPerPerson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setTheme(android.R.style.Theme_Wallpaper);

		etBill = (EditText) findViewById(R.id.etAmount);
		etTipPercentage = (EditText) findViewById(R.id.etPercentage);
		etNumOfPersons = (EditText) findViewById(R.id.etNumPersons);

		tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
		tvTotalAmount = (TextView) findViewById(R.id.tvTotalAmount);
		tvTotalPerPerson = (TextView) findViewById(R.id.tvTotalPerPerson);

		// Initialize values
		InitializeValues();

		/*
		 * Text watcher to listen any text change on the UI
		 */
		TextWatcher twChanges = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				TipChangeCalculator();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		};

		etBill.addTextChangedListener(twChanges);
		etTipPercentage.addTextChangedListener(twChanges);
		etNumOfPersons.addTextChangedListener(twChanges);
	}

	/*
	 * Initialize values
	 */
	private void InitializeValues() {
		etBill.setText("0.00");
		etTipPercentage.setText("0");
		etNumOfPersons.setText("1");

		tvTipAmount.setText("$ 0.0");
		tvTotalAmount.setText("$ 0.0");
		tvTotalPerPerson.setText("$ 0.0");
	}

	/*
	 * OnClick of Reset Button
	 */
	public void onReset(View v) {
		InitializeValues();
	}

	/*
	 * OnClick of Exit button
	 */
	public void onExit(View v) {
		this.finish();
	}

	/*
	 * Method to calculate Tip on any of text change
	 */
	private void TipChangeCalculator() {
		Float totalAmount = null; 
		Float tipPercentage = null;
		Integer numOfPersons = null;
		
		if(!etBill.getText().toString().isEmpty())
			 totalAmount = Float.valueOf(etBill.getText().toString());
		if(!etTipPercentage.getText().toString().isEmpty())
			tipPercentage = Float.valueOf(etTipPercentage.getText().toString());
		if(!etNumOfPersons.getText().toString().isEmpty())
			numOfPersons = Integer.valueOf(etNumOfPersons.getText().toString());

		if ((numOfPersons != null) && (totalAmount != null) && (tipPercentage != null) ) {
			Float totalTip = (totalAmount * tipPercentage) / 100;
			Float tipPerPerson = totalTip / numOfPersons;
			Float totalWithTip = totalAmount + totalTip;
			Float totalPerPerson = totalWithTip / numOfPersons;

			//Set the UI with the calculated values
			tvTipAmount.setText("$ "+String.valueOf(tipPerPerson));
			tvTotalAmount.setText("$ "+String.valueOf(totalWithTip));
			tvTotalPerPerson.setText("$ "+String.valueOf(totalPerPerson));
		}
	}
}
