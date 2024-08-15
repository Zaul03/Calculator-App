package Calculator.UI;

import Calculator.Calculator;

import javax.swing.*;
import java.awt.*;



public class UIBuilder {


    public static void frameBuilder(Calculator c,
                                    Dimension WINDOW_DIMENSION,
                                    Color BACKGRAOUND_COLOR,
                                    Image ICON,
                                    int HGAP,
                                    int VGAP){


        c.setPreferredSize(WINDOW_DIMENSION);
        c.setResizable(false);
        c.setLayout(new GridLayout(2, 1, HGAP, VGAP));
        c.setTitle("Calculator");
        c.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        c.setIconImage(ICON);
        c.setBackground(BACKGRAOUND_COLOR);
        c.addKeyListener(c);
    }
    
    public static void paneBuilder(JPanel display,
                                    JPanel buttons,
                                    Dimension DIS_DIM,
                                    Dimension BUTTONS_PANEL_DIM,
                                    Color displBackColor,
                                    Color BUTTONS_PANEL_BACKGROUND_COLOR){

        //////////////display panel////////////////////////
        display.setPreferredSize(DIS_DIM);
        display.setBackground(displBackColor);
        display.setVisible(true);

        /////////////buttons panel////////////////////////
        buttons.setPreferredSize(BUTTONS_PANEL_DIM);
        buttons.setBackground(BUTTONS_PANEL_BACKGROUND_COLOR);
        buttons.setVisible(true);
    }
    
    public static void displayBuilder(JTextField equationTxt,
                                      JTextField equationRes,
                                      Font EQUATION_TXT_FONT,
                                      Font EQUATION_RES_FONT,
                                      Color BACKGROUND_COLOR,
                                      Color EQUATION_TXT_COLOR)
    {
        equationTxt.setEditable(false);
        equationTxt.setFocusable(false);
        equationTxt.setHorizontalAlignment(JTextField.RIGHT);
        equationTxt.setFont(EQUATION_TXT_FONT);
        equationTxt.setBackground(BACKGROUND_COLOR);
        equationTxt.setBorder(null);
        equationTxt.setForeground(EQUATION_TXT_COLOR);

        equationRes.setEditable(false);
        equationRes.setFocusable(false);
        equationRes.setHorizontalAlignment(JTextField.RIGHT);
        equationRes.setFont(EQUATION_RES_FONT);
        equationRes.setBackground(Color.white);
        equationRes.setForeground(Color.BLACK);
        equationRes.setBorder(null);
    }

    public static void buttonsBuilder(JPanel buttonPanel,
                                      Calculator actionListener,
                                      JButton[] buttons,
                                      String[] BUTTONS_TEXT,
                                      Font BUTTONS_FONT,
                                      Color BUTTONS_BACKGROUND_COLOR)
    {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(BUTTONS_TEXT[i]);
            buttons[i].setFont(BUTTONS_FONT);
            buttons[i].setFocusable(false);
            buttons[i].setBackground(BUTTONS_BACKGROUND_COLOR);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(actionListener);
            buttons[i].setActionCommand(BUTTONS_TEXT[i]);
            buttonPanel.add(buttons[i]);}
    }
}
