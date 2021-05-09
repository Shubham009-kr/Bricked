package Game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj= new JFrame();
		Design game= new Design();
		obj.setBounds(10, 10, 708, 608); // Dimensions of main frame. 
		obj.setTitle("Bricked");
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);

	}

}
