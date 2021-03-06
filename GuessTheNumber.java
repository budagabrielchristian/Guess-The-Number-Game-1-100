import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuessTheNumber implements ActionListener {
   LinkedList<Integer> numbersTried = new LinkedList<>();
   JFrame frame;
   JButton giveUp, guess, playAgain;
   Random random = new Random();
   JLabel label, numbersTriedText;
   JTextField numberField;
   int randInt = random.nextInt(100) + 1, guesses = 0;
   GuessTheNumber() {
      label = new JLabel();
      label.setFont(new Font("Courier New", Font.PLAIN, 35));

      numbersTriedText = new JLabel();
      numbersTriedText.setFont(new Font("Courier New", Font.PLAIN, 20));

      numberField = new JTextField();
      numberField.setPreferredSize(new Dimension(800, 100));
      numberField.setText("Input a number [1-100], then guess!");
      numberField.setFont(new Font("Courier New", Font.PLAIN, 35));

      guess = new JButton("Guess!");
      guess.addActionListener(this);
      guess.setFont(new Font("Courier New", Font.PLAIN, 35));
      guess.setFocusable(false);

      playAgain = new JButton("Play Again!");
      playAgain.addActionListener(this);
      playAgain.setFont(new Font("Courier New", Font.PLAIN, 35));
      playAgain.setFocusable(false);
      playAgain.setVisible(false);

      giveUp = new JButton("Give Up!");
      giveUp.addActionListener(this);
      giveUp.setFont(new Font("Courier New", Font.PLAIN, 35));
      giveUp.setFocusable(false);

      frame = new JFrame();
      frame.setSize(1000, 1000);
      frame.setResizable(false);
      frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
      frame.setTitle("Guess the number! [1-100]");

      frame.add(numberField);
      frame.add(guess);
      frame.add(playAgain);
      frame.add(giveUp);
      frame.add(label);
      frame.add(numbersTriedText);
      frame.setVisible(true);
   }

   @Override public void actionPerformed(ActionEvent ae) {
      if (numberField.getText().isBlank()) {
         label.setText("Please input a number.");
      }
      try {
         int inputtedNumber = Integer.parseInt(numberField.getText());
         if (inputtedNumber <= 0 || inputtedNumber > 100) {
            label.setText("Input Numbers 1-100 only.");
            return;
         }
         if (ae.getSource() == guess) {
            if (inputtedNumber == randInt) {
               guesses++;
               numbersTried.add(inputtedNumber);
               label.setText("You win!! | Guesses Took: " + guesses);
               numbersTriedText.setText("Numbers Tried: " + numbersTried);
               guesses = 0;
               giveUp.setEnabled(false);
               playAgain.setVisible(true);
               guess.setEnabled(false);
            } else if (inputtedNumber > randInt) {
               label.setText("Too high!");
               numbersTried.add(inputtedNumber);
               guesses++;
            } else {
               label.setText("Too low!");
               guesses++;
               numbersTried.add(inputtedNumber);
            }
         }
      } catch (NumberFormatException e) {
         label.setText("Please input a number, not a String.");
      }
      if (ae.getSource() == giveUp) {
         label.setText("The Random Number Was: " + randInt +
                       "| Amount of tries: " + guesses);
         numberField.setText("");
         guesses = 0;
         guess.setEnabled(false);
         playAgain.setVisible(true);
         giveUp.setEnabled(false);
      }
      if (ae.getSource() == playAgain) {
         randInt = random.nextInt(100) + 1;
         guess.setEnabled(true);
         giveUp.setEnabled(true);
         guesses = 0;
         numbersTriedText.setText("");
         numbersTried.clear();
         label.setText("");
         playAgain.setVisible(false);
         numberField.setText("");
      }
   }
}
