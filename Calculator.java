
package calculator; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;


 public class Calculator extends JFrame implements ActionListener { 
     
        // Instances variables
        String display = "";  
        // Instance buttons
        JButton [] numButtons = new JButton[10];
        
        //Initializes each special button
        JButton buttonDot = new JButton("."); 
        JButton buttonPlus = new JButton("+"); 
        JButton buttonMinus = new JButton("-");
        JButton buttonMultiply = new JButton("*");  
        JButton buttonDivide = new JButton("/"); 
        JButton buttonEquals = new JButton("="); 
        JButton buttonClear = new JButton("C"); 
        
        // Adds the special buttons to an array to better implement
        JButton [] specialButtons = {buttonPlus, buttonMinus, buttonMultiply, buttonDivide,buttonEquals, buttonClear, buttonDot};
        
        // Sets decimal places to null if the drop down isn't selected
        Integer decimalPlaces = null;
     
        // Settings button is implemented on its own due to its functions
        JButton settings = new JButton("Settings");  
        // The space where the calculation result is stored
        JTextField calculation = new JTextField();  
        // Instance variables
        double firstNum, secNum, result, newResult;
        // Placeholder for operations
        String operation = "";
        private Object textField;
        
         // Stores all the panels
         private final JPanel [] panels;
        // Constructor 
         public Calculator()
         {
            //Sets the shape of the frame
            setSize(270,380);
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            add(mainPanel);
            JPanel textfieldPanel = new JPanel(null);
            calculation.setBounds(20,20,215,45);
            JPanel buttonPanel = new JPanel();
            GridLayout layout = new GridLayout(5,4);
            // Sets inner borders
            layout.setVgap(5);
            layout.setHgap(5);
            buttonPanel.setLayout(layout);
            textfieldPanel.add(calculation);
            
            // Adds the textfield and button panels to the master panel
            mainPanel.add(textfieldPanel);
            mainPanel.add(buttonPanel);
            
          
            setLocationRelativeTo(null); 
            
            // Sets the location and size of each button
            for (int i = 0; i < numButtons.length; i++){
                String val = Integer.toString(i);
                numButtons[i] = new JButton(val);
                numButtons[i].setBackground(Color.WHITE);  
                numButtons[i].setContentAreaFilled(false);
                numButtons[i].setOpaque(true);
                
                final int n = i;
                // Adds an action listener to set the textfield to the button selected
                numButtons[i].addActionListener(e -> calculation.setText(calculation.getText().concat(n+"")));
                
                // Adds the buttons to the panel
                buttonPanel.add(numButtons[i]);
            }
            
            
           //JButton [] specialButtons = {buttonPlus, buttonMinus, buttonMultiply, buttonDivide,buttonEquals, buttonClear, buttonDot};
           
           for(JButton button : specialButtons)
           {
        	   // Sets the color of each button
        	   button.setBackground(Color.LIGHT_GRAY);
        	   button.setContentAreaFilled(false);
        	   button.setOpaque(true);
        	   
        	   // Adds an action listener to each button
               
        	   button.addActionListener(this);
        	        	   
        	   // Adds each button to the frame
        	   buttonPanel.add(button); 
           }
           
            // Implements the settings button
            settings.setMinimumSize(new Dimension(100,40));
            settings.setPreferredSize(new Dimension(100,40));
            settings.setBackground(Color.WHITE);  
            settings.setContentAreaFilled(false);
            settings.setOpaque(true);
  
            // Adds an action listener to the settings button
            settings.addActionListener(this);
            
            // Adds settings button to the main panel
            mainPanel.add(settings); 
            
            
            panels = new JPanel[]{mainPanel, textfieldPanel, buttonPanel};
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true); 
            
        }
         
       
        // Method to add an action to each button
        @Override
        public void actionPerformed(ActionEvent e){
          
                 
           if 
              (e.getSource()==buttonDot) 
                calculation.setText(calculation.getText().concat("."));
            // Clears the textfield if the clear button is selected
            else if
                (e.getSource()==buttonClear) 
                calculation.setText("");
            /** 
             * If an operator is selected, it sets the number on the textfield
             * as the first number, sets the operation according to the button,
             * and clears the textfield screen 
             */
            else if 
                (e.getSource()==buttonPlus) {
                firstNum = Double.parseDouble(calculation.getText());
                operation = "addition";
                calculation.setText("");
            }  
            
            else if  
                (e.getSource()==buttonMinus) {
                firstNum = Double.parseDouble(calculation.getText());
                operation = "subtract";
                calculation.setText(""); 
            } 
            
            else if  
                (e.getSource()==buttonMultiply) {
                firstNum = Double.parseDouble(calculation.getText());
                operation = "multiply"; 
                calculation.setText(""); 
            } 
             
            else if  
                (e.getSource()==buttonDivide) {
                firstNum = Double.parseDouble(calculation.getText());
                operation = "divide"; 
                calculation.setText(""); 
            } 
            /**
             * If the equal button is selected, it sets the second number entered
             * as the second number
             */
            else if 
                (e.getSource()==buttonEquals) {
                secNum = Double.parseDouble(calculation.getText());
              
             /**
             * Switch method then takes the operation referenced in each button
             * method and calculates the result as a double
             */
                switch (operation) {
                    case "addition":
                        result = firstNum + secNum;
                        break;
                    case "subtract":
                        result = firstNum - secNum;
                        break;
                    case "multiply":
                        result = firstNum * secNum;
                        break;
                    case "divide":
                        result = firstNum / secNum;
                        break; 
                }
                 
                // Displays the result to the textfield screen
                String resultAsString = result+"";
                if(decimalPlaces != null)
                	resultAsString = String.format("%."+decimalPlaces+"f", result);
                
                calculation.setText(resultAsString);
            }
            
            else if 
                (e.getSource()==settings){
                Settings settingsFrame = new Settings(this);
                
            }
               
        }
        
        // Uses the  methods in the settings button to alter the calculator
        public void changeDisplaySettings(Color bgColor, Color fgColor, Color keyColor, String fontType, Integer decimalPlaces) 
    	{      
                // Sets the background color of each panel so they are all the same
    		for(JPanel panel : panels)
    		{
    		   panel.setBackground(bgColor);
   
    		} 
                
            // Changes the buttons depending on which button is selected
            for (JButton numButton : numButtons) {
                numButton.setForeground(keyColor);
                numButton.setBackground(fgColor);
                numButton.setContentAreaFilled(false);
                numButton.setOpaque(true);
                numButton.setFont(new Font(fontType, Font.PLAIN, 16));
            } 
                
            for (JButton specialButton : specialButtons) {
                specialButton.setForeground(keyColor);
                specialButton.setBackground(fgColor);
                specialButton.setContentAreaFilled(false);
                specialButton.setOpaque(true);
                specialButton.setFont(new Font(fontType, Font.PLAIN, 16));
            }
            
                // Changes the settings button separately
                settings.setForeground(keyColor);
                settings.setBackground(fgColor);
                settings.setContentAreaFilled(false);
                settings.setOpaque(true);
                settings.setFont(new Font(fontType, Font.PLAIN, 16));
                
                // Sets the number of decimal places depending on which is selected
                this.decimalPlaces = decimalPlaces;
    		
    	}
    
        
  
    
    public static void main(String[] args) {
        
      // Calls a new instance of the calculator class  
      Calculator newCalc = new Calculator();


    }

}
 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */

// The frame that is enabled when the settings button is selected
 class Settings extends JFrame 
 {
     
     
     JComboBox backgroundBox, foregroundBox, keypadBox, fontBox, decBox;
     String[] colorStrings = {"red", "blue", "black"};
     Color[] colors = {Color.RED, Color.BLUE, Color.BLACK};
     Integer [] decimals = {1,2,3,4,5,6,7};
     String fontTypes[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
     String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
     
     
     JLabel background, foreground, keypad, decimal, font;
   
     
    public Settings(Calculator calculator){
        
        
     setSize(290,380);
     setLayout(null);  
        
 
       
        backgroundBox = new JComboBox(colorStrings);
        backgroundBox.setBounds(120, 10, 90, 30);  
        foregroundBox = new JComboBox(colorStrings);
        foregroundBox.setBounds(120, 70, 90, 30); 
        keypadBox = new JComboBox(colorStrings);
        keypadBox.setBounds(120,120,90,30);
        decBox = new JComboBox(decimals);
        decBox.setBounds(120,170,130,30);
        fontBox = new JComboBox(fontTypes);
        fontBox.setBounds(120,220, 130, 30);
        
        
    
        background = new JLabel("Background");
        background.setBounds(10, 10, 90, 30);
        foreground = new JLabel("Foreground");
        foreground.setBounds(10, 70, 90, 30);
        keypad = new JLabel("Keypad");
        keypad.setBounds(10,120,90,30);
        decimal = new JLabel("Decimals");
        decimal.setBounds(10,170,90,30);
        font = new JLabel("Font Type");
        font.setBounds(10, 220, 90, 30);
        

        
        JButton okButton = new JButton("Ok");
        okButton.setBounds(90, 280, 90, 30);
         
        add(background);
        add(backgroundBox);
        add(foreground);
        add(foregroundBox);
        add(keypadBox);
        add(keypad);
        add(font);
        add(fontBox);
        add(decimal);
        add(decBox);
        add(okButton);
       
        JFrame thisFrame = this;
        okButton.addActionListener((ActionEvent arg0) -> {
            Color bgColor = colors[backgroundBox.getSelectedIndex()];
            Color fgColor = colors[foregroundBox.getSelectedIndex()];
            Color keyColor = colors[keypadBox.getSelectedIndex()];
            String fontType = fontFamilyNames[fontBox.getSelectedIndex()];    
            int decimalPlaces = decimals[decBox.getSelectedIndex()];    
            
            calculator.changeDisplaySettings(bgColor, fgColor, keyColor, fontType, decimalPlaces);
            thisFrame.dispose();
        });
        
       
        setLocationRelativeTo(null); 
        

        setVisible(true);
       
    }
    
 }