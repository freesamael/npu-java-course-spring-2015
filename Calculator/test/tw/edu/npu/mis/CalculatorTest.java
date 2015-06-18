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
public class CalculatorTest {

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
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.PLUS);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("5", mSUT.getDisplay());
    }

    @Test
    public void testAccuIntergerAdd() {
        testIntegerAdd();
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("8", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("11", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("14", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("17", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("20", mSUT.getDisplay());
    }

    @Test
    public void testFloatingAdd() {
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1", mSUT.getDisplay());
    }

    @Test
    public void testAccuIntegerSub() {
        testIntegerSub();
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-1", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-3", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-5", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-7", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-9", mSUT.getDisplay());
    }

    @Test
    public void testIntergerSubNegResult() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-1", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSub() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("2.5", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSubPrecision() {
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        mSUT.appendDot();
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.MINUS);
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-2.5", mSUT.getDisplay());
    }

    @Test
    public void testFloatingSubPrecisionNegResult() {
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.TIMES);
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("6", mSUT.getDisplay());
    }

    @Test
    public void testAccuIntegerMul() {
        testIntegerMul();
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("18", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("54", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("162", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("486", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1458", mSUT.getDisplay());
    }

    @Test
    public void testFloatingMul() {
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
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
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        mSUT.appendDigit(4);
        mSUT.performOperation(Calculator.Operator.OVER);
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("32", mSUT.getDisplay());
    }

    @Test
    public void testAccuIntegerDiv() {
        testIntegerDiv();
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("16", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("8", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("4", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("2", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("1", mSUT.getDisplay());
    }

    @Test
    public void testFloatingDiv() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        mSUT.performOperation(Calculator.Operator.OVER);
        mSUT.appendDigit(1);
        mSUT.appendDigit(6);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("0.125", mSUT.getDisplay());
    }

    @Test
    public void testFloatingDivVerifyAllDisplays() {
        assertEquals("0", mSUT.getDisplay());
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

    @Test
    public void testPlusMinus() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(3);
        mSUT.performOperation(Calculator.Operator.TIMES);
        mSUT.appendDigit(6);
        mSUT.performOperation(Calculator.Operator.PLUS_MINUS);
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-18", mSUT.getDisplay());
    }

    @Test
    public void testBasicArithmetic() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(1);
        assertEquals("1", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("16", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("16", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("5", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.MINUS);
        assertEquals("21", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("2", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.TIMES);
        assertEquals("19", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("3", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.OVER);
        assertEquals("57", mSUT.getDisplay());
        mSUT.appendDigit(4);
        assertEquals("4", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS_MINUS);
        assertEquals("-4", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("-14.25", mSUT.getDisplay());
    }

    @Test
    public void testBackspace() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(3);
        assertEquals("3", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.BACKSPACE);
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("6", mSUT.getDisplay());
        mSUT.appendDigit(8);
        assertEquals("68", mSUT.getDisplay());
        mSUT.appendDigit(7);
        assertEquals("687", mSUT.getDisplay());
        mSUT.appendDigit(9);
        assertEquals("6879", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.BACKSPACE);
        assertEquals("687", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.BACKSPACE);
        assertEquals("68", mSUT.getDisplay());
        mSUT.appendDigit(9);
        assertEquals("689", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("689", mSUT.getDisplay());
        mSUT.appendDigit(9);
        assertEquals("9", mSUT.getDisplay());
        mSUT.appendDot();
        assertEquals("9.", mSUT.getDisplay());
        mSUT.appendDigit(6);
        assertEquals("9.6", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.BACKSPACE);
        assertEquals("9.", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("9.2", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("698.2", mSUT.getDisplay());
    }

    @Test
    public void testClearEntry() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("5", mSUT.getDisplay());
        mSUT.appendDigit(0);
        assertEquals("50", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("50", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("5", mSUT.getDisplay());
        mSUT.appendDigit(0);
        assertEquals("50", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("2", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("25", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("75", mSUT.getDisplay());
    }

    @Test
    public void testClear() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("5", mSUT.getDisplay());
        mSUT.appendDigit(0);
        assertEquals("50", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("50", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("5", mSUT.getDisplay());
        mSUT.appendDigit(0);
        assertEquals("50", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.CLEAR);
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(2);
        assertEquals("2", mSUT.getDisplay());
        mSUT.appendDigit(5);
        assertEquals("25", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("25", mSUT.getDisplay());
    }

    @Test
    public void testReciprocal() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.RECIPROCAL);
        assertEquals("0.2", mSUT.getDisplay());
    }

    @Test
    public void testReciprocalAdd() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.RECIPROCAL);
        mSUT.performOperation(Calculator.Operator.PLUS);
        assertEquals("0.2", mSUT.getDisplay());
        mSUT.appendDigit(8);
        mSUT.performOperation(Calculator.Operator.RECIPROCAL);
        assertEquals("0.125", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("0.325", mSUT.getDisplay());
    }

    @Test
    public void testSqrt() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(9);
        mSUT.performOperation(Calculator.Operator.SQRT);
        assertEquals("3", mSUT.getDisplay());
    }

    @Test
    public void testSqrtAdd() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(9);
        mSUT.performOperation(Calculator.Operator.SQRT);
        assertEquals("3", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.PLUS);
        mSUT.appendDigit(1);
        mSUT.appendDigit(6);
        mSUT.performOperation(Calculator.Operator.SQRT);
        assertEquals("4", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("7", mSUT.getDisplay());
    }

    @Test
    public void testPercent() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        mSUT.appendDigit(0);
        mSUT.performOperation(Calculator.Operator.PERCENT);
        assertEquals("0.6", mSUT.getDisplay());
    }

    @Test
    public void testPercentAdd() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        mSUT.appendDigit(0);
        mSUT.performOperation(Calculator.Operator.PLUS);
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.PERCENT);
        assertEquals("3", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.EQUAL);
        assertEquals("63", mSUT.getDisplay());
    }

    @Test
    public void testMemSetClearRecall() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        mSUT.appendDigit(5);
        mSUT.performOperation(Calculator.Operator.MEM_SET);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("65", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.MEM_CLEAR);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("0", mSUT.getDisplay());
    }

    @Test
    public void testMemSetClearRecallAfterArithmetic() {
        testBasicArithmetic();
        mSUT.performOperation(Calculator.Operator.MEM_SET);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("-14.25", mSUT.getDisplay());
        mSUT.performOperation(Calculator.Operator.MEM_CLEAR);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("0", mSUT.getDisplay());
    }

    @Test
    public void testMemPlusMinus() {
        assertEquals("0", mSUT.getDisplay());
        mSUT.appendDigit(6);
        mSUT.performOperation(Calculator.Operator.MEM_PLUS);
        mSUT.appendDigit(4);
        mSUT.performOperation(Calculator.Operator.MEM_PLUS);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("10", mSUT.getDisplay());
        mSUT.appendDigit(8);
        mSUT.performOperation(Calculator.Operator.MEM_MINUS);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("2", mSUT.getDisplay());
    }

    @Test
    public void testMemPlusMinusAfterArithmetic() {
        testIntegerAdd();
        mSUT.performOperation(Calculator.Operator.MEM_PLUS);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        testIntegerSub();
        mSUT.performOperation(Calculator.Operator.MEM_PLUS);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        testIntegerMul();
        mSUT.performOperation(Calculator.Operator.MEM_MINUS);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        testIntegerDiv();
        mSUT.performOperation(Calculator.Operator.MEM_MINUS);
        mSUT.performOperation(Calculator.Operator.CLEAR_ENTRY);
        mSUT.performOperation(Calculator.Operator.MEM_RECALL);
        assertEquals("-32", mSUT.getDisplay());
    }
}
