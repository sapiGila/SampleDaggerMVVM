package redrocketz.project.com.appmvvm.mvvm2.calculator.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import redrocketz.project.com.appmvvm.R;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.CalculatorViewModel;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.factory.CalculatorViewModelFactory;
import redrocketz.project.com.appmvvm.mvvm2.common.Response;

/**
 * Created by snyind on 1/18/18.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result_value)
    TextView resultValue;

    private CalculatorViewModel viewModel;

    @Inject
    CalculatorViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel.class);
        viewModel.response().observe(this, this::processResponse);
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

    @OnClick(R.id.button_go_to_calc)
    public void onButtonGoToCalcClicked() {
        startActivity(new Intent(this, CalculatorActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.mainBinding2();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.unBinding();
    }
}
