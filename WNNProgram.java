/*WNN Program
 @authors Michael Kelley and James Irey
 @version 3.0
 *FINAL VERSION
 *May 3, 2004
 */

//********* MAIN CLASS *********
import javax.swing.JOptionPane; //Gui Interface
import javax.swing.JDialog; //Use Dialog interface
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.beans.*; //Messing with java beans
import java.awt.*;
import java.awt.event.*;
import java.io.*;  //Handle input/output
import javax.swing.*;
import java.util.*;



public class WNNProgram extends JPanel {


	
	//Creating filewriter and bufferedreader
    FileWriter fw;
    FileReader fr;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //Variables to hold users email address, questions, and comments when inputted
    String email;
    String question;
    String comment;
    String survey1winner, survey2winner, totalsurvey1winner, totalsurvey2winner;
    String password, reenablepass;

    //Variables
    int deletequestions, deleteemails, deletecomments;
    int option1tally =0, option2tally = 0, option3tally = 0, option4tally = 0;
    int option5tally =0, option6tally = 0, option7tally = 0, option8tally = 0;
    int tallyclearcount = 0, questionclearcount = 0, emailclearcount = 0;
    int commentclearcount = 0;
    int questionnumber = 1, emailnumber = 1, commentnumber = 1;
    int count;
    
    int totaloption1tally =0, totaloption2tally = 0, totaloption3tally = 0, totaloption4tally = 0;
    int totaloption5tally = 0, totaloption6tally = 0, totaloption7tally = 0, totaloption8tally = 0;
    
    //Creating the string to appear when user highlights tab               
    JLabel label;
    ImageIcon icon = new ImageIcon("images/middle.gif");
    JFrame frame;
    JFrame frame2;
    JFrame passwordFrame;
    String simpleDialogDesc = "Is downloading music from P2P networks ethical?";
    String iconDesc = "Please select an item then click the Choose It button";
    String moreDialogDesc = "Is pirating software ethical?";
    String commentDialogDesc = "We would like your comments";
    String adminDialogDesc = "Administrative tools";
    
    
    	
                        
		//Frame creation and components
    public WNNProgram(JFrame frame) {
        this.frame = frame;
	
        //Create the components
        JPanel frequentPanel = createBasicDialogBox();
        JPanel featurePanel = createOptionDialogBox();
        JPanel iconPanel = createPictureDialogBox();
        JPanel commentPanel = createCommentDialogBox();
        JPanel administrationPanel = createAdministrationDialogBox();
        
        label = new JLabel("Click the \"Choose This\" button"
                           + " to engage your choice.",
                           JLabel.CENTER);

        //Lay them out
        Border padding = BorderFactory.createEmptyBorder(40,40,25,40);
        frequentPanel.setBorder(padding);
        featurePanel.setBorder(padding);
        iconPanel.setBorder(padding);
        commentPanel.setBorder(padding);
        administrationPanel.setBorder(padding);
        
        //Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Survey 1", null, 
                          frequentPanel, simpleDialogDesc);
        tabbedPane.addTab("Survey 2", null, 
                          featurePanel, moreDialogDesc);
        tabbedPane.addTab("Comment", null, 
                          commentPanel, commentDialogDesc);
        tabbedPane.addTab("Vote Results", null, 
                          iconPanel, iconDesc);
                          
        tabbedPane.addTab("Options", null,
                           administrationPanel, adminDialogDesc);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
	

    void setLabel(String newText) {
        label.setText(newText);
    }

    private JPanel createBasicDialogBox(){
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String Option1Command = "Option1";
        final String Option2Command = "Option2";
        final String Option3Command = "Option3";
        final String Option4Command = "Option4";
        final String exitCommand = "exit";
        
        //Radio buttons for user responses
        
        radioButtons[0] = new JRadioButton("Yes, free music is awesome.");
        radioButtons[0].setActionCommand(Option1Command);

        radioButtons[1] = new JRadioButton("Yes, but only get the songs you need.");
        radioButtons[1].setActionCommand(Option2Command);

        radioButtons[2] = new JRadioButton("No, it is considered stealing.");
        radioButtons[2].setActionCommand(Option3Command);

        radioButtons[3] = new JRadioButton("No, the music industry suffers each time it happens.");
        radioButtons[3].setActionCommand(Option4Command);
        
        radioButtons[4] = new JRadioButton("Exit Program");
        radioButtons[4].setActionCommand(exitCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Choose This");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();
                
                
                
                //Option1 dialog
                if (command == Option1Command) {
                	JOptionPane.showMessageDialog(
                		frame, "Thank you for your vote",
                		"Message",
                		JOptionPane.INFORMATION_MESSAGE);
                		option1tally = option1tally + 1;
                		//Try-catch block
                		try {
                			File option1_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option1tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                
                			
                			if(option1_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option1_file));
                					totaloption1tally = in.readInt();
                					in.close();
                					totaloption1tally++;
                					out = new DataOutputStream(new FileOutputStream(option1_file));
                					out.writeInt(totaloption1tally);
                					out.close();
                				}
                			
                			else
                			{
                			
                				totaloption1tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option1_file));
                				out.writeInt(totaloption1tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption1tally);
                				
               
                	}	
                    

                //Option2 dialog
                 else if (command == Option2Command) {
                    JOptionPane.showMessageDialog(
                            frame, "Thank you for your vote",
                            "Message",
                              JOptionPane.INFORMATION_MESSAGE);
                              option2tally = option2tally + 1;
                      try {
                			File option2_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option2tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option2_file.exists())
                				{
                					
                					in = new DataInputStream(new FileInputStream(option2_file));
                					totaloption2tally = in.readInt();
                					in.close();
                					totaloption2tally++;
                					out = new DataOutputStream(new FileOutputStream(option2_file));
                					out.writeInt(totaloption2tally);
                					out.close();
                				}
                			
                			else
                			{
                				
                				totaloption2tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option2_file));
                				out.writeInt(totaloption2tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption2tally);
                 //Option3 dialog
                } else if (command == Option3Command) {
                    JOptionPane.showMessageDialog(
                            frame, "Thank you for your vote",
                            "Message",
                              JOptionPane.INFORMATION_MESSAGE);
                              option3tally = option3tally + 1;
                             
                try {
                			File option3_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option3tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option3_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option3_file));
                					totaloption3tally = in.readInt();
                					in.close();
                					totaloption3tally++;
                					out = new DataOutputStream(new FileOutputStream(option3_file));
                					out.writeInt(totaloption3tally);
                					out.close();
                				}
                			
                			else
                			{
                			
                				totaloption3tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option3_file));
                				out.writeInt(totaloption3tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file1.");
                    	return ;
                    }
                		System.out.println(totaloption3tally);
               

                //Option4 dialog
            } else if (command == Option4Command) {
            	JOptionPane.showMessageDialog(
            		frame, "Thank you for your vote",
            		"Message",
            		JOptionPane.INFORMATION_MESSAGE);
            		option4tally = option4tally + 1;
              
                  try {
                			File option4_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option4tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option4_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option4_file));
                					totaloption4tally = in.readInt();
                					in.close();
                					totaloption4tally++;
                					out = new DataOutputStream(new FileOutputStream(option4_file));
                					out.writeInt(totaloption4tally);
                					out.close();
                				}
                			
                			else
                			{
                			
                				totaloption4tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option4_file));
                				out.writeInt(totaloption4tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file2.");
                    	return ;
                    }
                		System.out.println(totaloption4tally);
                  
                    }
                     //EXIT COMMAND FOR SURVEY 1: THIS REALLY WORKS!
                 else if (command == exitCommand) {
                    System.exit(0);
                                 

            }
              
                return;
            }
        });

        return createPane(simpleDialogDesc + ":",
                          radioButtons, 
                          showItButton);
    }
        
    

    //Button number *NEEDS* to be even
    private JPanel create2ColPane(String description,
                                  JRadioButton[] radioButtons,
                                  JButton showButton) {
        JLabel label = new JLabel(description);
        int numPerColumn = radioButtons.length/2;

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0, 2));
        for (int i = 0; i < numPerColumn; i++) {
            grid.add(radioButtons[i]);
            grid.add(radioButtons[i + numPerColumn]);
        }

        //Make and lay out
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.add(label);
        grid.setAlignmentX(0.0f);
        box.add(grid);

        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);

        return pane;
    }
    
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {
        
        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);
        return pane;
    }

       //Another dialog box
    private JPanel createPictureDialogBox() {
        JButton showItButton = null;

        final int numButtons = 8; 
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        final String Info1Command = "Info1";
        final String Info2Command = "Info2";
        final String Info3Command = "Info3";
        final String Info4Command = "Info4";
        final String Info5Command = "Info5";
        final String Info6Command = "Info6";
        final String Info7Command = "Info7";  
        final String exit2Command = "exit2"; 
        
        //More radio buttons
        
        radioButtons[0] = new JRadioButton("Vote Results");
        radioButtons[0].setActionCommand(Info1Command);

        radioButtons[1] = new JRadioButton("Survey Info");
        radioButtons[1].setActionCommand(Info2Command);

        radioButtons[2] = new JRadioButton("WNN Contact Info");
        radioButtons[2].setActionCommand(Info3Command);

        radioButtons[3] = new JRadioButton("WNN Newsletter");
        radioButtons[3].setActionCommand(Info4Command);

        radioButtons[4] = new JRadioButton("About WNN");
        radioButtons[4].setActionCommand(Info5Command);

        radioButtons[5] = new JRadioButton("About Program");
        radioButtons[5].setActionCommand(Info6Command);
        
        radioButtons[6] = new JRadioButton("About our Class"); 
        radioButtons[6].setActionCommand(Info7Command);     
        
        radioButtons[7] = new JRadioButton("Exit Program"); 
        radioButtons[7].setActionCommand(exit2Command); 

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Choose This");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

            

                //Info1 dialog
                //Finding the survey winner
                if (command == Info1Command) {
                	if (option1tally > option2tally)
                {
                	if (option1tally > option3tally)
                	{
                		if (option1tally > option4tally)
                		{
                		survey1winner = " answer 1";	
                		}
                	}
                }
                
                if (option2tally > option1tally)
                {
                	if (option2tally > option3tally)
                	{
                		if (option2tally > option4tally)
                		{
                		survey1winner = " answer 2";	
                		}
                	}
                }
                
                if (option3tally > option1tally)
                {
                	if (option3tally > option2tally)
                	{
                		if (option3tally > option4tally)
                		{
                		survey1winner = " answer 3";	
                		}
                	}
                }
                
                if (option4tally > option1tally)
                {
                	if (option4tally > option2tally)
                	{
                		if (option4tally > option3tally)
                		{
                		survey1winner = " answer 4";	
                		}
                	}
                }
                
                //Jim's code for finding winner.
                if (option1tally == option2tally && option1tally > option3tally && option1tally > option4tally)
                	survey1winner = " Tie between answers 1 and 2";
                	
                if (option1tally == option3tally && option1tally > option2tally && option1tally > option4tally)
                	survey1winner = " Tie between answers 1 and 3";
                
                if (option1tally == option4tally && option1tally > option2tally && option1tally > option3tally)
                	survey1winner = " Tie between answers 1 and 4";
                	
                if (option2tally == option3tally && option2tally > option1tally && option2tally > option4tally)
                	survey1winner = " Tie between answers 2 and 3";	
                
                if (option2tally == option4tally && option2tally > option1tally && option2tally > option3tally)
                	survey1winner = " Tie between answers 2 and 4";
                	
                if (option3tally == option4tally && option3tally > option1tally && option3tally > option2tally)
                	survey1winner = " Tie between answers 3 and 4";
                
                if (option1tally == option2tally && option1tally == option3tally && option1tally > option4tally)
                	survey1winner = " Tie between answers 1, 2 and 3";
                	
                if (option1tally == option2tally && option1tally == option4tally && option1tally > option3tally)
                	survey1winner = " Tie between answers 1, 2 and 4";
                
                if (option2tally == option3tally && option2tally == option4tally && option2tally > option1tally)
                	survey1winner = " Tie between answers 2, 3 and 4";
                
                if (option1tally == option2tally && option2tally == option3tally && option3tally == option4tally)
                	survey1winner = " Tie between answers 1, 2, 3 and 4";
                	
                	if (option5tally > option6tally)
                {
                	if (option5tally > option7tally)
                	{
                		if (option5tally > option8tally)
                		{
                		survey2winner = " answer 1";	
                		}
                	}
                }
                
                if (option6tally > option5tally)
                {
                	if (option6tally > option7tally)
                	{
                		if (option6tally > option8tally)
                		{
                		survey2winner = " answer 2";	
                		}
                	}
                }
                
                if (option7tally > option5tally)
                {
                	if (option7tally > option6tally)
                	{
                		if (option7tally > option8tally)
                		{
                		survey2winner = " answer 3";	
                		}
                	}
                }
                
                if (option8tally > option5tally)
                {
                	if (option8tally > option6tally)
                	{
                		if (option8tally > option7tally)
                		{
                		survey2winner = " answer 4";	
                		}
                	}
                }
                
                if (option5tally == option6tally && option5tally > option7tally && option5tally > option8tally)
                	survey2winner = " Tie between answers 1 and 2";
                	
                if (option5tally == option7tally && option5tally > option6tally && option5tally > option8tally)
                	survey2winner = " Tie between answers 1 and 3";
                
                if (option5tally == option8tally && option5tally > option6tally && option5tally > option7tally)
                	survey2winner = " Tie between answers 1 and 4";
                	
                if (option6tally == option7tally && option6tally > option5tally && option6tally > option8tally)
                	survey2winner = " Tie between answers 2 and 3";	
                
                if (option6tally == option8tally && option6tally > option5tally && option6tally > option7tally)
                	survey2winner = " Tie between answers 2 and 4";
                	
                if (option7tally == option8tally && option7tally > option5tally && option7tally > option6tally)
                	survey2winner = " Tie between answers 3 and 4";
                
                if (option5tally == option6tally && option5tally == option7tally && option5tally > option8tally)
                	survey2winner = " Tie between answers 1, 2 and 3";
                	
                if (option5tally == option6tally && option5tally == option8tally && option5tally > option7tally)
                	survey2winner = " Tie between answers 1, 2 and 4";
                
                if (option6tally == option7tally && option6tally == option8tally && option6tally > option5tally)
                	survey2winner = " Tie between answers 2, 3 and 4";
                
                if (option5tally == option6tally && option6tally == option7tally && option7tally == option8tally)
                	survey2winner = " Tie between answers 1, 2, 3 and 4";
                
                	
                	
                	if (totaloption1tally > totaloption2tally)
                {
                	if (totaloption1tally > totaloption3tally)
                	{
                		if (totaloption1tally > totaloption4tally)
                		{
                		totalsurvey1winner = " answer 1";	
                		}
                	}
                }
                
                if (totaloption2tally > totaloption1tally)
                {
                	if (totaloption2tally > totaloption3tally)
                	{
                		if (totaloption2tally > totaloption4tally)
                		{
                		totalsurvey1winner = " answer 2";	
                		}
                	}
                }
                
                if (totaloption3tally > totaloption1tally)
                {
                	if (totaloption3tally > totaloption2tally)
                	{
                		if (totaloption3tally > totaloption4tally)
                		{
                		totalsurvey1winner = " answer 3";	
                		}
                	}
                }
                
                if (totaloption4tally > totaloption1tally)
                {
                	if (totaloption4tally > totaloption2tally)
                	{
                		if (totaloption4tally > totaloption3tally)
                		{
                		totalsurvey1winner = " answer 4";	
                		}
                	}
                }
                
                if (totaloption1tally == totaloption2tally && totaloption1tally > totaloption3tally && totaloption1tally >totaloption4tally)
                	totalsurvey1winner = " Tie between answers 1 and 2";
                	
                if (totaloption1tally == totaloption3tally && totaloption1tally > totaloption2tally && totaloption1tally > totaloption4tally)
                	totalsurvey1winner = " Tie between answers 1 and 3";
                
                if (totaloption1tally == totaloption4tally && totaloption1tally > totaloption2tally && totaloption1tally > totaloption3tally)
                	totalsurvey1winner = " Tie between answers 1 and 4";
                	
                if (totaloption2tally == totaloption3tally && totaloption2tally > totaloption1tally && totaloption2tally > totaloption4tally)
                	totalsurvey1winner = " Tie between answers 2 and 3";	
                
                if (totaloption2tally == totaloption4tally && totaloption2tally > totaloption1tally && totaloption2tally > totaloption3tally)
                	totalsurvey1winner = " Tie between answers 2 and 4";
                	
                if (totaloption3tally == totaloption4tally && totaloption3tally > totaloption1tally && totaloption3tally > totaloption2tally)
                	totalsurvey1winner = " Tie between answers 3 and 4";
                
                if (totaloption1tally == totaloption2tally && totaloption1tally == totaloption3tally && totaloption1tally > totaloption4tally)
                	totalsurvey1winner = " Tie between answers 1, 2 and 3";
                	
                if (totaloption1tally == totaloption2tally && totaloption1tally == totaloption4tally && totaloption1tally > totaloption3tally)
                	totalsurvey1winner = " Tie between answers 1, 2 and 4";
                
                if (totaloption2tally == totaloption3tally && totaloption2tally == totaloption4tally && totaloption2tally > totaloption1tally)
                	totalsurvey1winner = " Tie between answers 2, 3 and 4";
                
                if (totaloption1tally == option2tally && option2tally == option3tally && option3tally == option4tally)
                	totalsurvey1winner = " Tie between answers 1, 2, 3 and 4";
                	
                	if (totaloption5tally > totaloption6tally)
                {
                	if (totaloption5tally > totaloption7tally)
                	{
                		if (totaloption5tally > totaloption8tally)
                		{
                		totalsurvey2winner = " answer 1";	
                		}
                	}
                }
                
                if (totaloption6tally > totaloption5tally)
                {
                	if (totaloption6tally > totaloption7tally)
                	{
                		if (totaloption6tally > totaloption8tally)
                		{
                		totalsurvey2winner = " answer 2";	
                		}
                	}
                }
                
                if (totaloption7tally > totaloption5tally)
                {
                	if (totaloption7tally > totaloption6tally)
                	{
                		if (totaloption7tally > totaloption8tally)
                		{
                		totalsurvey2winner = " answer 3";	
                		}
                	}
                }
                
                if (totaloption8tally > totaloption5tally)
                {
                	if (totaloption8tally > totaloption6tally)
                	{
                		if (totaloption8tally > totaloption7tally)
                		{
                		totalsurvey2winner = " answer 4";	
                		}
                	}
                }
                
                if (totaloption5tally == totaloption6tally && totaloption5tally > totaloption7tally && totaloption5tally > totaloption8tally)
                	totalsurvey2winner = " Tie between answers 1 and 2";
                	
                if (totaloption5tally == totaloption7tally && totaloption5tally > totaloption6tally && totaloption5tally > totaloption8tally)
                	totalsurvey2winner = " Tie between answers 1 and 3";
                
                if (totaloption5tally == totaloption8tally && totaloption5tally > totaloption6tally && totaloption5tally > totaloption7tally)
                	totalsurvey2winner = " Tie between answers 1 and 4";
                	
                if (totaloption6tally == totaloption7tally && totaloption6tally > totaloption5tally && totaloption6tally > totaloption8tally)
                	totalsurvey2winner = " Tie between answers 2 and 3";	
                
                if (totaloption6tally == totaloption8tally && totaloption6tally > totaloption5tally && totaloption6tally > totaloption7tally)
                	totalsurvey2winner = " Tie between answers 2 and 4";
                	
                if (totaloption7tally == totaloption8tally && totaloption7tally > totaloption5tally && totaloption7tally > totaloption6tally)
                	totalsurvey2winner = " Tie between answers 3 and 4";
                
                if (totaloption5tally == totaloption6tally && totaloption5tally == totaloption7tally && totaloption5tally > totaloption8tally)
                	totalsurvey2winner = " Tie between answers 1, 2 and 3";
                	
                if (totaloption5tally == totaloption6tally && totaloption5tally == totaloption8tally && totaloption5tally > totaloption7tally)
                	totalsurvey2winner = " Tie between answers 1, 2 and 4";
                
                if (totaloption6tally == totaloption7tally && totaloption6tally == totaloption8tally && totaloption6tally > totaloption5tally)
                	totalsurvey2winner = " Tie between answers 2, 3 and 4";
                
                if (totaloption5tally == totaloption6tally && totaloption6tally == totaloption7tally && totaloption7tally == totaloption8tally)
                	totalsurvey2winner = " Tie between answers 1, 2, 3 and 4";
                	
                	//try writing to file
                try {
                		File option1_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option1tally.txt");
                		File option2_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option2tally.txt");
                		File option3_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option3tally.txt");		
                		File option4_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option4tally.txt");
                		File option5_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option5tally.txt");
                		File option6_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option6tally.txt");
                		File option7_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option7tally.txt");
                		File option8_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option8tally.txt");
                						
                		DataInputStream in;
                	
                	if (option1_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option1_file));
                		totaloption1tally = in.readInt();
                		in.close();
                		}
                		
                	if (option2_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option2_file));
                		totaloption2tally = in.readInt();
                		in.close();
                		}
                	
                	if (option3_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option3_file));
                		totaloption3tally = in.readInt();
                		in.close();
                		}
                		
                	if (option4_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option4_file));
                		totaloption4tally = in.readInt();
                		in.close();
                		}
                		
                	if (option5_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option5_file));
                		totaloption5tally = in.readInt();
                		in.close();
                		}
                		
                	if (option6_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option6_file));
                		totaloption6tally = in.readInt();
                		in.close();
                		}
                		
                	if (option7_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option7_file));
                		totaloption7tally = in.readInt();
                		in.close();
                		}
                		
                	if (option8_file.exists())
                	{
                	
                		in = new DataInputStream(new FileInputStream(option8_file));
                		totaloption8tally = in.readInt();
                		in.close();
                		}
                	}
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }	
                	
                    JOptionPane.showMessageDialog(frame,
                                    "Current Vote Results:\n Current Survey 1 Winner: " + survey1winner + "\n Current Survey 2 Winner: " + survey2winner,
                                    "Vote Talley",
                                    JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(frame,
                                    "Overall Vote Results:\n Survey 1: \n \nThe total votes for answer 1 are: " + totaloption1tally +
                                      "\nThe total votes for answer 2 are: " + totaloption2tally +
                                      "\nThe total votes for answer 3 are: " + totaloption3tally +
                                      "\nThe total votes for answer 4 are: " + totaloption4tally +
                                      "\nThe winner of survey 1 is: " + totalsurvey1winner,
                                    "Total Vote Talley",
                                    JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(frame,
                                    "Overall Vote Results:\n Survey 2: \n \nThe total votes for answer 1 are: " + totaloption5tally +
                                      "\nThe total votes for answer 2 are: " + totaloption6tally +
                                      "\nThe total votes for answer 3 are: " + totaloption7tally +
                                      "\nThe total votes for answer 4 are: " + totaloption8tally +
                                      "\nThe winner of survey 2 is: " + totalsurvey2winner,
                                    "Total Vote Talley",
                                    JOptionPane.PLAIN_MESSAGE);
                   
                //Info2 dialog
                } else if (command == Info2Command) {
                    JOptionPane.showMessageDialog(frame,
                                    "Music and software pirating has been in the news recently. \n We at WNN feel this would be a survey that can reach out to a broad audience \n Since everyone seems to have an opinion on this matter",
                                    "Survey Info",
                                    JOptionPane.INFORMATION_MESSAGE);
    
                //Info3 dialog
                } else if (command == Info3Command) {
                    JOptionPane.showMessageDialog(frame,
                                    "EditorMail@WNN.net \n 1-800-6593 \n WNN \n 3544 News Avenue \n Pittsburgh, PA \n 15392",
                                    "Contact WNN",
                                    JOptionPane.QUESTION_MESSAGE);
                                    }
                                    
                           
                    
                //Info4 dialog
                 else if (command == Info4Command) {
                 	
                 	//Try-catch block
                   try {
                        
                     
				     File emailnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\emailnumber.txt");         
                      DataInputStream in;
                      		
				               	if (emailnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(emailnumber_file));
					                emailnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
					    //Create a storage limit
					    if(emailnumber == 21)
					    {
					    JOptionPane.showMessageDialog(frame, "Storage amount full! \n Please contact your administrator to continue",
    	  				"Storage Full",
    	  				JOptionPane.INFORMATION_MESSAGE);
					    }
					    else
					    {
					    
				do
				{
				
				
				
                	
                 email = JOptionPane.showInputDialog(
                 	frame,
                	"Please type in your email address",
                 	"Newsletter Subscription",
                 	 JOptionPane.QUESTION_MESSAGE);
                 	
              
            			if(email == null)
                		{
                		return;
                		}
                		
                		else if(email.equals (""))
                		{
                			JOptionPane.showMessageDialog(
                 	frame, "Please type in an email address",
                 	"Error, no email address was inputted",
                 	JOptionPane.WARNING_MESSAGE);
                		}
            
            
            
                		else
                		{
                		   //Try-catch block
                              try {
                        		
					                	
                     
				     File emailnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\emailnumber.txt");         
                      
                      DataOutputStream out;
				   	  DataInputStream in;	
				   	  			
				                	
				                	
				                		if (emailnumber_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(emailnumber_file));
					                		emailnumber = in.readInt();
					                		in.close();
					                		fw = new FileWriter("C:\\WINDOWS\\Desktop\\mikes\\email" + emailnumber + ".txt");
                    						fw.write(email);
                       					 	fw.close();
                       					 	emailnumber++;
					                		out = new DataOutputStream(new FileOutputStream(emailnumber_file));
					                		out.writeInt(emailnumber);
					                		out.close();
				                		}
				                		
				                		else
				                		{
				                			fw = new FileWriter("C:\\WINDOWS\\Desktop\\mikes\\email" + emailnumber + ".txt");
                    						fw.write(email);
                       					 	fw.close();
                       					 	emailnumber++;
					                		out = new DataOutputStream(new FileOutputStream(emailnumber_file));
					                		out.writeInt(emailnumber);
					                		out.close();
				                		}      
                       					 	
                        				}
					      
                      
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    }
                    
                      
                                    
                    }while(email.equals (""));
                    
                    }
                                    }
                                 
                //Info5 dialog
                 else if (command == Info5Command) {
                    JOptionPane.showMessageDialog(frame,
                                    "World News Network \n Channel 53 \n Copyright 2004 \n Leading the world in news.",
                                    "About WNN",
                                    JOptionPane.INFORMATION_MESSAGE);
                //Info6 dialog
                } else if (command == Info6Command) {
                    JOptionPane.showMessageDialog(frame,
                                    "Programmed by: \n Mike Kelley \n James Irey \n Reliable applications for the people \n Copyright 2004",
                                    "Programmers",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    
                //Info7 dialog
                } else if (command == Info7Command) {
                    JOptionPane.showMessageDialog(frame,
                                    "Trinity Senior High School\n Java 2\n Oracle Internet Academy\n Teachers:\n Mrs. Hoffman\n Mr. Reese",
                                    "Our Class",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    
                 //Second exit program statement This one WORKS!!!                  
                 } else if (command == exit2Command) {
                    System.exit(0);
                    
                }
                          	
            }
        });

        return create2ColPane(iconDesc + ":",
                              radioButtons,
                              showItButton);
    }
    
    
    //Creat administration tab
    
    private JPanel createAdministrationDialogBox() {
    
    	  
    	  final int numButtons = 6;
    	  JRadioButton[] radioButtons = new JRadioButton[numButtons];
    	  final ButtonGroup group = new ButtonGroup();
    	  
    	  JButton showItButton = null;
    	  
    	  final String Option13Command = "Option13";
    	  final String Option14Command = "Option14";
    	  final String Option15Command = "Option15";
    	  final String Option16Command = "Option16";
    	  final String Option17Command = "Option17";
    	  final String exit5Command = "exit5";
    	  
    	  //Radio buttons for user responses
    	  
    	  radioButtons[0] = new JRadioButton("Clear the vote totals");
    	  radioButtons[0].setActionCommand(Option13Command);
    	  
    	  radioButtons[1] = new JRadioButton("Check Comments");
    	  radioButtons[1].setActionCommand(Option14Command);
    	  
    	  radioButtons[2] = new JRadioButton("Check Emails");
    	  radioButtons[2].setActionCommand(Option15Command);
    	  
    	  radioButtons[3] = new JRadioButton("Check Questions");
    	  radioButtons[3].setActionCommand(Option16Command);
    	  
    	  radioButtons[4] = new JRadioButton("re-enable administrator options");
    	  radioButtons[4].setActionCommand(Option17Command);
    	  
    	  radioButtons[5] = new JRadioButton("Exit Program");
    	  radioButtons[5].setActionCommand(exit5Command);
    	
    	  
    	  for (int i = 0; i < numButtons; i++) {
    	  	group.add(radioButtons[i]);
    	  }
    	  radioButtons[0].setSelected(true);
    	  
    	  showItButton = new JButton("Choose This");
    	  showItButton.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent e) {
    	  		String command = group.getSelection().getActionCommand();
    	  		
    	  		//Option 13 Dialog
    	  		//Block user if password is entered incorrectly 5 times
    	  		if (command == Option13Command) {
    	  			if(tallyclearcount == 5)
                 		{
                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
    	  					"User blocked",
    	  					JOptionPane.WARNING_MESSAGE);
    	  				}
    	  	
    	  			
    	  			else
    	  			{
    	  			password = JOptionPane.showInputDialog(frame, "Please enter the password",
                 	"Password Protected", JOptionPane.QUESTION_MESSAGE);
                 	
    	  						if(password == null)
                				{
                				return;
                				}
                			//Needed password	
	                 		else if(password.equals ("admin"))
	                 			{   
	                 			  //Try-catch block if password matches
			                 		try 
			                 		{
				                		File option1_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option1tally.txt");
				                		File option2_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option2tally.txt");
				                		File option3_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option3tally.txt");		
				                		File option4_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option4tally.txt");
				                		File option5_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option5tally.txt");
				                		File option6_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option6tally.txt");
				                		File option7_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option7tally.txt");
				                		File option8_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option8tally.txt");
				                		
				                		DataOutputStream out;
				                		DataInputStream in;				
				                	
				                	
				                		if (option1_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option1_file));
					                		totaloption1tally = in.readInt();
					                		in.close();
					                		
					                		totaloption1tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option1_file));
					                		out.writeInt(totaloption1tally);
					                		out.close();
				                		}
				                
				                		if (option2_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option2_file));
					                		totaloption2tally = in.readInt();
					                		in.close();
					                		
					                		totaloption2tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option2_file));
					                		out.writeInt(totaloption2tally);
					                		out.close();
				                		}
				                		
				                		if (option3_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option3_file));
					                		totaloption3tally = in.readInt();
					                		in.close();
					                		
					                		totaloption3tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option3_file));
					                		out.writeInt(totaloption3tally);
					                		out.close();
				                		}
				                		
				                		if (option4_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option4_file));
					                		totaloption4tally = in.readInt();
					                		in.close();
					                		
					                		totaloption4tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option4_file));
					                		out.writeInt(totaloption4tally);
					                		out.close();
				                		}
				                		
				                		if (option5_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option5_file));
					                		totaloption5tally = in.readInt();
					                		in.close();
					                		
					                		totaloption5tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option5_file));
					                		out.writeInt(totaloption5tally);
					                		out.close();
				                		}
				                		
				                		if (option6_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option6_file));
					                		totaloption6tally = in.readInt();
					                		in.close();
					                		
					                		totaloption6tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option6_file));
					                		out.writeInt(totaloption6tally);
					                		out.close();
				                		}
				                		
				                		if (option7_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option7_file));
					                		totaloption7tally = in.readInt();
					                		in.close();
					                		
					                		totaloption7tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option7_file));
					                		out.writeInt(totaloption7tally);
					                		out.close();
				                		}
				                		
				                		if (option8_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(option8_file));
					                		totaloption8tally = in.readInt();
					                		in.close();
					                		
					                		totaloption8tally = 0;
					                		out = new DataOutputStream(new FileOutputStream(option8_file));
					                		out.writeInt(totaloption8tally);
					                		out.close();
				                		}
				                		
				                	
				                	}
				                	
			                		catch(IOException exc) {
			                    	System.out.println("Cannot read file.");
			                    	return ;
			                    }
			                 				
			                 		option1tally = 0;
			                 		option2tally = 0;
			                 		option3tally = 0;
			                 		option4tally = 0;
			                 		option5tally = 0;
			                 		option6tally = 0;
			                 		option7tally = 0;
			                 		option8tally = 0;
			                 			
			                 		JOptionPane.showMessageDialog(frame, "You cleared it out",
			    	  						"Message",
			    	  						JOptionPane.INFORMATION_MESSAGE);		
	                 			}
	                 		
	                 		//Warn if password is incorrect
	                 		else if (!password.equals ("admin"))
	                 		{
	                 			JOptionPane.showMessageDialog(frame, "Incorrect assword! \n Files not cleared! \n If you wish to clear the files, please try again with the correct password!",
	    	  					"Incorrect Password",
	    	  					JOptionPane.WARNING_MESSAGE);
	    	  					
	    	  					tallyclearcount++;
	    	  					
	    	  					if(tallyclearcount == 5)
                 				{
		                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
		    	  					"User blocked",
		    	  					JOptionPane.WARNING_MESSAGE);
    	  						}
	    	  					
	    	  				}	
	    	  				}	
	    	  			
                 				
    	  			
    
    	  			
    	  			//Option 14 Dialog
    	  			}else if (command == Option14Command) {
    	  				
    	  				 try {
                        
                     
				     File commentnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\commentnumber.txt");         
                      DataInputStream in;
                      		
				               	if (commentnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(commentnumber_file));
					                commentnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
    	  			
    	  				if(commentclearcount == 5)
                 		{
                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
    	  					"User blocked",
    	  					JOptionPane.WARNING_MESSAGE);
    	  				}
    	  			
    	  			else if(commentnumber == 1)
    	  			{
    	  				JOptionPane.showMessageDialog(frame, "There are no comments to check",
    	  					"No Comments",
    	  					JOptionPane.WARNING_MESSAGE);
    	  			}
    	  			
    	  			else
    	  			{
    	  			password = JOptionPane.showInputDialog(frame, "Please enter the password",
                 	"Password Protected", JOptionPane.QUESTION_MESSAGE);
                 	
    	  					if(password == null)
                				{
                				return;
                				}
    	  			
	                 		else if(password.equals ("admin"))
	                 			{
	                 				
	                 				//Try-catch
    	  						try {
                        
                     
				     File commentnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\commentnumber.txt");         
                      DataInputStream in;
                      		
				               	if (commentnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(commentnumber_file));
					                commentnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    try
                    {
                    
                    for(count = 1; count <= commentnumber - 1;count++)
                    {
                    	fr = new FileReader("comment"+count+".txt");
                    	br = new BufferedReader(fr);
                    	String s;
                    	comment = br.readLine();
                    	
                    	while((s = br.readLine()) != null)
                    		comment = comment + "\n" + s;
                    		
                    	//Comment number
                    	JOptionPane.showMessageDialog(frame, "Comment number " + count + " is: \n" + comment,
    	  				"Comment " + count,
    	  				JOptionPane.INFORMATION_MESSAGE);
    	  					if(count == commentnumber -1)
    	  						{
    	  						
    	  							
    	  							deletecomments = JOptionPane.showConfirmDialog(frame, "Do you wish to delete all the comments you have just reveiwed?",
                 					"Comment deleting", JOptionPane.YES_NO_OPTION);	
                 						if(deletecomments == 0)
                 						{
                 				//Yet another try-catch			
                              try {
                        		
					                	
                     
				     File commentnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\commentnumber.txt");         
                      
                      DataOutputStream out;
				   	  	
				   	  						commentnumber = 1;
					                		out = new DataOutputStream(new FileOutputStream(commentnumber_file));
					                		out.writeInt(commentnumber);
					                		out.close();
				                		
                       					 	
                        				}
					      
                      
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    								
                 						}
                 						else if(deletecomments == 1)
                 						{
                 							JOptionPane.showMessageDialog(frame, "Please review the comments and try again",
    	  									"OK, action will be halted",
    	  									JOptionPane.INFORMATION_MESSAGE);
    	  									
                 						}
                 						
    	  						}
                    }
                    }
                    
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    
                    
    	  		
    	  				}
    	  					else if (!password.equals ("admin"))
	                 		{
	                 			JOptionPane.showMessageDialog(frame, "Incorrect assword! \n Files not checked! \n If you wish to clear the files, please try again with the correct password!",
	    	  					"Incorrect Password",
	    	  					JOptionPane.WARNING_MESSAGE);
	    	  					
	    	  					commentclearcount++;
	    	  					
	    	  					if(commentclearcount == 5)
                 				{
		                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
		    	  					"User blocked",
		    	  					JOptionPane.WARNING_MESSAGE);
    	  						}
	    	  					
	    	  				}	
    	  				
    	  				}
    	  			
    	  	
    	  				//Option 15 Dialog
    	  				}else if (command == Option15Command) {
    	  					
    	  					 try {
                        
                     
				     File emailnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\emailnumber.txt");         
                      DataInputStream in;
                      		
				               	if (emailnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(emailnumber_file));
					                emailnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
    	  					
    	  							if(emailclearcount == 5)
                 		{
                 			//Tell user they are now blocked
                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
    	  					"User blocked",
    	  					JOptionPane.WARNING_MESSAGE);
    	  				}
    	  				
    	  				else if(emailnumber == 1)
    	  			{
    	  				JOptionPane.showMessageDialog(frame, "There are no emails to check",
    	  					"No Emails",
    	  					JOptionPane.WARNING_MESSAGE);
    	  			}
    	  				
    	  			else
    	  			{
    	  				
    	  		
    	  			password = JOptionPane.showInputDialog(frame, "Please enter the password",
                 	"Password Protected", JOptionPane.QUESTION_MESSAGE);
                 	
    	  					if(password == null)
                				{
                				return;
                				}
    	  					
	                 		else if(password.equals ("admin"))
	                 			{
    	  						try {
                        
                     
				     File emailnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\emailnumber.txt");         
                      DataInputStream in;
                      		
				               	if (emailnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(emailnumber_file));
					                emailnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    try
                    {
                    
                    for(count = 1; count <= emailnumber - 1;count++)
                    {
                    	fr = new FileReader("email"+count+".txt");
                    	br = new BufferedReader(fr);
                    	email = br.readLine();
                    	JOptionPane.showMessageDialog(frame, "Email number " + count + " is: \n" + email,
    	  				"Email " + count,
    	  				JOptionPane.INFORMATION_MESSAGE);
    	  					if(count == emailnumber -1)
    	  						{
    	  						
    	  							deleteemails = JOptionPane.showConfirmDialog(frame, "Do you wish to delete all the emails you have just reveiwed?",
                 					"Email deleting", JOptionPane.YES_NO_OPTION);	
                 						if(deleteemails == 0)
                 						{
                 							
                              try {
                        		
					                	
                     
				     File emailnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\emailnumber.txt");         
                      
                      DataOutputStream out;
				   	  	
				   	  						emailnumber = 1;
					                		out = new DataOutputStream(new FileOutputStream(emailnumber_file));
					                		out.writeInt(emailnumber);
					                		out.close();
				                		
                       					 	
                        				}
					      
                      
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    								
                 						}
                 						else if(deleteemails == 1)
                 						{
                 							JOptionPane.showMessageDialog(frame, "Please review the emails and try again",
    	  									"OK, action will be halted",
    	  									JOptionPane.INFORMATION_MESSAGE);
    	  									
                 						}
                 							
    	  						}
                    }
                    }
                    
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    
                    
    	  		
    	  				}
    	  					else if (!password.equals ("admin"))
	                 		{
	                 			JOptionPane.showMessageDialog(frame, "Incorrect assword! \n Files not checked! \n If you wish to clear the files, please try again with the correct password!",
	    	  					"Incorrect Password",
	    	  					JOptionPane.WARNING_MESSAGE);
	    	  					
	    	  					emailclearcount++;
	    	  					
	    	  					if(emailclearcount == 5)
                 				{
		                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
		    	  					"User blocked",
		    	  					JOptionPane.WARNING_MESSAGE);
    	  						}
	    	  					
	    	  				}	
    	  				
    	  				}
    	  			
    	  					
    	  				//Option 16 Dialog
    	  				}else if (command == Option17Command) {
    	  					
                 			if(reenablepass == null)
                				{
                				return;
                				}
                 		
                 			else if(reenablepass.equals ("dog"))
		                 	{
		                 			tallyclearcount = 0;
		                 			questionclearcount = 0;
		                 			emailclearcount = 0;
		                 			commentclearcount = 0;
		                 			JOptionPane.showMessageDialog(frame, "Administrator options are re-enabled",
	    	  						"Success",
	    	  						JOptionPane.INFORMATION_MESSAGE);
		                 	}
                 		
                 		//If true, give admin options back
                 			else if(!reenablepass.equals ("dog"))
                 			{
	    	  					JOptionPane.showMessageDialog(frame, "Sorry, incorrect re-enabling password",
	    	  					"Opps, please try again!",
	    	  					JOptionPane.WARNING_MESSAGE);
    	  					}
    	  					
    	  					//Option 17 Dialog
    	  				}else if (command == Option16Command) {
    	  					
    	  					 try {
                        
                     
				     File questionnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\questionnumber.txt");         
                      DataInputStream in;
                      		
				               	if (questionnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(questionnumber_file));
					                questionnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
    	  					
    	  						if(questionclearcount == 5)
                 		{
                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
    	  					"User blocked",
    	  					JOptionPane.WARNING_MESSAGE);
    	  				}
    	  			else if(questionnumber == 1)
    	  			{
    	  				JOptionPane.showMessageDialog(frame, "There are no questions to check",
    	  					"No Questions",
    	  					JOptionPane.WARNING_MESSAGE);
    	  			}	
    	  			
    	  			else
    	  			{
    	  			password = JOptionPane.showInputDialog(frame, "Please enter the password",
                 	"Password Protected", JOptionPane.QUESTION_MESSAGE);
                 	
    	  					if(password == null)
                				{
                				return;
                				}
                				
    	  					//If correct password
	                 		if(password.equals ("admin"))
	                 			{
    	  						try {
                        
                     
				     File questionnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\questionnumber.txt");         
                      DataInputStream in;
                      		
				               	if (questionnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(questionnumber_file));
					                questionnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    try
                    {
                    
                    for(count = 1; count <= questionnumber - 1;count++)
                    {
                    	fr = new FileReader("question"+count+".txt");
                    	br = new BufferedReader(fr);
                    	question = br.readLine();
                    	JOptionPane.showMessageDialog(frame, "Question number " + count + " is: \n" + question,
    	  				"Question " + count,
    	  				JOptionPane.INFORMATION_MESSAGE);
    	  					if(count == questionnumber -1)
    	  						{
    	  						
    	  							
    	  							
    	  							deletequestions = JOptionPane.showConfirmDialog(frame, "Do you wish to delete all the questions you have just reveiwed?",
                 					"Question deleting", JOptionPane.YES_NO_OPTION );	
                 						if(deletequestions == 0)
                 						{
                 							
                              try {
                        		
					                	
                     
				     File questionnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\questionnumber.txt");         
                      
                      DataOutputStream out;
				   	  	
				   	  						questionnumber = 1;
					                		out = new DataOutputStream(new FileOutputStream(questionnumber_file));
					                		out.writeInt(questionnumber);
					                		out.close();
				                		
                       					 	
                        				}
					      
                      
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    								
                 						}
                 						else if(deletequestions == 1)
                 						{
                 							JOptionPane.showMessageDialog(frame, "Please review the questions and try again",
    	  									"OK, action will be halted",
    	  									JOptionPane.INFORMATION_MESSAGE);
    	  									
                 						}
                 				
                 				
    	  						}
                    }
                    }
                    
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    
                    
    	  		
    	  				}
    	  					else if (!password.equals ("admin"))
	                 		{
	                 			JOptionPane.showMessageDialog(frame, "Incorrect assword! \n Files not checked! \n If you wish to clear the files, please try again with the correct password!",
	    	  					"Incorrect Password",
	    	  					JOptionPane.WARNING_MESSAGE);
	    	  					
	    	  					questionclearcount++;
	    	  					
	    	  					if(questionclearcount == 5)
                 				{
		                 			JOptionPane.showMessageDialog(frame, "You are now blocked from using this option",
		    	  					"User blocked",
		    	  					JOptionPane.WARNING_MESSAGE);
    	  						}
	    	  					
	    	  				}	
    	  				
    	  				}
    	  				
    	  				//Option 18 Dialog	
    	  				} 
    	  				else if (command == exit5Command) {
    	  					System.exit(0);
    	  					
    	  				}
    	  				
    	  				
    	  			}
    	  			
    	  		});
    	  	
    	  	return createPane(adminDialogDesc + ":",
    	  							radioButtons,
    	  							showItButton);
    	  							
    	  						}
    	  					
    	  			
          //Create comment tab
          private JPanel createCommentDialogBox() {
         
          	  final int numButtons = 4;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String Option9Command = "Option9";
        final String Option10Command = "Option10";
        final String Option11Command = "Option11";
        final String Option12Command = "Option12";
        final String exit4Command = "exit4";

        radioButtons[0] = new JRadioButton("Submit a question for review for a future survey.");
        radioButtons[0].setActionCommand(Option9Command);

        radioButtons[1] = new JRadioButton("Submit a comment about this survey");
        radioButtons[1].setActionCommand(Option10Command);

       

        radioButtons[2] = new JRadioButton("Write authors of this survey");
        radioButtons[2].setActionCommand(Option11Command);
        
        radioButtons[3] = new JRadioButton("Exit Program");
        radioButtons[3].setActionCommand(exit4Command);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Choose This");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();


               //Option9 dialog
                if (command == Option9Command) {
                	try {
                        
                     
				     File questionnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\questionnumber.txt");         
                      DataInputStream in;
                      		
				               	if (questionnumber_file.exists())
				               	{
				                	
					               	in = new DataInputStream(new FileInputStream(questionnumber_file));
					                questionnumber = in.readInt();
					                in.close();
					            }
					    }
					    
					    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
					    //Check to see storage limit
					    if(questionnumber == 21)
					    {
					    JOptionPane.showMessageDialog(frame, "Storage amount full! \n Please contact your administrator to continue",
    	  				"Storage Full",
    	  				JOptionPane.INFORMATION_MESSAGE);
					    }
					    else
					    {
					  
                	do
                	{
                	
                	
                 question = JOptionPane.showInputDialog(
                 	frame, "Please type in your question",
                 	"Question submitting",
                 	JOptionPane.QUESTION_MESSAGE);
                
                		if(question == null)
                				{
                				return;
                				}
                
                		if(question.equals (""))
                		{
                			JOptionPane.showMessageDialog(
                 	frame, "Please type in a question ",
                 	"Error, no question was inputted",
                 	JOptionPane.WARNING_MESSAGE);
                		}
                
                		else
                		{
                
                              try {
                        		
					                	
                     
				     File questionnumber_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\questionnumber.txt");         
                      
                      DataOutputStream out;
				   	  DataInputStream in;	
				   	  			
				                	
				                	
				                		if (questionnumber_file.exists())
				                		{
				                	
					                		in = new DataInputStream(new FileInputStream(questionnumber_file));
					                		questionnumber = in.readInt();
					                		in.close();
					                		fw = new FileWriter("C:\\WINDOWS\\Desktop\\mikes\\question" + questionnumber + ".txt");
                    						fw.write(question);
                       					 	fw.close();
                       					 	questionnumber++;
					                		out = new DataOutputStream(new FileOutputStream(questionnumber_file));
					                		out.writeInt(questionnumber);
					                		out.close();
				                		}
				                		
				                		else
				                		{
				                			fw = new FileWriter("C:\\WINDOWS\\Desktop\\mikes\\question" + questionnumber + ".txt");
                    						fw.write(question);
                       					 	fw.close();
                       					 	questionnumber++;
					                		out = new DataOutputStream(new FileOutputStream(questionnumber_file));
					                		out.writeInt(questionnumber);
					                		out.close();
				                		}      
                       					 	
                        				}
					      
                      
                    catch(IOException exc) {
                    	System.out.println("Cannot open file.");
                    	return ;
                    }
                    }
                    }while(question.equals (""));
                    }
                    
                    }
                
                	

                //Option10 dialog
                else if (command == Option10Command) {
                
                 JFrame frame2 = new JFrame("Comment");
                
                Container contentPane = frame2.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(new Comment(frame2));

        frame2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                return;
            }
        });

              frame2.pack();
        frame2.setVisible(true);
        frame.setVisible(false);
          		
                	
                		
                }
                
                
                 
            	//Option11 dialog
                 else if (command == Option11Command) {
                	JOptionPane.showMessageDialog(
                		frame, "MKELLEYJR@msn.com or Ghostman04@hotmail.com",
                		"Message",
                		JOptionPane.INFORMATION_MESSAGE);
          
           				
           				
           		//Third Exit Statements THIS ONE WORKS TOO!!!!		
                
                
                } else if (command == exit4Command) {
                    System.exit(0);
                                 
            }
            }   
           
           
           
           
        });
          	return createPane(commentDialogDesc + ":",
                              radioButtons,
                              showItButton); 
     	
     	} 
     	
     	//Create another dialog box
    private JPanel createOptionDialogBox() {
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;
          
          //Give options names
          
        final String Option5Command = "Option5";
        final String Option6Command = "Option6";
        final String Option7Command = "Option7";
        final String Option8Command = "Option8";
        final String exit3Command = "exit3";
        
        //More exciting radio buttons

        radioButtons[0] = new JRadioButton("Yes, if the software is used for educational reasons.");
        radioButtons[0].setActionCommand(Option5Command);

        radioButtons[1] = new JRadioButton("Yes, software is too expensive now and days.");
        radioButtons[1].setActionCommand(Option6Command);

        radioButtons[2] = new JRadioButton("No, it is considered stealing.");                                  
        radioButtons[2].setActionCommand(Option7Command);

        radioButtons[3] = new JRadioButton("No, there are other free alternatives.");
        radioButtons[3].setActionCommand(Option8Command);
        
        radioButtons[4] = new JRadioButton("Exit Program");
        radioButtons[4].setActionCommand(exit3Command);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Choose This");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();
               
               //Option5 dialog
                if (command == Option5Command) {
                 JOptionPane.showMessageDialog(
                 	frame, "Thank you for your vote",
                 	"Message",
                 	JOptionPane.INFORMATION_MESSAGE);
                 	option5tally = option5tally + 1;
                 	
                 	try {
                			File option5_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option5tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option5_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option5_file));
                					totaloption5tally = in.readInt();
                					in.close();
                					totaloption5tally++;
                					out = new DataOutputStream(new FileOutputStream(option5_file));
                					out.writeInt(totaloption5tally);
                					out.close();
                			}
                			else
                			{
                			
                				totaloption5tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option5_file));
                				out.writeInt(totaloption5tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption5tally);
                 	

                //Option6 dialog
                } else if (command == Option6Command) {
                	JOptionPane.showMessageDialog(
                		frame, "Thank you for your vote",
                		"Message",
                		JOptionPane.INFORMATION_MESSAGE);
                		option6tally = option6tally + 1;
                try {
                			File option6_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option6tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option6_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option6_file));
                					totaloption6tally = in.readInt();
                					in.close();
                					totaloption6tally++;
                					out = new DataOutputStream(new FileOutputStream(option6_file));
                					out.writeInt(totaloption6tally);
                					out.close();
                				}
                			
                			else
                			{
                			
                				totaloption6tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option6_file));
                				out.writeInt(totaloption6tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption6tally);
                
                 
            	//Option7 dialog
                } else if (command == Option7Command) {
                	JOptionPane.showMessageDialog(
                		frame, "Thank you for your vote",
                		"Message",
                		JOptionPane.INFORMATION_MESSAGE);
                		option7tally = option7tally + 1;
                 try {
                			File option7_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option7tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option7_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option7_file));
                					totaloption7tally = in.readInt();
                					in.close();
                					totaloption7tally++;
                					out = new DataOutputStream(new FileOutputStream(option7_file));
                					out.writeInt(totaloption7tally);
                					out.close();
                				}
                			
                			else {
                			
                				totaloption7tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option7_file));
                				out.writeInt(totaloption7tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption7tally);
                 
                //Option8 dialog
                } else if (command == Option8Command) {
           			JOptionPane.showMessageDialog(
           				frame, "Thank you for your vote",
           				"Message",
           				JOptionPane.INFORMATION_MESSAGE);
           				option8tally = option8tally + 1;
           				
           				try {
                			File option8_file = new File ("C:\\WINDOWS\\Desktop\\mikes\\option8tally.txt");
                			
                			DataOutputStream out;
                			DataInputStream in;
                		
                			if(option8_file.exists())
                				{
                					in = new DataInputStream(new FileInputStream(option8_file));
                					totaloption8tally = in.readInt();
                					in.close();
                					totaloption8tally++;
                					out = new DataOutputStream(new FileOutputStream(option8_file));
                					out.writeInt(totaloption8tally);
                					out.close();
                				}
                			
                			else
                			{
                			
                				totaloption8tally = 1;
                				out = new DataOutputStream(new FileOutputStream(option8_file));
                				out.writeInt(totaloption8tally);
                				out.close();
                				}
                		}
                			
                		catch(IOException exc) {
                    	System.out.println("Cannot read file.");
                    	return ;
                    }
                		System.out.println(totaloption8tally);
           		//Third Exit Statements THIS ONE WORKS TOO!!!!	
           			
                } else if (command == exit3Command) {
                    System.exit(0);
                                 
            }
            }   
           
           
        });

        return createPane(moreDialogDesc + ":",
                          radioButtons,
                          showItButton);
                          
    } 
    


           //Dealing with menu 	
    public static void main(String[] args)
    throws java.io.IOException {   //I ADDED THIS..needed for io
       
        
      
        
        
        JFrame frame = new JFrame("WNN Survey"); //Frame title

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(new WNNProgram(frame));

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                
                
                
                //Exiting the application
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}


//********** END OF MAIN CLASS **********



