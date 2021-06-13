/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

/**
 *
 * @author mamm_
 */
public class OperationMin implements Strategy {

    @Override
    public int doOperation4(int num1, int num2, int num3, int num4) {
        return Integer.min(num1, Integer.min(num2, Integer.min(num3, num4)));
    }

    @Override
    public int doOperation3(int num1, int num2, int num3) {
        return Integer.min(num1, Integer.min(num2, num3));
    }
}
