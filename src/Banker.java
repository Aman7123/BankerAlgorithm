package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Banker {
	/**
	 * Variables for GUI
	 */
	private JFrame frmBankerApp;
	private JTextField[] allocationArray = new JTextField[15];
	private JTextField[] maxArray = new JTextField[15];
	private JTextField[] availableArray = new JTextField[3];
	private JLabel outText = new JLabel("");
	/**
	 * Variables for Banker
	 */
	private int need[][],allocate[][],max[][],avail[][];
	private int processes = 5;
	private int resources = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banker window = new Banker();
					window.frmBankerApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Banker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankerApp = new JFrame();
		frmBankerApp.setTitle("Banker App");
		frmBankerApp.setBounds(100, 100, 450, 300);
		frmBankerApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBankerApp.getContentPane().setLayout(null);
		
		/**
		 * Add Action Button.
		 */
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//initializing arrays
				need = new int[processes][resources];
				max = new int[processes][resources];
				allocate = new int[processes][resources];
				avail = new int[1][resources];
				//get data from form
				createTest();
				
				boolean done[] = new boolean[5];
				String processText = "";
				
				int j = 0; //initial processes complete
				while(j < 5) {  //loop until all process allocated
					boolean allocated = false;
					for(int i  = 0; i < 5; i++) {
						if(!done[i] && validCheck(i)) {  //trying to allocate
							for(int k = 0; k < 3; k++) {
								avail[0][k] = avail[0][k] - need[i][k] + max[i][k];
							}
							processText = processText + i + " ";
							allocated = done[i] = true;
							j++;
						}
					}
					if(!allocated) break;  //if no allocation
				}
				if(j == 5) {  //if all processes are allocated
					String outputText = "Safely allocated processes in-order: " + processText;
					outText.setText(outputText);
				}
				else {
					outText.setText("All proceess can't be allocated safely!");
				}
			}
		});
		btnRun.setBounds(335, 227, 89, 23);
		frmBankerApp.getContentPane().add(btnRun);
		
		/**
		 * Place output field on GUI
		 */
		outText.setBounds(10, 182, 350, 14);
		frmBankerApp.getContentPane().add(outText);
		
		/**
		 * Create Lables for GUI.
		 */
		JLabel lblAllocation = new JLabel("Allocation");
		lblAllocation.setBounds(60, 11, 60, 14);
		frmBankerApp.getContentPane().add(lblAllocation);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setBounds(204, 11, 46, 14);
		frmBankerApp.getContentPane().add(lblMax);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(340, 11, 60, 14);
		frmBankerApp.getContentPane().add(lblAvailable);
		
		JLabel lblP_0 = new JLabel("p0");
		lblP_0.setBounds(10, 36, 25, 14);
		frmBankerApp.getContentPane().add(lblP_0);
		
		JLabel lblP_1 = new JLabel("p1");
		lblP_1.setBounds(10, 61, 25, 14);
		frmBankerApp.getContentPane().add(lblP_1);
		
		JLabel lblP_2 = new JLabel("p2");
		lblP_2.setBounds(10, 86, 25, 14);
		frmBankerApp.getContentPane().add(lblP_2);
		
		JLabel lblP_3 = new JLabel("p3");
		lblP_3.setBounds(10, 111, 25, 14);
		frmBankerApp.getContentPane().add(lblP_3);
		
		JLabel lblP_4 = new JLabel("p4");
		lblP_4.setBounds(10, 136, 25, 14);
		frmBankerApp.getContentPane().add(lblP_4);
		
		/**
		 * Start Allocation Matrix.
		 */
		allocationArray[0] = new JTextField();
		allocationArray[0].setBounds(34, 33, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[0]);
		allocationArray[0].setColumns(10);
		
		allocationArray[1] = new JTextField();
		allocationArray[1].setColumns(10);
		allocationArray[1].setBounds(70, 33, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[1]);
		
		allocationArray[2] = new JTextField();
		allocationArray[2].setColumns(10);
		allocationArray[2].setBounds(105, 33, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[2]);
		
		allocationArray[3] = new JTextField();
		allocationArray[3].setColumns(10);
		allocationArray[3].setBounds(34, 61, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[3]);
		
		allocationArray[4] = new JTextField();
		allocationArray[4].setColumns(10);
		allocationArray[4].setBounds(70, 61, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[4]);
		
		allocationArray[5] = new JTextField();
		allocationArray[5].setColumns(10);
		allocationArray[5].setBounds(105, 61, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[5]);
		
		allocationArray[6] = new JTextField();
		allocationArray[6].setColumns(10);
		allocationArray[6].setBounds(34, 86, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[6]);
		
		allocationArray[7] = new JTextField();
		allocationArray[7].setColumns(10);
		allocationArray[7].setBounds(70, 86, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[7]);
		
		allocationArray[8] = new JTextField();
		allocationArray[8].setColumns(10);
		allocationArray[8].setBounds(105, 86, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[8]);
		
		allocationArray[9] = new JTextField();
		allocationArray[9].setColumns(10);
		allocationArray[9].setBounds(34, 111, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[9]);
		
		allocationArray[10] = new JTextField();
		allocationArray[10].setColumns(10);
		allocationArray[10].setBounds(70, 111, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[10]);
		
		allocationArray[11] = new JTextField();
		allocationArray[11].setColumns(10);
		allocationArray[11].setBounds(105, 111, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[11]);
		
		allocationArray[12] = new JTextField();
		allocationArray[12].setColumns(10);
		allocationArray[12].setBounds(34, 136, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[12]);
		
		allocationArray[13] = new JTextField();
		allocationArray[13].setColumns(10);
		allocationArray[13].setBounds(70, 136, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[13]);
		
		allocationArray[14] = new JTextField();
		allocationArray[14].setColumns(10);
		allocationArray[14].setBounds(105, 136, 25, 20);
		frmBankerApp.getContentPane().add(allocationArray[14]);
		
		/**
		 * Start Max Matrix.
		 */
		maxArray[0] = new JTextField();
		maxArray[0].setColumns(10);
		maxArray[0].setBounds(165, 33, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[0]);
		
		maxArray[1] = new JTextField();
		maxArray[1].setColumns(10);
		maxArray[1].setBounds(201, 33, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[1]);
		
		maxArray[2] = new JTextField();
		maxArray[2].setColumns(10);
		maxArray[2].setBounds(236, 33, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[2]);
		
		maxArray[3] = new JTextField();
		maxArray[3].setColumns(10);
		maxArray[3].setBounds(165, 61, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[3]);
		
		maxArray[4] = new JTextField();
		maxArray[4].setColumns(10);
		maxArray[4].setBounds(201, 61, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[4]);
		
		maxArray[5] = new JTextField();
		maxArray[5].setColumns(10);
		maxArray[5].setBounds(236, 61, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[5]);
		
		maxArray[6] = new JTextField();
		maxArray[6].setColumns(10);
		maxArray[6].setBounds(165, 86, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[6]);
		
		maxArray[7] = new JTextField();
		maxArray[7].setColumns(10);
		maxArray[7].setBounds(201, 86, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[7]);
		
		maxArray[8] = new JTextField();
		maxArray[8].setColumns(10);
		maxArray[8].setBounds(236, 86, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[8]);
		
		maxArray[9] = new JTextField();
		maxArray[9].setColumns(10);
		maxArray[9].setBounds(165, 111, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[9]);
		
		maxArray[10] = new JTextField();
		maxArray[10].setColumns(10);
		maxArray[10].setBounds(201, 111, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[10]);
		
		maxArray[11] = new JTextField();
		maxArray[11].setColumns(10);
		maxArray[11].setBounds(236, 111, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[11]);
		
		maxArray[12] = new JTextField();
		maxArray[12].setColumns(10);
		maxArray[12].setBounds(165, 136, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[12]);
		
		maxArray[13] = new JTextField();
		maxArray[13].setColumns(10);
		maxArray[13].setBounds(201, 136, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[13]);
		
		maxArray[14] = new JTextField();
		maxArray[14].setColumns(10);
		maxArray[14].setBounds(236, 136, 25, 20);
		frmBankerApp.getContentPane().add(maxArray[14]);
		
		/**
		 * Start Available Matrix.
		 */
		availableArray[0] = new JTextField();
		availableArray[0].setColumns(10);
		availableArray[0].setBounds(311, 33, 25, 20);
		frmBankerApp.getContentPane().add(availableArray[0]);
		
		availableArray[1] = new JTextField();
		availableArray[1].setColumns(10);
		availableArray[1].setBounds(347, 33, 25, 20);
		frmBankerApp.getContentPane().add(availableArray[1]);
		
		availableArray[2] = new JTextField();
		availableArray[2].setColumns(10);
		availableArray[2].setBounds(382, 33, 25, 20);
		frmBankerApp.getContentPane().add(availableArray[2]);
		
		JLabel lblCreatedByAaron = new JLabel("Created By Aaron Renner");
		lblCreatedByAaron.setBounds(10, 236, 178, 14);
		frmBankerApp.getContentPane().add(lblCreatedByAaron);
	}
	/**
	 * This method gathers data from the visual form such as Allocation, Max, and Available.
	 * This method also calculates the need based on the Max.
	 * 
	 */
	protected void createTest() {
		/*The code below handles Allocation and Max pull from form.*/
		int temp = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				int allocText = Integer.parseInt(allocationArray[temp].getText());
				int maxText = Integer.parseInt(maxArray[temp].getText());
				 allocate[i][j] = allocText;
				 max[i][j] = maxText;
				temp++;
			}
		}
		/*This code below handles pulling available from from.*/
		int temp2 = 0;
		for(int j = 0; j < 3; j++) {
			int availableText = Integer.parseInt(availableArray[temp2].getText());
            avail[0][j] = availableText;
            temp2++;
		}
		/*This code handles calculation of Need.*/
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){  //calculating need matrix
                need[i][j] = max[i][j] - allocate[i][j];
            }
        }
	}
	
	/**
	 * This is our live math method. This method checks if the requested need is available.
	 * 
	 * @param i The row of matrix, the "process (p0, p1, p2, p3, p4)".
	 * @return True if process can run, false if not.
	 */
	protected boolean validCheck(int i) {
		for(int j = 0; j < 3; j++) {
			if(avail[0][j] < need[i][j]) {
				return false;
			}
		}
		return true;
	}
}
