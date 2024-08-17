package Calculator;

import Calculator.UI.UIBuilder;
import Calculator.Evaluator.Evaluation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator extends JFrame implements ActionListener, KeyListener {

    /////window and window element parameters
    private final Dimension WINDOW_DIMENSION=new Dimension(350,400),  //main frame dimension
                            DISPLAY_PANEL_DIM=new Dimension(340, 50), //display panel dimension
                            BUTTONS_PANEL_DIM=new Dimension(350, 550);//buttons panel dimension


    private final int HGAP=3,VGAP=3; //horizontal and vertical gap

    private final Color BACKGROUND_COLOR=Color.WHITE,                                   //frame bg color
                        EQUATION_TXT_COLOR=Color.GRAY,
                        BUTTONS_BACKGROUND_COLOR=new Color(144, 144, 150),
                        BUTTONS_PANEL_BACKGROUND_COLOR=new Color(250, 250, 245);

    private final Font  BUTTONS_FONT=new Font("Arial", Font.PLAIN, 23),
                        EQUATION_TXT_FONT=new Font("Arial", Font.PLAIN, 21),
                        EQUATION_RES_FONT=new Font("Arial", Font.BOLD, 25);

    String[] BUTTONS_TEXT = {
        "PI", "e", "C", "DEL",
        "^", "(", ")", "*",
        "7", "8", "9", "/",
        "4", "5", "6", "+",
        "1", "2", "3", "-",
        ".", "0", "1/x", "="
    };

    ////////////Frame icon/////////////////////////
    Image ICON = getToolkit().getImage("src/Calculator/icon.png");


    ///////////////variables and elements//////////
    protected JTextField equationTxt, equationRes;   //equation text field and result text field
    protected JPanel displayPanel, buttonPanel;
    protected JButton[] buttons = new JButton[24];


    public Calculator() {
        super();
        initUI();
    }



    void initUI(){

        UIBuilder.frameBuilder(this,WINDOW_DIMENSION,BACKGROUND_COLOR,ICON,HGAP,VGAP);

        displayPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel = new JPanel(new GridLayout(6, 4, HGAP, VGAP));

        equationTxt=new JTextField(null);  //default text
        equationRes=new JTextField("0");

        UIBuilder.displayBuilder(equationTxt,equationRes,
                                EQUATION_TXT_FONT,
                                EQUATION_RES_FONT,
                                BACKGROUND_COLOR,
                                EQUATION_TXT_COLOR);

        UIBuilder.paneBuilder(displayPanel,buttonPanel,
                                DISPLAY_PANEL_DIM,
                                BUTTONS_PANEL_DIM,
                                Color.gray,
                                BUTTONS_PANEL_BACKGROUND_COLOR);

        displayPanel.add(equationTxt);
        displayPanel.add(equationRes);

        UIBuilder.buttonsBuilder(buttonPanel,
                                this,
                                buttons,
                                BUTTONS_TEXT,
                                BUTTONS_FONT,
                                BUTTONS_BACKGROUND_COLOR);

        this.add(displayPanel);
        this.add(buttonPanel);
        this.setVisible(true);
        this.pack();}



    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        String crtTxt=equationTxt.getText();
        String resTxt = equationRes.getText();
        String[] res;

        res = Evaluation.parseIncoming(crtTxt,resTxt,cmd);
        equationTxt.setText(res[0]);
        equationRes.setText(res[1]);}



    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        String cmd;
        String[] res;

        switch (key) {
            case KeyEvent.VK_ENTER:
                cmd="=";
                break;
            case KeyEvent.VK_DELETE,KeyEvent.VK_BACK_SPACE:
                cmd="DEL";
                break;
            default:
                cmd=String.valueOf(key);
                break;
        }

        String crtTxt = equationTxt.getText();
        String resTxt = equationRes.getText();

        res = Evaluation.parseIncoming(crtTxt,resTxt,cmd);
        equationTxt.setText(res[0]);
        equationRes.setText(res[1]);}

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

