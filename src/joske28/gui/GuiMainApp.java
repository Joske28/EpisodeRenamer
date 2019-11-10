package joske28.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import joske28.renamer.Renamer;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class GuiMainApp {

	private JFrame frmRenamer;
	private JTextField textFieldFileExtension;
	private JTextField textFieldSeason;
	private final static JFileChooser fileChooser = new JFileChooser();
	private static Renamer renamer;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//setup the file chooser
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					//setup the main app
					GuiMainApp window = new GuiMainApp();
					window.frmRenamer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiMainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmRenamer = new JFrame();
		frmRenamer.setTitle("Episode Renamer");
		frmRenamer.setBounds(100, 100, 610, 493);
		frmRenamer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 53, 53, 53, 53, 53, 53, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		frmRenamer.getContentPane().setLayout(gridBagLayout);
		
		//center the jframe
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmRenamer.setLocation(dim.width/2-frmRenamer.getSize().width/2, dim.height/2-frmRenamer.getSize().height/2);
		
		JLabel lblTitle = new JLabel("Bulk Episode Renamer By Joske");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 8;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		frmRenamer.getContentPane().add(lblTitle, gbc_lblTitle);
		
		JScrollPane scrollPaneEpisodeList = new JScrollPane();
		scrollPaneEpisodeList.setToolTipText("List of episodes");
		GridBagConstraints gbc_scrollPaneEpisodeList = new GridBagConstraints();
		gbc_scrollPaneEpisodeList.gridwidth = 4;
		gbc_scrollPaneEpisodeList.gridheight = 6;
		gbc_scrollPaneEpisodeList.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneEpisodeList.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEpisodeList.gridx = 1;
		gbc_scrollPaneEpisodeList.gridy = 4;
		frmRenamer.getContentPane().add(scrollPaneEpisodeList, gbc_scrollPaneEpisodeList);
		
		JList<String> listEpisodes = new JList<String>();
		listEpisodes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listEpisodes.setToolTipText("List of episodes");
		listEpisodes.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"No episodes found"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		listEpisodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneEpisodeList.setViewportView(listEpisodes);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setToolTipText("Series Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 5;
		gbc_lblName.gridy = 4;
		frmRenamer.getContentPane().add(lblName, gbc_lblName);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.fill = GridBagConstraints.BOTH;
		gbc_textFieldName.gridx = 6;
		gbc_textFieldName.gridy = 4;
		frmRenamer.getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnSelectPath = new JButton("Select Path");
		btnSelectPath.setToolTipText("Select the folder to bulk rename episodes in");
		
		btnSelectPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSelectPath = new GridBagConstraints();
		gbc_btnSelectPath.fill = GridBagConstraints.BOTH;
		gbc_btnSelectPath.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectPath.gridx = 5;
		gbc_btnSelectPath.gridy = 5;
		frmRenamer.getContentPane().add(btnSelectPath, gbc_btnSelectPath);
		
		JLabel labelPath = new JLabel("");
		labelPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_labelPath = new GridBagConstraints();
		gbc_labelPath.gridwidth = 2;
		gbc_labelPath.insets = new Insets(0, 0, 5, 0);
		gbc_labelPath.gridx = 6;
		gbc_labelPath.gridy = 5;
		frmRenamer.getContentPane().add(labelPath, gbc_labelPath);
		
		//action for path button
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = fileChooser.showOpenDialog(frmRenamer);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					//directory was opened
					labelPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
					
				}
				
			}
		});
		
		JLabel lblFileExtension = new JLabel("File Extension:");
		lblFileExtension.setToolTipText(".mp4, .mkv");
		lblFileExtension.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFileExtension = new GridBagConstraints();
		gbc_lblFileExtension.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileExtension.gridx = 5;
		gbc_lblFileExtension.gridy = 6;
		frmRenamer.getContentPane().add(lblFileExtension, gbc_lblFileExtension);
		
		textFieldFileExtension = new JTextField();
		textFieldFileExtension.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFileExtension.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldFileExtension.setToolTipText(".mp4, .mkv");
		GridBagConstraints gbc_textFieldFileExtension = new GridBagConstraints();
		gbc_textFieldFileExtension.gridwidth = 2;
		gbc_textFieldFileExtension.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFileExtension.fill = GridBagConstraints.BOTH;
		gbc_textFieldFileExtension.gridx = 6;
		gbc_textFieldFileExtension.gridy = 6;
		frmRenamer.getContentPane().add(textFieldFileExtension, gbc_textFieldFileExtension);
		textFieldFileExtension.setColumns(10);
		
		JLabel lblSeason = new JLabel("Season:");
		lblSeason.setToolTipText("Season name/Number, e.g, 1");
		lblSeason.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSeason = new GridBagConstraints();
		gbc_lblSeason.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeason.gridx = 5;
		gbc_lblSeason.gridy = 7;
		frmRenamer.getContentPane().add(lblSeason, gbc_lblSeason);
		
		textFieldSeason = new JTextField();
		textFieldSeason.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSeason.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSeason.setToolTipText("Season name/Number, e.g, 1");
		GridBagConstraints gbc_textFieldSeason = new GridBagConstraints();
		gbc_textFieldSeason.gridwidth = 2;
		gbc_textFieldSeason.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSeason.fill = GridBagConstraints.BOTH;
		gbc_textFieldSeason.gridx = 6;
		gbc_textFieldSeason.gridy = 7;
		frmRenamer.getContentPane().add(textFieldSeason, gbc_textFieldSeason);
		textFieldSeason.setColumns(10);
		
		JLabel lblEpisodeNumber = new JLabel("Episode Number:");
		lblEpisodeNumber.setToolTipText("The starting episode number. e.g, 1");
		lblEpisodeNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEpisodeNumber = new GridBagConstraints();
		gbc_lblEpisodeNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblEpisodeNumber.gridx = 5;
		gbc_lblEpisodeNumber.gridy = 8;
		frmRenamer.getContentPane().add(lblEpisodeNumber, gbc_lblEpisodeNumber);
		
		JSpinner spinnerEpisodeNumber = new JSpinner();
		spinnerEpisodeNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerEpisodeNumber.setToolTipText("The episode starting number");
		spinnerEpisodeNumber.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinnerEpisodeNumber = new GridBagConstraints();
		gbc_spinnerEpisodeNumber.fill = GridBagConstraints.BOTH;
		gbc_spinnerEpisodeNumber.gridwidth = 2;
		gbc_spinnerEpisodeNumber.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerEpisodeNumber.gridx = 6;
		gbc_spinnerEpisodeNumber.gridy = 8;
		frmRenamer.getContentPane().add(spinnerEpisodeNumber, gbc_spinnerEpisodeNumber);
		
		JButton btnDisplayEpisodes = new JButton("Display Episodes");
		btnDisplayEpisodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if file extension is valid
				if (textFieldFileExtension.getText().matches("^\\.[A-Za-z\\d]+$")) {
					//check if path is valid
					if (labelPath.getText().length() > 0) {
						//extension and path is valid
						try {
						renamer = new Renamer(labelPath.getText(), textFieldFileExtension.getText());
						}
						catch(Exception exception) {
							JOptionPane.showMessageDialog(null, "Check to make sure that the file is not protected", "Error - Failed to get all files", JOptionPane.ERROR_MESSAGE);
						}
						//make sure that there is episodes in the directory
						if (renamer.getEpisodes().size() > 0) {
							//if there is, display them in the list
							DefaultListModel<String> model = new DefaultListModel<String>();
							renamer.getEpisodes().forEach(episode -> model.addElement(episode.substring(episode.lastIndexOf(File.separator)+1)));
							listEpisodes.setModel(model);
						}else {
							//display no episodes
							DefaultListModel<String> model = new DefaultListModel<String>();
							model.addElement("No episodes found");
							listEpisodes.setModel(model);
						}
						
						
					}else {
						//path is invalid
						JOptionPane.showMessageDialog(null, "The path is invalid, please select a valid directory", "Error - Invalid path", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					//extension is invalid
					JOptionPane.showMessageDialog(null, "The extension is invalid, please input a valid extension. E.g., .mp4", "Error - Invalid extension", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDisplayEpisodes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnDisplayEpisodes = new GridBagConstraints();
		gbc_btnDisplayEpisodes.fill = GridBagConstraints.BOTH;
		gbc_btnDisplayEpisodes.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayEpisodes.gridx = 5;
		gbc_btnDisplayEpisodes.gridy = 9;
		frmRenamer.getContentPane().add(btnDisplayEpisodes, gbc_btnDisplayEpisodes);
		
		JButton btnRename = new JButton("Rename!");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String validFileNameRegex = "^[\\w-\\. ]+$";
				//check if file extension is valid
				if (textFieldFileExtension.getText().matches("^\\.[A-Za-z\\d]+$")) {
					//check if path is valid
					if (labelPath.getText().length() > 0) {
						//check if name is valid
						if (textFieldName.getText().matches(validFileNameRegex)) {
							//check if season is valid
							if (textFieldSeason.getText().matches(validFileNameRegex)) {
								//all is valid, shouldn't need to check spinner as swing makes sure it is valid
								//create a new renamer just in case display episodes was not clicked - the old one will get garbage collected
								try {
								renamer = new Renamer(labelPath.getText(), textFieldFileExtension.getText(), textFieldName.getText(), textFieldSeason.getText(), (int) spinnerEpisodeNumber.getValue());
								renamer.renameAll();
								JOptionPane.showMessageDialog(null, "Successfully renamed " + renamer.getEpisodes().size() + " episodes", "Success", JOptionPane.INFORMATION_MESSAGE);
								} catch(Exception exception) {
									JOptionPane.showMessageDialog(null, "Check to make sure that the file is not protected", "Error - Failed to rename files", JOptionPane.ERROR_MESSAGE);
								}
								
							}else {
								//season is invalid
								JOptionPane.showMessageDialog(null, "The season is invalid, please input a valid season. E.g., Two, 2, Second", "Error - Invalid season", JOptionPane.ERROR_MESSAGE);
							}
							
						}else {
							//name is invalid
							JOptionPane.showMessageDialog(null, "The name is invalid, please input a valid name. E.g., Fancy Series Name", "Error - Invalid name", JOptionPane.ERROR_MESSAGE);
						}
						
					}else {
						//path is invalid
						JOptionPane.showMessageDialog(null, "The path is invalid, please select a valid directory", "Error - Invalid path", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					//extension is invalid
					JOptionPane.showMessageDialog(null, "The extension is invalid, please input a valid extension. E.g., .mp4", "Error - Invalid extension", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRename = new GridBagConstraints();
		gbc_btnRename.fill = GridBagConstraints.BOTH;
		gbc_btnRename.insets = new Insets(0, 0, 5, 5);
		gbc_btnRename.gridx = 6;
		gbc_btnRename.gridy = 9;
		frmRenamer.getContentPane().add(btnRename, gbc_btnRename);
	}
}
