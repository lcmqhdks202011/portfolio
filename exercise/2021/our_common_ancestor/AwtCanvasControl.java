import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class AwtCanvasControl {
    private Frame mainFrame;
    private Label headerLabel;
    private Panel controlPanel;

    public AwtCanvasControl() {
        prepareGUI();
    }



    private void prepareGUI() {
        mainFrame = new Frame("Coalescence");
        mainFrame.setSize(780, 820);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);

        controlPanel = new Panel();

        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void showControl(Coalescence male, Coalescence female, ArrayList<Point> population, double MaxT, int pop) {
        controlPanel.setLayout(new GridLayout(1, 1, 0, 0));

        controlPanel.add(new MyCanvas(male, female,population, MaxT, pop));
        mainFrame.setVisible(true);
    }

    class MyCanvas extends Canvas {

        Coalescence male;
        Coalescence female;
        ArrayList<Point> population;
        double MaxT;
        int pop;

        public MyCanvas(Coalescence male, Coalescence female, ArrayList<Point> population, double MaxT, int pop) {
            setBackground(Color.gray);
            this.male = male;
            this.female = female;
            this.population = population;
            this.MaxT = MaxT;
            this.pop = pop;
        }

        public void paint(Graphics g) {
            Graphics2D g2;
            g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            Font font = new Font("Serif", Font.PLAIN, 13);
            g2.setFont(font);
            g2.drawString("" + "Starting Population : " + pop + " / " +
                    "Tmax : " + MaxT , 10, 20);

            g2.setColor(Color.BLACK);

            g2.setStroke(new BasicStroke(5));
            g2.draw(new Line2D.Double(640, 60, 640, 660));
            g2.draw(new Line2D.Double(40, 660, 640, 660));
            g2.setStroke(new BasicStroke());
            g2.drawString("" + pop, 660, 65 );
            g2.drawString("" + pop/2, 660, 360 );
            g2.drawString("" + 0, 660, 660 );
            g2.drawString("" + 0, 40, 680 );
            g2.drawString("" + MaxT, 620, 680 );
            g2.drawString("" + MaxT/2, 350, 680 );
            g2.drawString("Ans", 620, 690 );



            g2.setColor(Color.BLUE);
            g2.drawLine(300,360,330,360);
            g2.drawString("Aieux", 350, 360);
            this.placeAllPoint(g2, male.points, MaxT, pop);
            g2.setColor(Color.RED);
            g2.drawLine(300,380,330,380);
            g2.drawString("Aieules", 350, 380);
            this.placeAllPoint(g2, female.points, MaxT, pop);
            g2.setColor(Color.GREEN);
            g2.drawLine(300,400,330,400);
            g2.drawString("Total Population", 350, 400);
            this.placeAllPoint(g2, population, MaxT, pop);
            g2.setColor(Color.cyan);
            g2.drawString("IFT 2015 - Devoir 2", 40, 700);
            g2.drawString("Author : Changmin Lee / Laurent Charlebois", 40, 720);


        }

        public void placePoint(Graphics2D g2, Point p, double MaxT, int pop){
            int x = 40 + (int)(p.t * 600/ MaxT);
            int y = 660 - (int)(p.n * 600/ pop);

            g2.drawOval(x,y,3,3);
        }

        public void placeAllPoint(Graphics2D g2, ArrayList<Point> points, double MaxT, int pop){
            for(Point i : points){
                this.placePoint(g2, i, MaxT, pop);
            }
        }
    }


}