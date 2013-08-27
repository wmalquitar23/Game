
public class Point {

	int x;
	int y;
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void SetX(int x)
	{
		this.x = x;
	}
	public void SetY(int y)
	{
		this.y = y;
	}
	public int GetX()
	{
		return this.x;
	}
	public int GetY()
	{
		return this.y;
	}
	public void SetXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public Point GetPoint()
	{
		return this;
	}
}
