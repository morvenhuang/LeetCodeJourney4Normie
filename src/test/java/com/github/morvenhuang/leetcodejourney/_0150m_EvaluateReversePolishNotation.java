package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won’t be any divide by zero operation.
 * <p>
 * <p>
 * Example 1:
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * <p>
 * Example 2:
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * <p>
 * Example 3:
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class _0150m_EvaluateReversePolishNotation {

    @Test
    void test01() {
        String[] a = {"2", "1", "+", "3", "*"};
        int r = slt(a);
        Assertions.assertEquals(9, r);
    }

    @Test
    void test02() {
        String[] a = {"4", "13", "5", "/", "+"};
        int r = slt(a);
        Assertions.assertEquals(6, r);
    }

    @Test
    void test03() {
        String[] a = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int r = slt(a);
        Assertions.assertEquals(22, r);
    }

    /**
     * 这个没有难度，上过算法和数据结构的都知道"逆波兰栈"
     * 我们平时写a+b，这是中缀表达式，写成逆波兰式（后缀表达式）就是：ab+
     */
    int slt(String[] a) {
        Stack<Integer> stack = new Stack<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        for (String s : a) {
            if (operators.contains(s)) {
                int eval = eval(stack.pop(), stack.pop(), s);
                stack.push(eval);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    int eval(Integer a, Integer b, String operator) {
        if (operator.equals("+")) {
            return a + b;
        }
        if (operator.equals("-")) {
            return b - a;
        }
        if (operator.equals("*")) {
            return a * b;
        }
        if (operator.equals("/")) {
            return b / a;
        }
        throw new RuntimeException("Oops");
    }
}
