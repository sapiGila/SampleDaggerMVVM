package redrocketz.project.com.appmvvm.object;


import android.text.TextUtils;

import java.math.BigDecimal;

import redrocketz.project.com.appmvvm.mvvm1.enumeration.Operator;

public class Calculator {

    private Operator operator;
    private String inputValue1;
    private String inputValue2;

    public Calculator(Operator operator,
                      String inputValue1,
                      String inputValue2) {
        this.operator = operator;
        this.inputValue1 = inputValue1;
        this.inputValue2 = inputValue2;
    }

    public Operator getOperator() {
        return operator;
    }

    public BigDecimal getInputValue1() {
        return convertBigDecimalValue(inputValue1);
    }

    public BigDecimal getInputValue2() {
        return convertBigDecimalValue(inputValue2);
    }

    private BigDecimal convertBigDecimalValue(String text) {
        String value = "0";
        if (!TextUtils.isEmpty(text)) {
            value = text;
        }
        return BigDecimal.valueOf(Double.valueOf(value));
    }

    public static class CalculateEvent {
        private BigDecimal result;

        public CalculateEvent(BigDecimal result) {
            this.result = result;
        }

        public BigDecimal getResult() {
            return result;
        }
    }
}
