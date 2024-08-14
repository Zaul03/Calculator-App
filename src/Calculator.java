import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.math.*;

public class Calculator extends JFrame implements ActionListener, KeyListener {

    ///////////////variables and elements//////////
    protected JTextField equationTxt, equationRes;
    protected JPanel displayPanel, buttonPanel;
    protected JButton[] buttons = new JButton[24];


    ////////////Frame icon/////////////////////////
    Image imageIcon = getToolkit().getImage("out/production/Calculator/simple-calculator.png");


    Calculator() {
        super();


        this.setPreferredSize(new Dimension(350, 450));
        this.setResizable(false);
        this.setLayout(new GridLayout(2, 1, 5, 5));
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(imageIcon);
        this.setBackground(Color.white);
        this.addKeyListener(this);


        equationTxt = new JTextField(null);
        equationTxt.setEditable(false);
        equationTxt.setFocusable(false);
        equationTxt.setHorizontalAlignment(JTextField.RIGHT);
        equationTxt.setFont(new Font("Arial", Font.PLAIN, 21));
        equationTxt.setBackground(Color.white);
        equationTxt.setBorder(null);
        equationTxt.setForeground(Color.gray);

        equationRes = new JTextField("0");
        equationRes.setEditable(false);
        equationRes.setFocusable(false);
        equationRes.setHorizontalAlignment(JTextField.RIGHT);
        equationRes.setFont(new Font("Arial", Font.BOLD, 25));
        equationRes.setBackground(Color.white);
        equationRes.setForeground(Color.BLACK);
        equationRes.setBorder(null);


        //////////////display panel////////////////////////
        displayPanel = new JPanel(new GridLayout(2, 1));
        displayPanel.setPreferredSize(new Dimension(340, 50));
        displayPanel.setMinimumSize(new Dimension(200, 30));
        displayPanel.setBackground(Color.LIGHT_GRAY);
        displayPanel.setVisible(true);
        displayPanel.add(equationTxt);
        displayPanel.add(equationRes);
        //////////////////////////////////////////////////


        /////////////buttons panel////////////////////////
        buttonPanel = new JPanel(new GridLayout(6, 4, 3, 3));
        buttonPanel.setPreferredSize(new Dimension(350, 550));
        buttonPanel.setMinimumSize(new Dimension(200, 200));
        buttonPanel.setBackground(new Color(250, 250, 245));
        buttonPanel.setVisible(true);
        //////////////////////////////////////////////////


        //////////buttons text with buttons init//////////
        String[] buttonsText = {
            "π", "e", "C", "DEL",
            "1/x", "(", ")", "x",
            "7", "8", "9", "/",
            "4", "5", "6", "+",
            "1", "2", "3", "-",
            ".", "0", " ", "="
        };


        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonsText[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 23));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(144, 144, 150));
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setActionCommand(buttonsText[i]);
            buttonPanel.add(buttons[i]);
        }
///////////////////////////////////////////////////////////


        this.add(displayPanel);
        this.add(buttonPanel);
        this.setVisible(true);
        this.pack();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        String crtTxt = equationTxt.getText();
        String resTxt = equationRes.getText();
        boolean duplicate = crtTxt.endsWith(cmd);
        boolean consecSymbols=crtTxt.endsWith("+")||
                                  crtTxt.endsWith("-")||
                                  crtTxt.endsWith("=")||
                                  crtTxt.endsWith("x")||
                                  crtTxt.endsWith("/")||
                                  crtTxt.endsWith(".")||
                                  crtTxt.endsWith("(");
        boolean lastIsNr=false;
        for(int i=0;i<10;i++)
            lastIsNr=crtTxt.endsWith(String.valueOf((char)('0'+i)))||lastIsNr;
        lastIsNr=lastIsNr||crtTxt.endsWith("e")||crtTxt.endsWith("π");


        switch (cmd) {
            case "+", "-", "x", "/", "=", ".":
                if (duplicate || crtTxt.isEmpty()||consecSymbols)
                    break;
                equationTxt.setText(crtTxt + cmd);
                break;
            case "C":
                equationTxt.setText(null);
                equationRes.setText("0");
                break;
            case "DEL":
                if (crtTxt.equals("0") || crtTxt.isEmpty())
                    break;
                equationTxt.setText(crtTxt.substring(0, crtTxt.length() - 1));
                break;
            case "π":
                if (duplicate)
                    break;
                if (crtTxt.equals("0")) {
                    equationTxt.setText("π");
                    break;}
                if(lastIsNr){
                    equationTxt.setText(crtTxt+"x" + "π");
                    break;}
                equationTxt.setText(crtTxt + "π");
                break;
            case "e":
                if (duplicate)
                    break;
                if (crtTxt.equals("0")) {
                    equationTxt.setText("e");
                    break;}
                if(lastIsNr){
                    equationTxt.setText(crtTxt+"x"+cmd);
                    break;}
                equationTxt.setText(crtTxt + cmd);
                break;
            case "(":
                if (crtTxt.equals("0")){
                    equationTxt.setText(cmd);
                break;}
                if(lastIsNr){
                    equationTxt.setText(crtTxt+"x"+cmd);
                    break;}
                equationTxt.setText(crtTxt + cmd);
                break;
            case ")":
                if (crtTxt.isEmpty()||crtTxt.equals("0")||consecSymbols)
                    break;
                equationTxt.setText(crtTxt + cmd);
                break;
            case "1/x":
                if (resTxt.equals("0")) {
                    equationTxt.setText(null);
                    equationRes.setText("Error! Can not divide by 0!");
                    break;
                }
                equationTxt.setText("1/"+"("+crtTxt+")");
                break;
            case "0":
                if (crtTxt.endsWith("/")) {
                    equationTxt.setText("0");
                    equationRes.setText("Error! Can not divide by 0!");
                    break;
                }
                if (crtTxt.equals("0"))
                    break;
                equationTxt.setText(crtTxt + cmd);
                break;
            case "1", "2", "3", "4", "5", "6", "7", "8", "9":
                if (crtTxt.equals("0")) {
                    equationTxt.setText(cmd);
                    break;
                }
                equationTxt.setText(crtTxt + cmd);
                break;
            default:
                break;
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        Character key = e.getKeyChar();
        String crtTxt = equationTxt.getText();
        String resTxt = equationRes.getText();

        boolean duplicate = crtTxt.endsWith(String.valueOf(key));
        boolean consecSymbols=crtTxt.endsWith("+")||
                                  crtTxt.endsWith("-")||
                                  crtTxt.endsWith("=")||
                                  crtTxt.endsWith("x")||
                                  crtTxt.endsWith("/")||
                                  crtTxt.endsWith(".")||
                                  crtTxt.endsWith("(");

        boolean lastIsNr=false;
        for(int i=0;i<10;i++)
            lastIsNr=crtTxt.endsWith(String.valueOf((char)('0'+i)))||lastIsNr;

        lastIsNr=lastIsNr||crtTxt.endsWith("e")||crtTxt.endsWith("π");


        switch (key) {
            case '+', '-', 'x', '/', '.':
                    if (crtTxt.isEmpty())
                        break;
                    if(consecSymbols)   //works for dupes as well
                        break;
                    equationTxt.setText(crtTxt + key);
                    break;
            case (char)KeyEvent.VK_ENTER, '=':
                    if (crtTxt.isEmpty())
                        break;
                    if(consecSymbols)
                        break;
                    if(key==10 &&crtTxt.endsWith("="))
                        break;

                    equationTxt.setText(crtTxt+"=");
                    break;
            case 'c':
                    equationTxt.setText(null);
                    equationRes.setText("0");
                    break;
            case KeyEvent.VK_DELETE,KeyEvent.VK_BACK_SPACE:
                    if (crtTxt.equals("0") || crtTxt.isEmpty())
                        break;
                    equationTxt.setText(crtTxt.substring(0, crtTxt.length() - 1));
                    break;
            case 'e':
                    if (duplicate)
                        break;
                    if (crtTxt.equals("0")) {
                        equationTxt.setText("e");
                        break;}
                    if(lastIsNr){
                        equationTxt.setText(crtTxt+"x" + key);
                        break;}
                    equationTxt.setText(crtTxt + key);
                    break;
            case '(':
                    if (crtTxt.equals("0")){
                        equationTxt.setText(String.valueOf(key));
                    break;}
                    if(lastIsNr){
                        equationTxt.setText(crtTxt+"x"+key);
                        break;}
                    equationTxt.setText(crtTxt + key);
                    break;
            case ')':
                    if (crtTxt.isEmpty()||crtTxt.equals("0")||consecSymbols)
                        break;
                    equationTxt.setText(crtTxt + key);
                    break;
            case '0':
                    if (crtTxt.endsWith("/")) {
                        equationTxt.setText("0");
                        equationRes.setText("Error! Can not divide by 0!");
                        break;
                    }
                    if (crtTxt.equals("0"))
                        break;
                    equationTxt.setText(crtTxt + key);
                    break;
            case '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    if (crtTxt.equals("0")) {
                        equationTxt.setText(String.valueOf(key));
                        break;}
                    equationTxt.setText(crtTxt + key);
                    break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
};

