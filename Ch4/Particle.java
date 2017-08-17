import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Particle {

	private static final double INFINITY = Double.POSITIVE_INFINITY;
	
	private double rx, ry;
	private double vx, vy;
	private int count;
	private final double radius;
	private final double mass;
	private final Color color;
	
	public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color)
	{
		this.vx = vx;
		this.vy = vy;
		this.rx = rx;
		this.ry = ry;
		this.radius = radius;
		this.mass = mass;
		this.color = color;
	}
	
	public Particle()
	{
		rx = StdRandom.uniform(0.0, 1.0);
		ry = StdRandom.uniform(0.0, 1.0);
		vx = StdRandom.uniform(-0.005, 0.005);
		vy = StdRandom.uniform(-0.005, 0.005);
		radius = 0.01;
		mass = 0.5;
		color = Color.BLACK;
	}
	
	public void move(double dt)
	{
		rx += vx * dt;
		ry += vy * dt;
	}
	
	public void draw()
	{
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx,  ry,  radius);
	}
	
	public int count()
	{ return count; }
	
	public double timeToHit(Particle that)
	{
		if (this == that)
			return INFINITY;
		
		double dx = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		
		if (dvdr > 0)
			return INFINITY;
		
		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		
		if (d < 0)
			return INFINITY;
		
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}
	
	public double timeToHitVerticalWall()
	{
		if (vx > 0)
			return (1.0 - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return INFINITY;
	}
	
	public double timeToHitHorizontalWall()
	{
		if (vy > 0)
			return (1.0 - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return INFINITY;
	}
	
	public void bounceOff(Particle that)
	{
		double dx = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		double dist = this.radius + that.radius;
		
		double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
		
		double fx = magnitude * dx / dist;
		double fy = magnitude * dy / dist;
		
		this.vx += fx / this.mass;
		this.vy += fy / this.mass;
		that.vx -= fx / that.mass;
		that.vy -= fy / that.mass;
		
		this.count++;
		that.count++;
	}
	
	public void bounceOffVerticalWall()
	{
		vx = -vx;
		count++;
	}
	
	public void bounceOffHorizontalWall()
	{
		vy = -vy;
		count++;
	}
	public double kineticEnergy()
	{
		return 0.5 * mass * (vx * vx + vy * vy);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
