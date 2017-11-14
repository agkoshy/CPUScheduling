package cpuscheduling;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * User class
 *
 * This class contains the frontend load, that enables a user to interact with a GUI that contains all the different types of
 * scheduling methods. Contains the main method so this class is the one that executes.
 *
 * @author Alvis Koshy, Zhu Su
 * @version 1.0
 * @since 2017-11-14
 */

public class User extends JFrame implements ActionListener {

	JFrame frame = new JFrame("Scheduler");
	JButton buttonOK = new JButton("OK");
	JButton buttonExit = new JButton("Exit");
	JLabel schedule = new JLabel("Schedule Algorithm: ");
	static String[] options = { "First Come First Served", "Shortest Job First", "Preemptive Shortest Job First",
			"Priorty", "" + "Preemptive Priority", "Round Robin", "Multilevel Queue", "Multilevel Feedback Queue" };
	static JComboBox cmb = new JComboBox(options);
	static ArrayList<Process> list = new ArrayList<Process>();
	static Process process;
	
	/**
	 * A blank constructor
	 */
	public User() {
		super();
		initialize();
	}
	/**
	 * A method to initialize the GUI
	 */
	private void initialize() {

		frame.add(schedule);
		frame.add(cmb);
		frame.add(buttonOK);
		frame.add(buttonExit);
		buttonOK.addActionListener(this);
		buttonExit.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1, 5, 5));
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	/**
	 * The main method, used for runningg the program
	 * @param args
	 */
	public static void main(String[] args) {

		
		//run and init the GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new User().setVisible(false);
			}
		});

		// build array of processes
		// call a schedule using said array
		// schedule will build the queue with respective rules
	}

	/**
	 * An array getter that populates an array with random int values ranging from 0-60
	 * 
	 * @return
	 * 		numbers - an array that contains the random values
	 */
	public static int[] getNumber() {
		int[] numbers = new int[5];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new Random().nextInt(60);
		}
		return numbers;

	}
	/**
	 * An array getter that populates an array in conjunction with numbers, since it relies on IO
	 * This iterates through and checks to see if there is any duplicates in the array and avoids choosing that number
	 * device.
	 * 
	 * @return
	 * 		all - an array of device numbers that has no duplicates
	 */	
	public static int[] getDevNum() {
		boolean duplicate = false;
		int max = 100;
		int min = 1;
		Random rand = new Random();

		int[] all = new int[20];
		for (int x = 0; x < 20; x++) {
			duplicate = false;
			// generates # from 1-100
			int randomNum = rand.nextInt((max - min) + 1) + min;
			// iterates through array
			for (int i : all) {
				// if there's a matching flag boolean
				if (i == randomNum) {
					duplicate = true;
					break;
				}
			}
			// if boolean is true, stay at the same x value
			if (duplicate) {
				x--;
			} else {
				all[x] = randomNum;
			}
		}
		return all;
	}
	/**
	 * The method that gives the buttons and checkbox an action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//If exit btn is clicked, close gui
		if(buttonExit == e.getSource())
		{
			frame.setVisible(false);
			frame.dispose();
		}
		
		Object selected = cmb.getSelectedItem();
		//if FCFS is chosen, parse it to FCFS method in schedule
		if (selected.toString().equals("First Come First Served")) {
			if (buttonOK == e.getSource()) {
				for (int i = 0; i < 5; i++) {
					list.add(process = new Process(i, new Random().nextInt(100)));
					System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
				}
				Schedule.NP_FCFS(list);
				frame.dispose();

			}
			//if SJF is chosen, parse it to SJF method in schedule
		} else if (selected.toString().equals("Shortest Job First")) {
			
			if (buttonOK == e.getSource()) {
				for (int i = 0; i < 5; i++) {
					list.add(process = new Process(i, new Random().nextInt(100)));
					System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
				}
				Schedule.NP_SJF(list);
				frame.dispose();

			}
			//if SRTF is chosen, parse it to SRTF method in schedule
		} else if (selected.toString().equals("Preemptive Shortest Job First")) {
			if (buttonOK == e.getSource()) {
				for (int i = 0; i < 5; i++) {
					list.add(process = new Process(i, getNumber(), new Random().nextInt(100), getNumber(), getDevNum()));
					System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
				}
				Schedule.P_SRTF(list);
				frame.dispose();

			}
			//if priority is chosen, parse it to priority method in schedule
		} else if (selected.toString().equals("Priorty")) {
			if (buttonOK == e.getSource()) {
				for (int i = 0; i < 5; i++) {
					list.add(process = new Process(i, new Random().nextInt(100), new Random().nextInt(6)));
					System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime() + "\t"
							+ process.getPriority());
				}
				Schedule.NP_Priority(list);
				frame.dispose();
				//if pre-emptive priority is chosen, parse it to pre-emptive priority method in schedule
		} else if (selected.toString().equals("Preemptive Priority")) {
				if (buttonOK == e.getSource()) {
					for (int i = 0; i < 5; i++) {
						list.add(process = new Process(i, new Random().nextInt(100), new Random().nextInt(6)));
						System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime() + "\t"
								+ process.getPriority());
					}
					Schedule.P_Priority(list);
					frame.dispose();

				}
				//if RR is chosen, parse it to RR method in schedule
		} else if (selected.toString().equals("Round Robin")) {
				if (buttonOK == e.getSource()) {
					for (int i = 0; i < 5; i++) {
						list.add(process = new Process(i, new Random().nextInt(100)));
						System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
					}
					Schedule.P_RR(list);
					frame.dispose();

				}
				//if MLQ is chosen, parse it to MLQ method in schedule
		} else if (selected.toString().equals("Multilevel Queue")) {
				if (buttonOK == e.getSource()) {
					for (int i = 0; i < 5; i++) {
						list.add(process = new Process(i, new Random().nextInt(100)));
						System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
					}
					Schedule.MLQ(list);
					frame.dispose();

				}
				//if MLFQ is chosen, parse it to MLFQ method in schedule
		} else if (selected.toString().equals("Multilevel Feedback Queue")) {
				if (buttonOK == e.getSource()) {
					for (int i = 0; i < 5; i++) {
						list.add(process = new Process(i, new Random().nextInt(100)));
						System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime());
					}
					Schedule.MLFQ(list);
					frame.dispose();

				}
			}
		}
	}
}
