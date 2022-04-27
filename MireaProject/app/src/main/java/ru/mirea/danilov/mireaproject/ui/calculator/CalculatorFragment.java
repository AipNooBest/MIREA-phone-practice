package ru.mirea.danilov.mireaproject.ui.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import ru.mirea.danilov.mireaproject.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {
    private FragmentCalculatorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalculatorViewModel calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);

        final TextView textView = binding.output;
        calculatorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        // Bind onClickListener to all buttons
        binding.zero.setOnClickListener(this::onClicked);
        binding.one.setOnClickListener(this::onClicked);
        binding.two.setOnClickListener(this::onClicked);
        binding.three.setOnClickListener(this::onClicked);
        binding.four.setOnClickListener(this::onClicked);
        binding.five.setOnClickListener(this::onClicked);
        binding.six.setOnClickListener(this::onClicked);
        binding.seven.setOnClickListener(this::onClicked);
        binding.eight.setOnClickListener(this::onClicked);
        binding.nine.setOnClickListener(this::onClicked);
        binding.plus.setOnClickListener(this::onClicked);
        binding.minus.setOnClickListener(this::onClicked);
        binding.multi.setOnClickListener(this::onClicked);
        binding.divide.setOnClickListener(this::onClicked);
        binding.equals.setOnClickListener(this::onClicked);
        binding.clear.setOnClickListener(this::onClicked);
        binding.dot.setOnClickListener(this::onClicked);

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onClicked(@NonNull View view) {
        String tag = view.getTag().toString();
        Log.d("TAG", tag);
        String outputString = binding.output.getText().toString();
        Log.d("TAG", outputString);

        if (isOperator(tag.charAt(0)) && isOperator(outputString.charAt(outputString.length() - 1))) {
            binding.output.setText(outputString);
        }
        else if (tag.equals("=")) {
            if (isOperator(outputString.charAt(outputString.length() - 1))) {
                outputString = outputString.substring(0, outputString.length() - 1);
            }
            binding.output.setText(String.valueOf(calculate(outputString)));
        }
        else if (tag.equals("C")) {
            binding.output.setText("0");
        }
        else if (binding.output.getText().toString().equals("0")) {
            binding.output.setText(tag);
        }
        else {
            binding.output.setText(outputString + tag);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private double calculate(String text) {
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (isOperator(text.charAt(i))) {
                numbers.add(number.toString());
                number = new StringBuilder();
                operators.add(String.valueOf(text.charAt(i)));
            } else {
                number.append(text.charAt(i));
            }
        }
        numbers.add(number.toString());
        double result = Double.parseDouble(numbers.get(0));
        for (int i = 0; i < operators.size(); i++) {
            switch (operators.get(i)) {
                case "+":
                    result += Double.parseDouble(numbers.get(i + 1));
                    break;
                case "-":
                    result -= Double.parseDouble(numbers.get(i + 1));
                    break;
                case "*":
                    result *= Double.parseDouble(numbers.get(i + 1));
                    break;
                case "/":
                    result /= Double.parseDouble(numbers.get(i + 1));
                    break;
            }
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}