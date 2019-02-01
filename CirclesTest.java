import javax.swing.JFrame;
import javax.swing.JPanel;

public class CirclesTest
{
	public static void main(String[ ] args)
	{
		JFrame frame = new JFrame( );
		frame.setTitle("250 Non-Intersecting Circles");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel buttonPanel = new JPanel();
		CirclesPanel panel = new CirclesPanel( );
		frame.add(panel);
		frame.setVisible(true);
	}
}
