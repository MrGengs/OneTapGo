package com.example.onetapgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView displayText;
    private TextView expressionText;
    private ImageView btnBack;

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    private Button buttonEquals, buttonClear, buttonDecimal, buttonPlusMinus, buttonPercent, buttonDelete;

    private String currentNumber = "0";
    private String currentExpression = "";
    private double firstOperand = 0;
    private String pendingOperation = "";
    private boolean isNewOperation = true;
    private boolean lastPressedEquals = false;

    // Format untuk angka
    private DecimalFormat formatter = new DecimalFormat("#,##0.########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Inisialisasi view
        displayText = findViewById(R.id.displayText);
        expressionText = findViewById(R.id.expressionText);
        btnBack = findViewById(R.id.btnBack);

        // Inisialisasi tombol angka
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Inisialisasi tombol operasi
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDecimal = findViewById(R.id.buttonDecimal);
        buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Set onClick listener untuk semua tombol angka
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        // Set onClick listener untuk tombol operasi
        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonDecimal.setOnClickListener(this);
        buttonPlusMinus.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        // Set onClick listener untuk tombol back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kembali ke activity sebelumnya
            }
        });

        // Update display awal
        updateDisplay();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        // Handle tombol angka
        if (viewId == R.id.button0) {
            handleNumberInput("0");
        } else if (viewId == R.id.button1) {
            handleNumberInput("1");
        } else if (viewId == R.id.button2) {
            handleNumberInput("2");
        } else if (viewId == R.id.button3) {
            handleNumberInput("3");
        } else if (viewId == R.id.button4) {
            handleNumberInput("4");
        } else if (viewId == R.id.button5) {
            handleNumberInput("5");
        } else if (viewId == R.id.button6) {
            handleNumberInput("6");
        } else if (viewId == R.id.button7) {
            handleNumberInput("7");
        } else if (viewId == R.id.button8) {
            handleNumberInput("8");
        } else if (viewId == R.id.button9) {
            handleNumberInput("9");
        }
        // Handle tombol operasi
        else if (viewId == R.id.buttonAdd) {
            handleOperation("+");
        } else if (viewId == R.id.buttonSubtract) {
            handleOperation("−");
        } else if (viewId == R.id.buttonMultiply) {
            handleOperation("×");
        } else if (viewId == R.id.buttonDivide) {
            handleOperation("÷");
        } else if (viewId == R.id.buttonEquals) {
            handleEquals();
        } else if (viewId == R.id.buttonClear) {
            handleClear();
        } else if (viewId == R.id.buttonDecimal) {
            handleDecimal();
        } else if (viewId == R.id.buttonPlusMinus) {
            handlePlusMinus();
        } else if (viewId == R.id.buttonPercent) {
            handlePercent();
        } else if (viewId == R.id.buttonDelete) {
            handleDelete();
        }
    }

    private void handleNumberInput(String number) {
        if (isNewOperation || currentNumber.equals("0") || lastPressedEquals) {
            // Jika ini adalah operasi baru atau angka saat ini adalah 0, ganti dengan angka baru
            currentNumber = number;
            isNewOperation = false;

            if (lastPressedEquals) {
                // Jika terakhir adalah equals, mulai perhitungan baru
                firstOperand = 0;
                pendingOperation = "";
                currentExpression = "";
                lastPressedEquals = false;
            }
        } else {
            // Tambahkan angka ke angka saat ini
            currentNumber += number;
        }
        updateDisplay();
    }

    private void handleOperation(String operation) {
        if (lastPressedEquals) {
            // Jika terakhir menekan equals, gunakan hasil sebagai operand pertama
            currentExpression = formatNumber(Double.parseDouble(currentNumber)) + " " + operation + " ";
            lastPressedEquals = false;
        } else if (!pendingOperation.isEmpty()) {
            // Jika sudah ada operasi tertunda, hitung dulu
            double secondOperand = Double.parseDouble(currentNumber);
            firstOperand = performOperation(firstOperand, secondOperand, pendingOperation);
            currentNumber = String.valueOf(firstOperand);
            currentExpression = formatNumber(firstOperand) + " " + operation + " ";
        } else {
            // Simpan operand pertama dan operasi
            firstOperand = Double.parseDouble(currentNumber);
            currentExpression = formatNumber(firstOperand) + " " + operation + " ";
        }

        pendingOperation = operation;
        isNewOperation = true;
        updateDisplay();
    }

    private void handleEquals() {
        if (pendingOperation.isEmpty()) {
            // Tidak ada operasi tertunda
            return;
        }

        // Ambil operand kedua
        double secondOperand = Double.parseDouble(currentNumber);

        // Simpan ekspresi lengkap
        String fullExpression = currentExpression + formatNumber(secondOperand) + " = ";

        // Lakukan operasi
        double result = performOperation(firstOperand, secondOperand, pendingOperation);

        // Update display
        currentNumber = String.valueOf(result);
        currentExpression = fullExpression;

        // Reset status
        pendingOperation = "";
        isNewOperation = true;
        lastPressedEquals = true;

        updateDisplay();
    }

    private double performOperation(double a, double b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "−":
                return a - b;
            case "×":
                return a * b;
            case "÷":
                if (b == 0) {
                    // Menangani pembagian dengan nol
                    return Double.NaN;
                }
                return a / b;
            default:
                return b;
        }
    }

    private void handleClear() {
        currentNumber = "0";
        currentExpression = "";
        firstOperand = 0;
        pendingOperation = "";
        isNewOperation = true;
        lastPressedEquals = false;
        updateDisplay();
    }

    private void handleDecimal() {
        if (isNewOperation) {
            currentNumber = "0.";
            isNewOperation = false;
            lastPressedEquals = false;
        } else if (!currentNumber.contains(".")) {
            currentNumber += ".";
        }
        updateDisplay();
    }

    private void handlePlusMinus() {
        if (currentNumber.equals("0")) {
            return;
        }

        if (currentNumber.startsWith("-")) {
            currentNumber = currentNumber.substring(1);
        } else {
            currentNumber = "-" + currentNumber;
        }
        updateDisplay();
    }

    private void handlePercent() {
        if (!currentNumber.equals("0")) {
            double value = Double.parseDouble(currentNumber) / 100;
            currentNumber = String.valueOf(value);
            updateDisplay();
        }
    }

    private void handleDelete() {
        if (currentNumber.length() > 1) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        } else {
            currentNumber = "0";
        }
        updateDisplay();
    }

    private void updateDisplay() {
        // Format angka agar lebih mudah dibaca
        try {
            double number = Double.parseDouble(currentNumber);

            // Cek jika NaN
            if (Double.isNaN(number)) {
                displayText.setText("Error");
                return;
            }

            // Format angka
            String formattedNumber = formatNumber(number);
            displayText.setText(formattedNumber);
        } catch (NumberFormatException e) {
            // Jika parsing gagal (misalnya saat input adalah "0.")
            displayText.setText(currentNumber);
        }

        // Update expression text
        expressionText.setText(currentExpression);
    }

    private String formatNumber(double number) {
        // Jika angka adalah bilangan bulat, hilangkan desimal
        if (number == (long) number) {
            return String.format("%,d", (long) number);
        } else {
            return formatter.format(number);
        }
    }
}
