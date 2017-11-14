package cpuscheduling;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class User extends JFrame implements ActionListener{

	JFrame frame = new JFrame("Scheduler");
	JButton buttonOK = new JButton("OK");
	JButton buttonExit = new JButton("Exit");
	JLabel schedule = new JLabel("Schedule Algorithm: ");
	static String[] options={"First Come First Served","Shortest Job First","Preemptive Shortest Job First","Priorty",""
			+ "Preemptive Priority", "Round Robin", "Multilevel Queue", "Multilevel Feedback Queue"};
	static JComboBox cmb = new JComboBox(options);
	static ArrayList<Process> list = new ArrayList<Process>();
	static ArrayList<Process> plist = new ArrayList<Process>();
	static ArrayList<Process> list3 = new ArrayList<Process>();
	static Process process;

	public User() {
		super();
		initialize();
	}

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
	public static void main(String[] args) {
		
	
		System.out.println("Process\tArrival\tBurst\tPriority");
		for(int i = 0; i < 5; i++)
		{
			
			list3.add(process = new Process(i, getNumber(), new Random().nextInt(6), getNumber(), getDevNum()));
		}
		for (int i = 0; i < 5; i++) {
			list.add(process = new Process(i, new Random().nextInt(100)));
			System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime() + "\t" + process.getPriority());			
		}
		
		for (int i = 0; i < 5; i++) {
			plist.add(process = new Process(i, new Random().nextInt(100), new Random().nextInt(6)));
			System.out.println("P" + (i + 1) + "\t" + process.getArrivalTime() + "\t" + process.getReqTime() + "\t" + process.getPriority());			
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new User().setVisible(true);
			}
		});




		// build array of processes
		// call a schedule using said array
		// schedule will build the queue with respective rules
	}
	public static ArrayList<Integer> getNumber()
	{
		ArrayList<Integer> numbers = new ArrayList<Integer>();   
		Random randomGenerator = new Random(50);
		while (numbers.size() < 10) {

		    int random = randomGenerator .nextInt(10);
		    numbers.add(random);
		}
		return numbers;
		
	}
	
	public static ArrayList<Integer> getDevNum() 
	{
		ArrayList<Integer> numbers = new ArrayList<Integer>();   
		Random randomGenerator = new Random(50);
		while (numbers.size() < 10) {

		    int random = randomGenerator .nextInt(10);
		    if (!numbers.contains(random)) {
		        numbers.add(random);
		    }
		}
		return numbers;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object selected = cmb.getSelectedItem();
		if(selected.toString().equals("First Come First Served"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.NP_FCFS(list);
				frame.dispose();

			}
		} else if(selected.toString().equals("Shortest Job First"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.NP_SJF(list);
				frame.dispose();

			}
		}else if(selected.toString().equals("Preemptive Shortest Job First"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.P_SRTF(list);
				frame.dispose();

			}
		}else if(selected.toString().equals("Priorty"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.NP_Priority(plist);
				frame.dispose();

			}
		}else if(selected.toString().equals("Preemptive Priority"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.P_Priority(list3);
				frame.dispose();

			}
		}else if(selected.toString().equals("Round Robin"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.P_RR(list);
				frame.dispose();

			}
		}else if(selected.toString().equals("Multilevel Queue"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.MLQ(list);
				frame.dispose();

			}
		}else if(selected.toString().equals("Multilevel Feedback Queue"))
		{
			if(buttonOK == e.getSource()) {
				Schedule.MLFQ(list);
				frame.dispose();

			}
		}
	}


}
