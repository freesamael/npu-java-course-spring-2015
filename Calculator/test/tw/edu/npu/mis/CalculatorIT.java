/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samael Wang <freesamael@gmail.com>
 */
public class CalculatorIT {

    private Calculator mSUT;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mSUT = new Calculator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIntegerAdd() {
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.PLUS);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("5", mSUT.getDisplay());
    }

    @Test
    public void testFloatingAdd() {
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.PLUS);
        mSUT.appendDigit(3);
        mSUT.appendDot();
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("5.5", mSUT.getDisplay());
    }

    @Test
    public void testFloatingAddVerifyAllDisplays() {
        mSUT.appendDigit(3);
        assertEquals("3", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("36", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("36.", mSUT.getDisplay());
        mSUT.appendDigit(8);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("4", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("43", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("43.", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("43.2", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("43.23", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("43.234", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("43.2345", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("43.23456", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("80.03456", mSUT.getDisplay());
    }

    @Test
    public void testIntegerSub() {
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1", mSUT.getDisplay());
    }

    @Test
    public void testIntergerSubNegResult() {
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-1", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSub() {
        mSUT.appendDigit(3);
        mSUT.appendDot();
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1.1", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSubNegResult() {
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(3);
        mSUT.appendDot();
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-1.1", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSubVerifyAllDisplays() {
        mSUT.appendDigit(3);
        assertEquals("3", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("36", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("36.", mSUT.getDisplay());
        mSUT.appendDigit(8);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.MINUS);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("4", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("43", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("43.", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("43.2", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("43.23", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("43.234", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("43.2345", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("43.23456", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-6.43456", mSUT.getDisplay());
    }

    @Test
    public void testIntegerMul() {
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.TIMES);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("6", mSUT.getDisplay());
    }

    @Test
    public void testFloatingMul() {
        mSUT.appendDigit(3);
        mSUT.appendDot();
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.TIMES);
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("7.26", mSUT.getDisplay());
    }

    @Test
    public void testFloatingMulVerifyAllDisplays() {
        mSUT.appendDigit(3);
        assertEquals("3", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("36", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("36.", mSUT.getDisplay());
        mSUT.appendDigit(8);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.TIMES);
        assertEquals("36.8", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("4", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("43", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("43.", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("43.2", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1589.76", mSUT.getDisplay());
    }

    @Test
    public void testIntegerDiv() {
        mSUT.appendDigit(9);
        mSUT.performOperation(Calculator.Operator.OVER);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("3", mSUT.getDisplay());
    }
    
    @Test
    public void testFloatingDiv() {
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.OVER);
        mSUT.appendDigit(1);
        mSUT.appendDigit(6);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("0.125", mSUT.getDisplay());
    }
    
    @Test
    public void testFloatingDivVerifyAllDisplays() {
        mSUT.appendDigit(2);
        assertEquals("2", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.OVER);
        assertEquals("2", mSUT.getDisplay());
        mSUT.appendDigit(1);
        assertEquals("1", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("16", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("0.125", mSUT.getDisplay());
    }
}
