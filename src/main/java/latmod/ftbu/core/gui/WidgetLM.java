package latmod.ftbu.core.gui;
import latmod.ftbu.core.util.FastList;
import cpw.mods.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class WidgetLM
{
	public final GuiLM gui;
	public int posX, posY, width, height;
	public String title = null;
	
	public WidgetLM(GuiLM g, int x, int y, int w, int h)
	{
		gui = g;
		posX = x;
		posY = y;
		width = w;
		height = h;
	}
	
	public boolean isAt(int x, int y)
	{ return x >= posX && y >= posY && x < posX + width && y < posY + height; }
	
	public boolean mouseOver()
	{ return isAt(gui.mouseXR, gui.mouseYR); }
	
	public void render(TextureCoords icon, double rw, double rh)
	{ if(icon != null) icon.render(gui, posX, posY, (int)(width * rw), (int)(height * rh)); }
	
	public void render(TextureCoords icon)
	{ render(icon, 1D, 1D); }
	
	public void mousePressed(int b)
	{
	}
	
	public boolean keyPressed(int key, char keyChar)
	{
		return false;
	}
	
	public void addMouseOverText(FastList<String> l)
	{
		if(title != null) l.add(title);
	}
}