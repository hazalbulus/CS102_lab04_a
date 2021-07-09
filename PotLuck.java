import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Pot Luck class
 * @author Hazal Buluş
 * @version 1.0 09.07.2021 
 */

public class PotLuck extends JFrame{

    //Constants
    private final int ROW = 4;
    private final int COLUMN = 4;
    private final int WIDTH= 400;
    private final int HEIGHT= 400;

    //Variables
    private int bomb1;
    private int bomb2;
    private int prize;
    private JButton buttons[];
    private GridLayout layout;
    private JPanel panel;
    private int attempt;
    private JLabel attemptLabel;


    public PotLuck(){

        super("Welcome to Pot Luck Game!");
        attempt = 0;

        panel = new JPanel();
        layout = new GridLayout(ROW,COLUMN, 6, 6);
        panel.setLayout( layout);
        panel.setBackground(new Color(240, 164, 226));
        attemptLabel = new JLabel("Guesses: " + attempt);
        add(attemptLabel, BorderLayout.NORTH);

        start();

        for(int i = 0; i < ROW * COLUMN; i++){
            buttons[i].addActionListener(new ButtonHandler());
        }
        
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public void addButtons(JPanel panel)
    {
        for(int i = 0; i < ROW * COLUMN; i++){
            JButton button = new JButton((i + 1) +"");
            buttons[i] = button;
            button.setBackground(new Color(177, 110, 255));
            panel.add(button);
        }
    }

    public class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JButton button = (JButton) e.getSource();
            attempt++;

            if(button.getText().equals(bomb1 + "") || button.getText().equals(bomb2 + ""))
            {
                JLabel label = new JLabel();
                ImageIcon bomb = new ImageIcon(this.getClass().getResource("/bomb.png"));
                label.setIcon(bomb);
                label.setSize(button.getWidth(), button.getHeight());
                button.add(label);
                button.setText("");
                attemptLabel.setText("Oh no! You are blown up at attempt " + attempt + "!!!");
                attemptLabel.setForeground(Color.RED);

                for(int i = 0; i< buttons.length; i++){
                    if (buttons[i].getBackground() != Color.DARK_GRAY){
                        buttons[i].setBackground(new Color(161, 0, 0  ));
                    }
                }

                finish();
            }

            else if(button.getText().equals(prize + ""))
            {   
                JLabel label = new JLabel();
                ImageIcon bomb = new ImageIcon(this.getClass().getResource("/star.png"));
                label.setIcon(bomb);
                label.setSize(button.getWidth(), button.getHeight());
                button.add(label);
                button.setText("");
                attemptLabel.setText("Congratulations! You find the prize at attempt " + attempt + "!!!");
                attemptLabel.setForeground(new Color(43, 186, 45));

                for(int i = 0; i< buttons.length; i++){
                    if (buttons[i].getBackground() != Color.DARK_GRAY){
                        buttons[i].setBackground(new Color(182, 255, 168 ));
                    }
                }

                finish();
            }
            else{
                button.setEnabled(false);
                button.setBackground(Color.GRAY);
                attemptLabel.setText("Guesses: " + attempt);
            }
        }
    }

    public void start(){
        bomb1 = 0;
        bomb2 = 0;
        prize = 0;
        while(bomb1 == bomb2 || bomb1 == prize || prize == bomb2){
            bomb1 = (int) ((Math.random() * ROW * COLUMN ) + 1); 
            bomb2 = (int) ((Math.random() * ROW * COLUMN ) + 1); 
            prize = (int) ((Math.random() * ROW * COLUMN ) + 1); 
        }

        buttons = new JButton[ROW * COLUMN];
        addButtons(panel);
    }

    public void finish(){
        for(int i = 0; i< buttons.length; i++){
            buttons[i].setEnabled(false);
        }
    }
}