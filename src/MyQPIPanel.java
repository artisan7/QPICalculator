import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyQPIPanel extends Panel {
	private float grade = 4.0f;
	private MyNumPicker np_units;
	
	public MyQPIPanel() {
		JTextField tf_course = new JTextField();
		
		String[] letterGrades = {"A", "B+", "B", "C+", "C", "D", "F", "FD", "W"};
		JComboBox<String> cb_grade = new JComboBox<String>(letterGrades);
		cb_grade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = cb_grade.getSelectedIndex();
				switch (selected) {
				case 0: grade = 4.0f; break;
				case 1: grade = 3.5f; break;
				case 2: grade = 3.0f; break;
				case 3: grade = 2.5f; break;
				case 4: grade = 2.0f; break;
				case 5: grade = 1.0f; break;
				default:grade = 0.0f; break;
				}
			}
		});
		
		np_units = new MyNumPicker(3);
		
		setLayout(new GridLayout(1, 3));
		add(tf_course);
		add(cb_grade);
		add(np_units);
	}
	
	public float getGrade() { return grade; }
	public int getUnits() { return np_units.getValue(); }
}