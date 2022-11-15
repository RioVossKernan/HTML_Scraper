//Rio Voss-Kernan, Nov 2022, HTML Scraper
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SwingGraphics implements ActionListener {
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

    public SwingGraphics() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("HTML Scraper");
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
        textPanel.setLayout(new GridLayout());
        ta = new JTextArea();
        ta.setEditable(false);
        ta.setFont(new Font("Poppins",Font.PLAIN,12));
    //set scrollable
        JScrollPane scroll = new JScrollPane(ta);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textPanel.add(scroll);
    //color
        //ta.setBackground(Color.black);
        //ta.setForeground(Color.GREEN);

    //button
        goButton = new JButton("GO");
        goButton.setFont(new Font("Poppins",Font.BOLD,20));
        goButton.setPreferredSize(new Dimension(200,50));
        goButton.setActionCommand("GO");
        goButton.addActionListener(new ButtonClickListener());

    //set labels
        termLabel = new JLabel("TERM:", JLabel.CENTER);
        urlLabel = new JLabel("URL:    ", JLabel.CENTER);
        termLabel.setFont(new Font("Poppins",Font.BOLD,20));
        urlLabel.setFont(new Font("Poppins",Font.BOLD,20));

    //set term panel
        termPanel.setLayout(new FlowLayout());
        termPanel.add(termLabel);
        term = new JTextField();
        term.setPreferredSize(new Dimension(400,20));
        termPanel.add(term);
        inputPanel.add(termPanel);

    //set URL panel
        urlPanel.setLayout(new FlowLayout());
        urlPanel.add(urlLabel);
        url = new JTextField();
        url.setPreferredSize(new Dimension(400,20));
        urlPanel.add(url);
        inputPanel.add(urlPanel);

    //set topPanel Layout
        topPanel.setLayout(new FlowLayout());
        topPanel.add(inputPanel);
        topPanel.add(goButton);

    // set Frame layout
        mainFrame.add(textPanel,BorderLayout.CENTER);
        mainFrame.add(topPanel,BorderLayout.NORTH);

        mainFrame.setVisible(true);
    }

    public void scrapeURLToTextArea(String pURL,String pTerm){
        try{
            //Set up inputStream
            URL url = new URL(pURL);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;

            //Read line-by-line until end
            while ((line = reader.readLine()) != null) {
                //get Wiki Links
                if(pTerm.equals("WIKI LINKS")) {
                    if (line.contains("href=\"/wiki")) {
                        int index = line.indexOf("href=\"") + 6; //start at href= because that signals start of link
                        String link = "";

                        while (line.charAt(index) != '\"') {    //continue through chars until the end quotation mark
                            link = link.concat(line.charAt(index) + "");   //add char to link
                            index++;
                        }

                        if (link.contains("/wiki/")) //some links are .json or just #. This avoids that
                            ta.append(link + "\n");
                    }

                //get LINKS
                }else if(pTerm.equals("LINKS")) {
                        if(line.contains("href=\"")){
                            int index = line.indexOf("href=\"") + 6; //start at href= because that signals start of link
                            String link = "";

                            while(line.charAt(index) != '\"') {    //continue through chars until the end quotation mark
                                link = link.concat(line.charAt(index)+"");   //add char to link
                                index ++;
                            }

                            if(link.contains("http")) //some links are .json or just #. This avoids that
                                ta.append(link + "\n");
                        }

                //get term mentions
                }else  if(line.contains(pTerm)) {
                    ta.append(line + "\n");
                }
            }
            reader.close();

        }catch(Exception ex){
            ta.setText(ex + "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("GO")) {
                ta.setText("");
                String pURL = url.getText();
                String pTerm = term.getText();
                scrapeURLToTextArea(pURL,pTerm);
            }
        }
    }
}