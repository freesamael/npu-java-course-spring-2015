/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.text.DecimalFormat;
import java.util.Observable;

/**
 * The model class of the calculator application.
 */
public class Calculator extends Observable {

    public static final int MAX_INPUT_LENGTH = 10;

    /**
     * The available operators of the calculator.
     */
    public enum Operator {

        CLEAR,
        CLEAR_ENTRY,
        BACKSPACE,
        EQUAL,
        PLUS,
        MINUS,
        TIMES,
        OVER,
        PLUS_MINUS,
        RECIPROCAL,
        PERCENT,
        SQRT,
        MEM_CLEAR,
        MEM_SET,
        MEM_PLUS,
        MEM_MINUS,
        MEM_RECALL
    }

    /**
     * The internal state of the calculator.
     */
    private enum State {

        INPUT_OPERATED_VALUE,
        INPUT_OPERATOR,
        INPUT_OPERATING_VALUE,
        OUTPUT_RESULT
    }

    private State mState;
    private String mOperatedValue;
    private String mOperatingValue;
    private String mMemoryValue;
    private String mErrorMessage;
    private Operator mOperator;

    public Calculator() {
        performClear();
        performMemoryClear();
    }

    /**
     * Append a single digit to the calculation.
     *
     * @param digit Single integer digit between 0 ~ 9.
     */
    public void appendDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Invalid 'digit' value: " + digit);
        }

        mErrorMessage = null;
        if (mState == State.INPUT_OPERATED_VALUE
                && mOperatedValue.length() < MAX_INPUT_LENGTH) {
            if ("0".equals(mOperatedValue)) {
                mOperatedValue = String.valueOf(digit);
            } else {
                mOperatedValue += String.valueOf(digit);
            }
            setChanged();
        } else if (mState == State.INPUT_OPERATOR) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = String.valueOf(digit);
            setChanged();
        } else if (mState == State.INPUT_OPERATING_VALUE
                && mOperatingValue.length() < MAX_INPUT_LENGTH) {
            if ("0".equals(mOperatingValue)) {
                mOperatingValue = String.valueOf(digit);
            } else {
                mOperatingValue += String.valueOf(digit);
            }
            setChanged();
        } else if (mState == State.OUTPUT_RESULT) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = String.valueOf(digit);
            mOperatingValue = "0";
            setChanged();
        }

        notifyObservers();
    }

    /**
     * Append a dot to the calculation.
     */
    public void appendDot() {
        mErrorMessage = null;
        if (mState == State.INPUT_OPERATED_VALUE
                && !mOperatedValue.contains(".")) {
            mOperatedValue += ".";
            setChanged();
        } else if (mState == State.INPUT_OPERATOR) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = "0.";
            setChanged();
        } else if (mState == State.INPUT_OPERATING_VALUE
                && !mOperatingValue.contains(".")) {
            mOperatingValue += ".";
            setChanged();
        } else if (mState == State.OUTPUT_RESULT) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = "0.";
            setChanged();
        }

        notifyObservers();
    }

    /**
     * Input an operator to the calculation.
     *
     * @param operator Operator to perform.
     */
    public void performOperation(Operator operator) {
        mErrorMessage = null;
        if (operator == Operator.EQUAL) {
            performEqual();
        } else if (operator == Operator.BACKSPACE) {
            performBackspace();
        } else if (operator == Operator.CLEAR_ENTRY) {
            performClearEntry();
        } else if (operator == Operator.CLEAR) {
            performClear();
        } else if (operator == Operator.PLUS_MINUS
                || operator == Operator.RECIPROCAL
                || operator == Operator.SQRT) {
            performUniOperation(operator);
        } else if (operator == Operator.PERCENT) {
            performPercent();
        } else if (operator == Operator.MEM_SET
                || operator == Operator.MEM_PLUS
                || operator == Operator.MEM_MINUS) {
            performMemoryUpdate(operator);
        } else if (operator == Operator.MEM_RECALL) {
            performMemoryRecall();
        } else if (operator == Operator.MEM_CLEAR) {
            performMemoryClear();
        } else /* general operators */ {
            performGeneralOperation(operator);
        }

        notifyObservers();
    }

    /**
     * Get the display for current calculation.
     *
     * @return A string to display.
     */
    public String getDisplay() {
        if (mErrorMessage != null) {
            return mErrorMessage;
        }

        if (mState == State.INPUT_OPERATING_VALUE) {
            return mOperatingValue;
        }
        return mOperatedValue;
    }

    /**
     * Calculate result.
     */
    private void performEqual() {
        if (mState == State.INPUT_OPERATOR) {
            mOperatingValue = mOperatedValue;
        }
        mState = State.OUTPUT_RESULT;
        calculateResult();
        setChanged();
    }

    /**
     * Make actual calculation.
     */
    private void calculateResult() {
        double operatedValue = Double.parseDouble(mOperatedValue);
        double operatingValue = Double.parseDouble(mOperatingValue);
        Double result = null;

        if (mOperator == Operator.PLUS) {
            result = operatedValue + operatingValue;
        } else if (mOperator == Operator.MINUS) {
            result = operatedValue - operatingValue;
        } else if (mOperator == Operator.TIMES) {
            result = operatedValue * operatingValue;
        } else if (mOperator == Operator.OVER) {
            if (operatingValue == 0) {
                mErrorMessage = "Error";
                mOperatedValue = mOperatingValue = "0";
            } else {
                result = operatedValue / operatingValue;
            }
        }

        if (result != null) {
            mOperatedValue = formatDecimal(result);
        }
    }

    /**
     * Format decimal for better display.
     *
     * @param decimal Decimal number to format.
     * @return Formated string.
     */
    private String formatDecimal(double decimal) {
        DecimalFormat fmt = new DecimalFormat("#.########");
        String value = fmt.format(decimal);
        if (value.length() > 10) {
            fmt = new DecimalFormat("0.#####E0");
            value = fmt.format(decimal);
        }
        return value;
    }

    /**
     * Perform an uni-operation (i.e. plus-minus, reciprocal, sqrt) on current
     * input.
     *
     * @param op Operator to perform. Must be an uni-operation.
     */
    private void performUniOperation(Operator op) {
        if (!"0".equals(mOperatedValue)
                && (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE)) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = formatDecimal(
                    uniOperation(Double.parseDouble(mOperatedValue), op));
            setChanged();
        } else if (!"0".equals(mOperatingValue)
                && (mState == State.INPUT_OPERATOR
                || mState == State.INPUT_OPERATING_VALUE)) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = formatDecimal(
                    uniOperation(Double.parseDouble(mOperatingValue), op));
            setChanged();
        }
    }

    /**
     * Do an uni-operation (plus-minus, reciprocal or sqrt) on the input.
     *
     * @param input Input to calculate. Must not be 0.
     * @param op Operator to perform. Must be an uni-operation.
     * @return Calculation result.
     */
    private double uniOperation(double input, Operator op) {
        switch (op) {
            case PLUS_MINUS:
                return input * -1;
            case RECIPROCAL:
                return 1 / input;
            case SQRT:
                return Math.sqrt(input);
            default:
                throw new UnsupportedOperationException("Invalid operator: " + op);
        }
    }

    /**
     * Get the percentage of current input.
     */
    private void performPercent() {
        if (!"0".equals(mOperatedValue)
                && (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE)) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = formatDecimal(
                    Double.parseDouble(mOperatedValue) / 100);
            setChanged();
        } else if (!"0".equals(mOperatingValue)
                && (mState == State.INPUT_OPERATOR
                || mState == State.INPUT_OPERATING_VALUE)) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = formatDecimal(Double.parseDouble(mOperatedValue)
                    * Double.parseDouble(mOperatingValue) / 100);
            setChanged();
        }
    }

    /**
     * Delete the last digit in current input.
     */
    private void performBackspace() {
        if (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE) {
            mState = State.INPUT_OPERATED_VALUE;
            if (mOperatedValue.length() > 1) {
                mOperatedValue = mOperatedValue
                        .substring(0, mOperatedValue.length() - 1);
            } else {
                mOperatedValue = "0";
            }
        } else /* mState == State.INPUT_OPERATOR
         || mState == State.INPUT_OPERATING_VALUE */ {
            mState = State.INPUT_OPERATING_VALUE;
            if (mOperatingValue.length() > 1) {
                mOperatingValue = mOperatingValue
                        .substring(0, mOperatingValue.length() - 1);
            } else {
                mOperatingValue = "0";
            }
        }
        setChanged();
    }

    /**
     * Clear current input.
     */
    private void performClearEntry() {
        if (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = "0";
        } else /* mState == State.INPUT_OPERATOR
         || mState == State.INPUT_OPERATING_VALUE */ {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = "0";
        }
        setChanged();
    }

    /**
     * Clear the calculation.
     */
    private void performClear() {
        mErrorMessage = null;
        mState = State.OUTPUT_RESULT;
        mOperator = null;
        mOperatedValue = mOperatingValue = "0";
        setChanged();
    }

    /**
     * Perform a memory update operation (M+, M-, MS).
     *
     * @param op Operation to perform. Must be a memory operation.
     */
    private void performMemoryUpdate(Operator op) {
        if (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE) {
            memoryUpdate(Double.parseDouble(mOperatedValue), op);
        } else /* mState == State.INPUT_OPERATOR
         || mState == State.INPUT_OPERATING_VALUE */ {
            memoryUpdate(Double.parseDouble(mOperatingValue), op);
        }
        mState = State.OUTPUT_RESULT;
    }

    /**
     * Update memory by given input and operation.
     *
     * @param input Input to perform operation with.
     * @param op Operation to perform. Must be a memory operation.
     */
    private void memoryUpdate(double input, Operator op) {
        switch (op) {
            case MEM_SET:
                mMemoryValue = formatDecimal(input);
                break;
            case MEM_PLUS:
                mMemoryValue = formatDecimal(
                        Double.parseDouble(mMemoryValue) + input);
                break;
            case MEM_MINUS:
                mMemoryValue = formatDecimal(
                        Double.parseDouble(mMemoryValue) - input);
                break;
            default:
                throw new UnsupportedOperationException("Invalid operator: " + op);
        }
    }

    /**
     * Clear memory value.
     */
    private void performMemoryClear() {
        mMemoryValue = "0";
    }

    /**
     * Recall memory value.
     */
    private void performMemoryRecall() {
        if (mState == State.OUTPUT_RESULT
                || mState == State.INPUT_OPERATED_VALUE) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = mMemoryValue;
        } else /* mState == State.INPUT_OPERATOR
         || mState == State.INPUT_OPERATING_VALUE */ {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = mMemoryValue;
        }
        setChanged();
    }

    /**
     * Perform a general operation such as +, -, x, /, ..., etc.
     */
    private void performGeneralOperation(Operator op) {
        if (mState == State.INPUT_OPERATING_VALUE) {
            calculateResult();
            setChanged();
        }
        mState = State.INPUT_OPERATOR;
        mOperator = op;
    }
}
