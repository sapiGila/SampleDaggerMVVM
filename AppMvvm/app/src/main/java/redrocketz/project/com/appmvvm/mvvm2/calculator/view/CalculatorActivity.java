package redrocketz.project.com.appmvvm.mvvm2.calculator.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import redrocketz.project.com.appmvvm.R;
import redrocketz.project.com.appmvvm.mvvm1.enumeration.Operator;
import redrocketz.project.com.appmvvm.object.Calculator;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.CalculatorViewModel;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.factory.CalculatorViewModelFactory;
import redrocketz.project.com.appmvvm.mvvm2.common.Response;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.input_value_1)
    EditText inputValue1;
    @BindView(R.id.operation_selector)
    Spinner operationSelector;
    @BindView(R.id.input_value_2)
    EditText inputValue2;
    @BindView(R.id.result_value)
    TextView resultValue;

    private CalculatorViewModel viewModel;

    @Inject
    CalculatorViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        initSpinner();
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel.class);
        viewModel.response().observe(this, this::processResponse);
    }

    private void initSpinner() {
        //initialize spinner
        ArrayList<Operator> operators = new ArrayList<>(EnumSet.allOf(Operator.class));
        ArrayAdapter<Operator> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, operators);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSelector.setAdapter(dataAdapter);
    }

    @OnClick(R.id.calculate_button)
    public void onViewClicked() {
        Calculator request = new Calculator((Operator) operationSelector.getAdapter()
                .getItem(operationSelector.getSelectedItemPosition()),
                inputValue1.getText().toString(),
                inputValue2.getText().toString());
        viewModel.handleCalculate(request);
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                resultValue.setText("Please Wait");
                break;

            case SUCCESS:
                updateDisplay(response.data);
                break;

            case ERROR:
                resultValue.setText(response.error.getMessage());
                break;
        }
    }

    private void updateDisplay(BigDecimal value) {
        resultValue.setText(String.valueOf(value));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.calculatorBinding2();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.unBinding();
    }
}
