package MyFractal;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MyFractal extends JFrame implements ActionListener {

    MyPanel drawingArea = null;   // The Panel
    public final int size = 1024; // The width of the frame

    private class Point {
	double x, y; // x and y values of the point
	Point(double _x, double _y) {
	    x = _x;
	    y = _y;
	}
	Point(int _x, int _y) {
	    x = _x;
	    y = _y;
	}
	Point(Point p) {
	    x = p.x;
	    y = p.y;
	}
    }

    private class MyPanel extends JPanel {

	int recursion = 0;  // A quantidade da recursão 
        
        
        // Este é o metodo que recursivamente ira desenhar um losango dentro do quadrado
        // e vice versa. Verificando se a iteração é a 0, se for ira desenhar apenas os pontos 
        // passados por parametro. Se não for, ira pegar o meio entre cada ponto e ira chamar 
        // recursivamente o desenho desde mesmo losango/quadrado (passado por parametro) e tembem a 
        // recursão de seu quadrado/losango interno
        
        public void drawLosangles(Graphics g, Point p1, Point p2, Point p3,Point p4, int n) {
            if(n==0){
                g.setColor(Color.black);
		g.drawLine((int)p1.x,(int)p1.y,(int)p2.x,(int)p2.y);
		g.drawLine((int)p2.x,(int)p2.y,(int)p3.x,(int)p3.y);
		g.drawLine((int)p3.x,(int)p3.y,(int)p4.x,(int)p4.y);
                g.drawLine((int)p4.x,(int)p4.y,(int)p1.x,(int)p1.y);
		return;
            }
            Point mp1 = new Point((p2.x+p1.x)/2.0,(p2.y+p1.y)/2.0);
	    Point mp2 = new Point((p3.x+p2.x)/2.0,(p3.y+p2.y)/2.0);
	    Point mp3 = new Point((p3.x+p4.x)/2.0,(p3.y+p4.y)/2.0);
            Point mp4 = new Point((p4.x+p1.x)/2.0,(p4.y+p1.y)/2.0);
            drawLosangles(g, p1, p2, p3, p4, n-1);
	    drawLosangles(g, mp1, mp2, mp3,mp4,n-1);
        }
		
        // Esta é a função que ira chamar o inicio do desenho e chamar a função acima para 
        // a recursão
	public void paintComponent(Graphics g) {
            // Criando os pontos iniciais do losango.
            Point l1 = new Point(10, 10);
            Point l2 = new Point(10, size-500);
            Point l3 = new Point(size-500, size-500);
            Point l4 = new Point(size-500, 10);
            drawLosangles(g, l1, l2, l3, l4, recursion);
	    
            
	    // Escrevendo na tela a quantidade de recursão
	    g.setFont(new Font("Serif", Font.BOLD, 20));
	    g.drawString("Nível de recursão: " + recursion,size/2-100,60);
	}	
    }
	
    /**
     * Definindo a area de desenho e criando os botões
     */
    public MyFractal() {
	// Criando um novo Panel
	drawingArea = new MyPanel();
	drawingArea.setVisible(true);
	setPreferredSize(new Dimension(size, (size/2)+95));
	// Definindo a area a ser desenhada
	setContentPane(drawingArea);
	// Tell this frame to terminate when the JFrame display is closed.
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// Set the title of the displayed window.
	setTitle("Meu Fractal");
	// Adicionando os 2 botoes de iteração
	JButton button1 = new JButton("Less");
	button1.setActionCommand("less");
	button1.addActionListener(this);
	JButton button2 = new JButton("More");
	button2.setActionCommand("more");
	button2.addActionListener(this);
	// Adicionando os botões ao Panel
	drawingArea.add(button1);
	drawingArea.add(button2);
	// Tornando visivel
	setVisible(true);
	pack();
	setVisible(true);
    }

    /**
     * Criando o listener. Oque será feito quando um botão for pressionado
     */
    public void actionPerformed(ActionEvent e) {
	// Diminui um nivel de recursão
	if ("less".equals(e.getActionCommand())) {
	    drawingArea.recursion--;
	    if (drawingArea.recursion < 0) drawingArea.recursion = 0;
	}
	// Aumenta um nivel de recursão
	else if ("more".equals(e.getActionCommand())) {
	    drawingArea.recursion++;
	}
	repaint();
    }
	
    /**
     * ======================================================================
     * A classe main que so ira criar uma nova instancia da Classe MyFractal
     * ======================================================================
     */
    public static void main(String[] args) {
	MyFractal losangle = new MyFractal();
    }
}