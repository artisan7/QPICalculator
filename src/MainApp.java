import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {
	
	Vector<MyQPIPanel> subjects = new Vector<MyQPIPanel>();
	
	JPanel pnl_control;
	JPanel pnl_qpi;
	JPanel pnl_result;
	JTextField tf_result;
	
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
		MyQPIPanel qpi1 = new MyQPIPanel();
		subjects.add(qpi1);
		
		pnl_qpi.add(qpi1);
		
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
		setSize(450, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(pnl_control, BorderLayout.NORTH);
		add(pnl_qpi, BorderLayout.CENTER);
		add(pnl_result, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str_src = ((JButton) e.getSource()).getText();
		
		if (str_src.equals("ADD")) {
			MyQPIPanel s = new MyQPIPanel();
			subjects.add(s);
			pnl_qpi.add(s);
			
			setSize(getSize().width, getSize().height + 30);
		}
		else if (str_src.equals("RESET")) {
			int delSize = subjects.size()-1;
			if (delSize > 0) {
				setSize(getSize().width, getSize().height - 30*delSize);
				
				subjects.clear();
				pnl_qpi.removeAll();
				
				MyQPIPanel qpi1 = new MyQPIPanel();
				subjects.add(qpi1);
				pnl_qpi.add(qpi1);
			}
		}
		else if (str_src.equals("CALCULATE")) {
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
	
	public static void main(String[] args) {
		new MainApp();
	}
}