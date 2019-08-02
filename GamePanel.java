import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	
	public static final int WIDTH=400;
	public static final int HEIGHT=400;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS=30;
	private int targetTime=1000/FPS;
	
	private TileMap tileMap;
	private Player player;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread==null){
			thread=new Thread(this);
			thread.start();
		}
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void run(){
		init();
		
		long startTime;
		long urdTime;
		long waitTime;
		
		while(running){
			startTime=System.nanoTime();
			
			update();
			render();
			draw();
			
			urdTime=(System.nanoTime()-startTime)/1000000;
			waitTime=targetTime-urdTime;
			
			try{
				thread.sleep(waitTime);
			}catch(Exception e){}
	}
	}
	
	private void init(){
		running=true;
		
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D)image.getGraphics();
		
		tileMap=new TileMap("Map.txt",32);
		tileMap.loadTiles("pic/tileset.gif");
		
		player=new Player(tileMap);
		player.setx(50);
		player.sety(50);
	}
	
	//////////////////////////////////////////////////////////////////
	
	private void update(){
		
		tileMap.update();
		player.update();
		
	}
	
	private void render(){
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		tileMap.draw(g);
		player.draw(g);
	}
	
	private void draw(){
		Graphics g2=getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
		
	}
	
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){
		int keyCode=key.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT){player.setLeft(true);}
		if(keyCode==KeyEvent.VK_RIGHT){player.setRight(true);}
		if(keyCode==KeyEvent.VK_W){player.setJumping(true);}

	}

	public void keyReleased(KeyEvent key){
		int keyCode=key.getKeyCode();
		if(keyCode==KeyEvent.VK_LEFT){player.setLeft(false);}
		if(keyCode==KeyEvent.VK_RIGHT){player.setRight(false);}
		if(keyCode==KeyEvent.VK_W){player.setJumping(false);}

	}

	private int previousX=0;
	private int previousY=0;

	public void mouseClicked(MouseEvent mouse){
	
	//pause=false;
		//player.setJumping(true);

		

	}
	private boolean l=true;
	private boolean r=true;
	public void mousePressed(MouseEvent mouse){
		//player.setJumping(true);
	int y = mouse.getY();
 //if(y < 150)
       // {
        	player.setJumping(true);


      //  }

	}

	public void mouseReleased(MouseEvent mouse){
	//	player.setFiring(false);
		
		player.setJumping(false);
		
	}
	public void mouseMoved(MouseEvent mouse){	
	//player.setPlayer(mouse.getX(),mouse.getY());
		int x = mouse.getX();
		int y = mouse.getY();

        if (x <= 150) {
        	if(l == true)
        	{
        		player.setRight(false);
        		player.setLeft(true);
        		//l=false;
        	}
       // 	else
       // 	{
       // 		player.setLeft(false);
      //  		l=true;
      //  	}
        }
        if (x>=250)
        	{
        	if(r == true)
        	{
        		player.setLeft(false);
        		player.setRight(true);
       // 		r=false;
        	}
      //  	else
      //  	{
        //		player.setRight(false);
       // 		r=true;
        //	}
        }
        if(x > 150 && x < 250)
        {
        	        		player.setLeft(false);
        		player.setRight(false);

        }

       


	}

	public void mouseDragged(MouseEvent mouse){
	//	player.setPlayer(mouse.getX(),mouse.getY());
	//	player.setFiring(true);
			
		//player.setJumping(true);


	}
	
	public void mouseEntered(MouseEvent mouse){		
	//	player.setJumping(true);

	}
	
	public void mouseExited(MouseEvent mouse){		
	//	player.setJumping(true);

	
	}


}