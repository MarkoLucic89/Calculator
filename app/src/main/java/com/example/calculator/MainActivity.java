package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CALCULATION = "calculation";
    private TextView textViewCalculation;
    private EditText editTextDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        closeKeyboard();
    }

    private void closeKeyboard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                editTextDisplay.setShowSoftInputOnFocus(false);
        }
    }

    private void init() {
        textViewCalculation = findViewById(R.id.textViewCalculation);
        editTextDisplay = findViewById(R.id.editTextDisplay);
    }

    private void updateText(String s) {
        String oldString = editTextDisplay.getText().toString();
        int cursorPosition = editTextDisplay.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        editTextDisplay.setText(String.format("%s%s%s", leftString, s, rightString));
        editTextDisplay.setSelection(cursorPosition + s.length());
    }

    public void buttonZero(View view) {
        updateText(getResources().getString(R.string.zeroText));
    }

    public void buttonOne(View view) {
        updateText(getResources().getString(R.string.oneText));
    }

    public void buttonTwo(View view) {
        updateText(getResources().getString(R.string.twoText));
    }

    public void buttonThree(View view) {
        updateText(getResources().getString(R.string.threeText));
    }

    public void buttonFour(View view) {
        updateText(getResources().getString(R.string.fourText));
    }

    public void buttonFive(View view) {
        updateText(getResources().getString(R.string.fiveText));
    }

    public void buttonSix(View view) {
        updateText(getResources().getString(R.string.sixText));
    }

    public void buttonSeven(View view) {
        updateText(getResources().getString(R.string.sevenText));
    }

    public void buttonEight(View view) {
        updateText(getResources().getString(R.string.eightText));
    }

    public void buttonNine(View view) {
        updateText(getResources().getString(R.string.nineText));
    }

    public void buttonDecimal(View view) {
        updateText(getResources().getString(R.string.decimalText));
    }

    public void buttonParenthesesOpen(View view) {
        updateText(getResources().getString(R.string.parenthesesOpenText));
    }

    public void buttonParenthesesClose(View view) {
        updateText(getResources().getString(R.string.parenthesesCloseText));
    }

    public void buttonAdd(View view) {
        updateText(getResources().getString(R.string.addText));
    }

    public void buttonSubtract(View view) {
        updateText(getResources().getString(R.string.subtractText));
    }

    public void buttonMultiply(View view) {
        updateText(getResources().getString(R.string.multiplyText));
    }

    public void buttonDivide(View view) {
        updateText(getResources().getString(R.string.divideText));
    }

    public void buttonEquals(View view) {
        String stringExpression = editTextDisplay.getText().toString();
        stringExpression = stringExpression.replaceAll(getResources().getString(R.string.multiplyText), "*");
        stringExpression = stringExpression.replaceAll(getResources().getString(R.string.divideText), "/");
        Expression expression = new Expression(stringExpression);
        String result = String.valueOf(expression.calculate());
        textViewCalculation.setText(stringExpression);
        editTextDisplay.setText(String.format("%12s", result));
        editTextDisplay.setSelection(result.length());
    }

    public void buttonBackspace(View view) {
        int cursorPosition = editTextDisplay.getSelectionStart();
        int textLength = editTextDisplay.getText().length();
        if (cursorPosition > 0 && textLength > 0) {
            SpannableStringBuilder spannableStringBuilder = SpannableStringBuilder.valueOf(editTextDisplay.getText().toString());
            spannableStringBuilder.replace(cursorPosition - 1, cursorPosition, "");
            editTextDisplay.setText(spannableStringBuilder);
            editTextDisplay.setSelection(cursorPosition - 1);
        }
    }

    public void buttonClear(View view) {
        editTextDisplay.setText("");
        textViewCalculation.setText("");
    }

    public void buttonSin(View view) {
        updateText("sin(");
    }

    public void buttonCos(View view) {
        updateText("cos(");
    }

    public void buttonTan(View view) {
        updateText("tan(");
    }

    public void buttonArcSin(View view) {
        updateText("arcsin(");
    }

    public void buttonArcCos(View view) {
        updateText("arccos(");
    }

    public void buttonArcTan(View view) {
        updateText("arctan(");
    }

    public void buttonLog(View view) {
        updateText("log(");
    }

    public void buttonNaturalLog(View view) {
        updateText("ln(");
    }

    public void buttonSquareRoot(View view) {
        updateText("sqrt(");
    }

    public void buttonE(View view) {
        updateText("e");
    }

    public void buttonPi(View view) {
        updateText("pi");
    }

    public void buttonAbsoluteValue(View view) {
        updateText("abs(");
    }

    public void buttonIsPrime(View view) {
        updateText("ispr(");
    }

    public void buttonXSquared(View view) {
        updateText("^(2)");
    }

    public void buttonXPowerY(View view) {
        updateText("^(");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String calculation = textViewCalculation.getText().toString();
        outState.putString(KEY_CALCULATION, calculation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String calculationRestored = savedInstanceState.getString(KEY_CALCULATION);
        textViewCalculation.setText(calculationRestored);
    }
}