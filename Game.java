import javax.swing.JFrame;

public class Game{
	
	public static void main(String[] args){
		
		JFrame window=new JFrame("Kirby");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.pack();
		window.setVisible(true);
	}
}
	