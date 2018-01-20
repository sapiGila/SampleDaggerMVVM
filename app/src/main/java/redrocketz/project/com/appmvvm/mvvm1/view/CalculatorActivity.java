package redrocketz.project.com.appmvvm.mvvm1.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import redrocketz.project.com.appmvvm.R;
import redrocketz.project.com.appmvvm.mvvm1.app.CalculatorApp;
import redrocketz.project.com.appmvvm.mvvm1.enumeration.Operator;
import redrocketz.project.com.appmvvm.object.Calculator;
import redrocketz.project.com.appmvvm.mvvm1.viewmodel.CalculatorViewModel;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.input_value_1)
    EditText inputValue1;
    @BindView(R.id.operation_selector)
    Spinner operationSelector;
    @BindView(R.id.input_value_2)
    EditText inputValue2;
    @BindView(R.id.result_value)
    TextView resultValue;

    @NonNull
    private CompositeDisposable compositeDisposable;

    @NonNull
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        initSpinner();
        calculatorViewModel = getViewModel();
    }

    @NonNull
    private CalculatorViewModel getViewModel() {
        return ((CalculatorApp) getApplication()).getViewModel();
    }

    private void initSpinner() {
        //initialize spinner
        ArrayList<Operator> operators = new ArrayList<>(EnumSet.allOf(Operator.class));
        ArrayAdapter<Operator> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, operators);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSelector.setAdapter(dataAdapter);
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(calculatorViewModel.calculate()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDisplay));
    }

    private void unBind() {
        compositeDisposable.clear();
    }

    @OnClick(R.id.calculate_button)
    public void onViewClicked() {
        Calculator request = new Calculator((Operator) operationSelector.getAdapter()
                .getItem(operationSelector.getSelectedItemPosition()),
                inputValue1.getText().toString(),
                inputValue2.getText().toString());
        calculatorViewModel.handleCalculate(request);
    }

    private void updateDisplay(BigDecimal value) {
        resultValue.setText(String.valueOf(value));
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }
}
