import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {
	
	private Vector<MyQPIPanel> subjects = new Vector<MyQPIPanel>();
	private Dimension ORIGINAL_SIZE = new Dimension(450, 130);
	
	private JPanel pnl_control;
	private JPanel pnl_qpi;
	private JPanel pnl_result;
	private JTextField tf_result;
	
	// MAIN APP CONSTRUCTOR
	public MainApp() {
		pnl_control = new JPanel();
		pnl_qpi = new JPanel();
		pnl_result = new JPanel();
		
		// CONTROL BUTTON SET UP
		JButton btn_addSubj = new JButton("ADD");
		JButton btn_reset = new JButton("RESET");
		JButton btn_calc = new JButton("CALCULATE");
		
		btn_addSubj.addActionListener(this);
		btn_reset.addActionListener(this);
		btn_calc.addActionListener(this);
		
		pnl_control.setLayout(new GridLayout(1, 3));
		pnl_control.add(btn_addSubj);
		pnl_control.add(btn_reset);
		pnl_control.add(btn_calc);
		
		// QPI PANELS SETUP
		addQPIPanel();
		
		// RESULT PANEL SETUP
		JLabel lbl_qpi = new JLabel("QPI");
		lbl_qpi.setHorizontalAlignment(JLabel.CENTER);
		tf_result = new JTextField();
		tf_result.setEditable(false);
		tf_result.setHorizontalAlignment(JTextField.CENTER);
		pnl_result.setLayout(new GridLayout(1,2));
		
		pnl_result.add(lbl_qpi);
		pnl_result.add(tf_result);
		
		// MAIN WINDOW SETUP
		setTitle("QPI Calculator");
		setSize(ORIGINAL_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		
		add(pnl_control, BorderLayout.NORTH);
		add(pnl_qpi, BorderLayout.CENTER);
		add(pnl_result, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	// ACTION LISTENER
	public void actionPerformed(ActionEvent e) {
		String str_src = ((JButton) e.getSource()).getText();
		
		if (str_src.equals("ADD")) {	// add button functionality
			addQPIPanel();
		}
		else if (str_src.equals("RESET") && subjects.size()-1 > 0) {	// reset button functionality
			subjects.clear();
			pnl_qpi.removeAll();
			
			addQPIPanel();

			setSize(ORIGINAL_SIZE);
		}
		else if (str_src.equals("CALCULATE")) {		// calculate button functionality
			float sum = 0;
			int total_units = 0;
			
			for (MyQPIPanel s : subjects) {
				int units = s.getUnits();
				sum += s.getGrade()*units;
				total_units += units;
			}
			
			tf_result.setText(String.format("%.2f", sum/total_units));
		}
	}
	
	// ADD QPI PANEL
	private void addQPIPanel() {
		MyQPIPanel s = new MyQPIPanel();
		subjects.add(s);
		
		// delete button w/ functionality
		JButton btn_delete = new JButton("x");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel src = (JPanel) ((JButton) e.getSource()).getParent();
				subjects.remove(src.getComponent(0));
				pnl_qpi.remove(src);
				
				setSize(getSize().width, getSize().height - 41);
			}
		});
		
		// qpi panel setup
		JPanel p = new JPanel();
		p.add(s, BorderLayout.CENTER);
		p.add(btn_delete, BorderLayout.EAST);
		
		pnl_qpi.add(p);
		setSize(getSize().width, getSize().height + 41);
	}
	
	// MAIN
	public static void main(String[] args) {
		new MainApp();
	}
}