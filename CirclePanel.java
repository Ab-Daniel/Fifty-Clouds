import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class CirclesPanel extends JPanel
{
	public CirclesPanel( )
	{
		generator = new Random( );
		setBackground(Color.white);
	}
	
	public void constructCircles( )
	{
		circles = new ArrayList<Ellipse2D.Double>( );
		rejected = 0;
		//  fill circles array list with 250 circles that don't intersect
		for (int i = 1; i <= 50; i++)
		{
			boolean done;
			do
			{
				int r = generator.nextInt(30) + 1;              // 1 <= r <= 30
				int xCenter = generator.nextInt(getWidth( ) - 2 * r) + r;   // r <= xCenter <= width - r
				int yCenter = generator.nextInt(getHeight( )- 2 * r) + r;   // r <= yCenter <= height - r
				Ellipse2D.Double c
				      = new Ellipse2D.Double(xCenter - r, yCenter - r, 2 * r, 2 * r);
				done = true;
				if(i > 1)
				{
					for(int k = 0; k < circles.size( ); k++)
					{
						boolean intersect = circlesIntersect(c, (Ellipse2D.Double)circles.get(k));
						if(intersect)
						{
							done = false;
							rejected++;
							break;
						}
					}
				}
				if(done)
					circles.add(c);
			} while(!done);
		}
		System.out.println("Rejected % = " + (100 * rejected/(rejected + 300)));
	}
	
	/**
	 * Test if two circles intersect (distance between centers is less than sum of radii)
	 * @param c1 the first circle
	 * @param c2 the second circle
	 */
	public boolean circlesIntersect(Ellipse2D.Double c1, Ellipse2D.Double c2)
	{
		double radius1 = c1.getWidth( ) / 2;
		double radius2 = c2.getWidth( ) / 2;
		double dx = (c1.x + radius1) - (c2.x + radius2);
		double dy = (c1.y + radius1) - (c2.y + radius2);
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance < radius1 + radius2;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		/*Rectangle r = new Rectangle(0, 0, getWidth( ), getHeight( ));
		g2.setColor(Color.white);
		g2.fill(r);
		g2.setColor(Color.black);*/
		constructCircles( );
		for (int k = 0; k < circles.size( ); k++)
		{
			Ellipse2D.Double c = circles.get(k);
			g2.draw(c);
		}
	}
	
	private ArrayList<Ellipse2D.Double> circles;
	private int rejected;
	private Random generator;
}
