import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;

    private JLabel urlLabel;
    private JLabel termLabel;

    private JPanel topPanel;
    private JPanel urlPanel;
    private JPanel termPanel;
    private JPanel textPanel;

    private JButton goButton;

    private JTextArea ta;
    private JTextField url;
    private JTextField term;

    private int WIDTH=800;
    private int HEIGHT=700;

    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        //Instantiate Panels
        textPanel = new JPanel();
        topPanel = new JPanel();
        urlPanel = new JPanel();
        termPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2,1));


        //text area
        textPanel.setLayout(new GridLayout(1,1));
        ta = new JTextArea();
        ta.setBounds(200,200,50,50);
        textPanel.add(ta);

        //button
        goButton = new JButton("GO");
        goButton.setFont(new Font("Poppins",Font.BOLD,20));
        goButton.setPreferredSize(new Dimension(200,50));
        goButton.setActionCommand("GO");
        goButton.addActionListener(new ButtonClickListener());


        //set labels
        termLabel = new JLabel("URL:  ", JLabel.CENTER);
        urlLabel = new JLabel("TERM:", JLabel.CENTER);
        termLabel.setFont(new Font("Poppins",Font.BOLD,20));
        urlLabel.setFont(new Font("Poppins",Font.BOLD,20));

        //set URL panel
        urlPanel.setLayout(new FlowLayout());
        urlPanel.add(urlLabel);
        url = new JTextField();
        url.setPreferredSize(new Dimension(400,20));
        urlPanel.add(url);
        inputPanel.add(urlPanel);

        //set term panel
        termPanel.setLayout(new FlowLayout());
        termPanel.add(termLabel);
        term = new JTextField();
        term.setPreferredSize(new Dimension(400,20));
        termPanel.add(term);
        inputPanel.add(termPanel);


        //set topPanel Layout
        topPanel.setLayout(new FlowLayout());
        topPanel.add(inputPanel);
        topPanel.add(goButton);


        // set Frame layout
        mainFrame.add(textPanel,BorderLayout.CENTER);
        mainFrame.add(topPanel,BorderLayout.NORTH);


        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("GO")) {

            }
        }
    }
}