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

    /**
     * The available operators of the calculator.
     */
    public enum Operator {

        CLEAR, // C
        CLEAR_ENTRY, // CE
        BACKSPACE, // ⌫
        EQUAL, // =
        PLUS, // +
        MINUS, // -
        TIMES, // ×
        OVER, // ⁄
        PLUS_MINUS, // ±
        RECIPROCAL, // 1/x
        PERCENT, // %
        SQRT, // √
        MEM_CLEAR, // MC
        MEM_SET, // MS
        MEM_PLUS, // M+
        MEM_MINUS, // M-
        MEM_RECALL   // MR
    }

    /**
     * The internal state of the calculator.
     */
    public enum State {
        INPUT_OPERATED_VALUE,
        INPUT_OPERATOR,
        INPUT_OPERATING_VALUE,
        OUTPUT_RESULT
    }

    public static final int MAX_INPUT_LENGTH = 10;
    private State mState;
    private String mOperatedValue;
    private String mOperatingValue;
    private String mMemoryValue;
    private String mErrorMessage;
    private Operator mOperator;

    public Calculator() {
        clear();
        memoryClear();
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
        } else if (mState == State.INPUT_OPERATOR) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = String.valueOf(digit);
        } else if (mState == State.INPUT_OPERATING_VALUE
                && mOperatingValue.length() < MAX_INPUT_LENGTH) {
            if ("0".equals(mOperatingValue)) {
                mOperatingValue = String.valueOf(digit);
            } else {
                mOperatingValue += String.valueOf(digit);
            }
        } else if (mState == State.OUTPUT_RESULT) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = String.valueOf(digit);
            mOperatingValue = "0";
        }
        
        setChanged();
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
        } else if (mState == State.INPUT_OPERATOR) {
            mState = State.INPUT_OPERATING_VALUE;
            mOperatingValue = "0.";
        } else if (mState == State.INPUT_OPERATING_VALUE
                && !mOperatingValue.contains(".")) {
            mOperatingValue += ".";
        } else if (mState == State.OUTPUT_RESULT) {
            mState = State.INPUT_OPERATED_VALUE;
            mOperatedValue = "0.";
        }
        
        setChanged();
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
            if (mState == State.INPUT_OPERATOR) {
                mOperatingValue = mOperatedValue;
            }
            mState = State.OUTPUT_RESULT;
            calculateResult();
        } else if (operator == Operator.PLUS_MINUS) {
            if (mState == State.INPUT_OPERATED_VALUE) {
                mOperatedValue = String.valueOf(
                        Double.parseDouble(mOperatedValue) * -1);
            } else if (mState == State.INPUT_OPERATOR) {
                mState = State.INPUT_OPERATING_VALUE;
                mOperatingValue = String.valueOf(
                        Double.parseDouble(mOperatedValue) * -1);
            } else if (mState == State.INPUT_OPERATING_VALUE) {
                mOperatingValue = String.valueOf(
                        Double.parseDouble(mOperatingValue) * -1);
            } else /* OUTPUT_RESULT */ {
                mState = State.INPUT_OPERATED_VALUE;
                mOperatedValue = String.valueOf(
                        Double.parseDouble(mOperatedValue) * -1);
            }
        } else {
            if (mState == State.INPUT_OPERATING_VALUE) {
                calculateResult();
            }
            mState = State.INPUT_OPERATOR;
            mOperator = operator;
        }
        
        setChanged();
        notifyObservers();
    }

    private void calculateResult() {
        double operatedValue = Double.parseDouble(mOperatedValue);
        double operatingValue = Double.parseDouble(mOperatingValue);
        Double result = null;
        
        switch (mOperator) {
            case PLUS:
                result = operatedValue + operatingValue;
                break;
            case MINUS:
                result = operatedValue - operatingValue;
                break;
            case TIMES:
                result = operatedValue * operatingValue;
                break;
            case OVER:
                if (operatingValue == 0) {
                    mErrorMessage = "Error - divided by zero.";
                } else { 
                    result = operatedValue / operatingValue;
                }
                break;
        }
        
        if (result != null) {
            DecimalFormat fmt = new DecimalFormat("#.########");
            mOperatedValue = fmt.format(result);
        }
    }

    /**
     * Get the display for current calculation.
     *
     * @return A string to display.
     */
    public String getDisplay() {
        if (mErrorMessage != null)
            return mErrorMessage;
        
        if (mState == State.INPUT_OPERATING_VALUE) {
            return mOperatingValue;
        }
        return mOperatedValue;
    }

    /**
     * Clear the calculation.
     */
    private void clear() {
        mState = State.OUTPUT_RESULT;
        mOperator = null;
        mOperatedValue = "0";
        mOperatingValue = "0";
    }

    /**
     * Clear the memory value.
     */
    private void memoryClear() {
        mMemoryValue = "0";
    }
}
