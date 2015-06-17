/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Samael Wang <freesamael@gmail.com>
 */
public class CalculatorController implements Initializable, Observer {
    
    private Calculator mCalculator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mCalculator = new Calculator();
        mCalculator.addObserver(this);
        display.setText(mCalculator.getDisplay());
    }

    public void handleNumber0(ActionEvent event) {
        mCalculator.appendDigit(0);
    }

    public void handleNumber1(ActionEvent event) {
        mCalculator.appendDigit(1);
    }

    public void handleNumber2(ActionEvent event) {
        mCalculator.appendDigit(2);
    }

    public void handleNumber3(ActionEvent event) {
        mCalculator.appendDigit(3);
    }

    public void handleNumber4(ActionEvent event) {
        mCalculator.appendDigit(4);
    }

    public void handleNumber5(ActionEvent event) {
        mCalculator.appendDigit(5);
    }

    public void handleNumber6(ActionEvent event) {
        mCalculator.appendDigit(6);
    }

    public void handleNumber7(ActionEvent event) {
        mCalculator.appendDigit(7);
    }

    public void handleNumber8(ActionEvent event) {
        mCalculator.appendDigit(8);
    }

    public void handleNumber9(ActionEvent event) {
        mCalculator.appendDigit(9);
    }

    public void handleDot(ActionEvent event) {
        mCalculator.appendDot();
    }

    public void handlePlusMinus(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.PLUS_MINUS);
    }

    public void handlePlus(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.PLUS);
    }

    public void handleMinus(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MINUS);
    }

    public void handleTimes(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.TIMES);
    }

    public void handleOver(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.OVER);
    }

    public void handleEqual(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.EQUAL);
    }

    public void handleBackspace(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.BACKSPACE);
    }

    public void handleClear(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.CLEAR);
    }

    public void handleClearEntry(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.CLEAR_ENTRY);
    }

    public void handleReciprocal(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.RECIPROCAL);
    }

    public void handlePercent(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.PERCENT);
    }

    public void handleSqrt(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.SQRT);
    }

    public void handleMemSet(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MEM_SET);
    }

    public void handleMemClear(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MEM_CLEAR);
    }

    public void handleMemPlus(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MEM_PLUS);
    }

    public void handleMemMinus(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MEM_MINUS);
    }

    public void handleMemRecall(ActionEvent event) {
        mCalculator.performOperation(Calculator.Operator.MEM_RECALL);
    }

    @Override
    public void update(Observable o, Object arg) {
        display.setText(mCalculator.getDisplay());
    }
    
    @FXML
    private Label display;
}
