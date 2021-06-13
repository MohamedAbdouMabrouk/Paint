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
public class Context {
    
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeStrategy4(int num1, int num2, int num3, int num4){
      return strategy.doOperation4(num1, num2, num3, num4);
   }
   
   public int executeStrategy3(int num1, int num2, int num3){
      return strategy.doOperation3(num1, num2, num3);
   }
}
