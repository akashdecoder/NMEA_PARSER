import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Parser extends JFrame{
    JFrame f;
    JLabel l1,l2;
    JLabel l,l3;
    JTextField t1,t2,t3,t4;
    JButton b1, b2, b3, b4;
    JLabel g,g1,g2,g3,g4,g5,g6,g7,g8,g9;
    JTextField gt1, gt2, gt3, gt4, gt5, gt6, gt7, gt8, gt9;
    JLabel r,r1,r2,r3,r4,r5,r6,r7;
    JTextField rt1, rt2, rt3, rt4, rt5, rt6, rt7;
    
    public Parser(){
      f = new JFrame("NMEA GPS Parser");
      f.setLayout(null);
      f.setSize(800,1000);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      l1 = new JLabel("");
      l1.setText("GGA:");
      l2 = new JLabel("");
      l2.setText("RMC:");
      t1 = new JTextField();
      t2 = new JTextField();
      b3 = new JButton("Parse GGA");
      b4 = new JButton("Parse RMC");
      
      b1 = new JButton("GGA Checksum");
      b2 = new JButton("RMC Checksum");
      
      l = new JLabel("GGA Checksum: ");
      t3 = new JTextField();
      
      l3 = new JLabel("RMS Checksum: ");
      t4 = new JTextField();
      
      g = new JLabel("Parsed GGA Data");
      r = new JLabel("Parsed RMC Data");
      
      g1 = new JLabel("UTC TIME: ");
      g2 = new JLabel("LATITUDE: ");
      g3 = new JLabel("LONGITUDE: ");
      g4 = new JLabel("QUALITY: ");
      g5 = new JLabel("No. OF SATELLITES: ");
      g6 = new JLabel("HDOP: ");
      g7 = new JLabel("ALTITUDE: ");
      g8 = new JLabel("HEIGHT: ");
      
      gt1 = new JTextField();
      gt2 = new JTextField();
      gt3 = new JTextField();
      gt4 = new JTextField();
      gt5 = new JTextField();
      gt6 = new JTextField();
      gt7 = new JTextField();
      gt8 = new JTextField();
      
      r1 = new JLabel("UTC TIME: ");
      r2 = new JLabel("LATITUDE: ");
      r3 = new JLabel("LONGITUDE: ");
      r4 = new JLabel("SPPED: ");
      r5 = new JLabel("TRUE COURSE: ");
      r6 = new JLabel("DATE: ");
      r7 = new JLabel("MAGNETIC VARIATION: ");
      
      rt1 = new JTextField();
      rt2 = new JTextField();
      rt3 = new JTextField();
      rt4 = new JTextField();
      rt5 = new JTextField();
      rt6 = new JTextField();
      rt7 = new JTextField();

      
      l1.setBounds(60,30,40,30);
      t1.setBounds(100,30,500,30);
      b3.setBounds(620, 32, 150,25);
      
      l2.setBounds(60,60,40,30);
      t2.setBounds(100,60,500,30);
      b4.setBounds(620,62,150,25);
      
      
      b1.setBounds(100,100,140,30);
      b2.setBounds(460,100,140,30);
      
      l.setBounds(100,140,200,30);
      t3.setBounds(220,140,200,30);
      
      l3.setBounds(100,180,200,30);
      t4.setBounds(220,180,200,30);
      
      g.setBounds(20,250,150,30);
      g1.setBounds(20,290,80,30);
      gt1.setBounds(200,290,120,30);
      g2.setBounds(20,320,80,30);
      gt2.setBounds(200,320,120,30);
      g3.setBounds(20,350,90,30);
      gt3.setBounds(200,350,120,30);
      g4.setBounds(20,380,80,30);
      gt4.setBounds(200,380,120,30);
      g5.setBounds(20,410,150,30);
      gt5.setBounds(200,410,120,30);
      g6.setBounds(20,440,50,30);
      gt6.setBounds(200,440,120,30);
      g7.setBounds(20,470,80,30);
      gt7.setBounds(200,470,120,30);
      g8.setBounds(20,500,80,30);
      gt8.setBounds(200,500,120,30);
      
      r.setBounds(390,250,150,30);
      r1.setBounds(390,290,150,30);
      rt1.setBounds(580,290,120,30);
      r2.setBounds(390,320,150,30);
      rt2.setBounds(580,320,120,30);
      r3.setBounds(390,350,150,30);
      rt3.setBounds(580,350,120,30);
      r4.setBounds(390,380,150,30);
      rt4.setBounds(580,380,120,30);
      r5.setBounds(390,410,150,30);
      rt5.setBounds(580,410,120,30);
      r6.setBounds(390,440,150,30);
      rt6.setBounds(580,440,120,30);
      r7.setBounds(390,470,200,30);
      rt7.setBounds(580,470,120,30);
      
      
      f.add(l1);
      f.add(t1);
      f.add(b3);
      f.add(l2);
      f.add(t2);
      f.add(b4);
      f.add(b1);
      f.add(b2);
      f.add(l);
      f.add(t3);
      f.add(l3);
      f.add(t4);
      f.add(g);
      f.add(g1);
      f.add(gt1);
      f.add(g2);
      f.add(gt2);
      f.add(g3);
      f.add(gt3);
      f.add(g4);
      f.add(gt4);
      f.add(g5);
      f.add(gt5);
      f.add(g6);
      f.add(gt6);
      f.add(g7);
      f.add(gt7);
      f.add(g8);
      f.add(gt8);
      
      f.add(r);
      f.add(r1);
      f.add(rt1);
      f.add(r2);
      f.add(rt2);
      f.add(r3);
      f.add(rt3);
      f.add(r4);
      f.add(rt4);
      f.add(r5);
      f.add(rt5);
      f.add(r6);
      f.add(rt6);
      f.add(r7);
      f.add(rt7);
      
      f.setVisible(true);
      f.setResizable(true);
      
      b1.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  try {
    			  String str;
    			  str = t1.getText();
    			  t3.setText(hexchecksum(checksum(str)));
    		  }
    		  catch(Exception e1){
    			  JOptionPane.showMessageDialog(null,"Enter valid GGA sentence");
    		  }
    	  }
      });
      b2.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  try {
    			  String str;
    			  str = t2	.getText();
    			  t4.setText(hexchecksum(checksum(str)));
    		  }
    		  catch(Exception e1){
    			  JOptionPane.showMessageDialog(null,"Enter valid RMC sentence");
    		  }
    	  }
      });
      b3.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  int Utcgga;
			  double Latitude;
			  double Longitude;
			  double altitude;
			  double heigth;
			  int nos;
			  double HDOP;
			  String str;
    		  try {
    			  str = t1.getText();
    			  String delim = "[,]";
    		      String []ele = str.split(delim);
    		      assert(ele[0] == "$GPGGA");
    		      Utcgga = Integer.parseInt(ele[1]);
    		      gt1.setText(Integer.toString(Utcgga));
    		      Latitude = getCoordinates(ele[2]);
    		      if(ele[3] == "S")   Latitude = -Latitude;
    		      gt2.setText(Double.toString(Latitude));
    		      Longitude = getCoordinates(ele[4]);
    		      gt3.setText(Double.toString(Longitude));
    		      gt4.setText("1");
    		      nos = Integer.parseInt(ele[7]);
    		      gt5.setText(Integer.toString(nos));
    		      HDOP = Double.parseDouble(ele[8]);
    		      gt6.setText(Double.toString(HDOP));
    		      altitude = Double.parseDouble(ele[9]);
    		      gt7.setText(Double.toString(altitude));
    		      heigth = Double.parseDouble(ele[11]);
    		      gt8.setText(Double.toString(heigth));
    		  }
    		  catch(Exception e1){
    			  JOptionPane.showMessageDialog(null,"!!Check The Code!!");
    		  }
    	  }
      });
      b4.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  String str;
    		  double KNOTS_TO_MPS = 0.514444444;
    		  int Utc;
    		  double lat;
    		  double lon;
    		  double speed;
    		  double truecourse;
    		  int date;
    		  double mgv;
    		  try {
    			  str = t2.getText();
    			  String delim = "[,]";
    		      String []ele = str.split(delim);
    		      assert(ele[0] == "$GPRMC");
    		      Utc = Integer.parseInt(ele[1]);
    		      rt1.setText(Integer.toString(Utc));
    		      lat = getCoordinates(ele[3]);
    		      if(ele[3] == "S")   lat = -lat;
    		      rt2.setText(Double.toString(lat));
    		      lon = getCoordinates(ele[5]);
    		      rt3.setText(Double.toString(lon));
    		      speed = Double.parseDouble(ele[7])*KNOTS_TO_MPS;
    		      rt4.setText(Double.toString(speed));
    		      truecourse = Double.parseDouble(ele[8]);
    			  rt5.setText(Double.toString(truecourse));
    			  date = Integer.parseInt(ele[9]);
    			  rt6.setText(Integer.toString(date));
    			  mgv = Double.parseDouble(ele[10]);
    			  rt7.setText(Double.toString(mgv));
    		  }
    		  catch(Exception e1){
    			  JOptionPane.showMessageDialog(null,"Enter valid RMC sentence");
    		  }
    	  }
      });
    }
    public static int checksum(String nmea){
      char ch;
      int checksum = 0;
      for(int i=0;i<nmea.length();++i){
        ch = nmea.charAt(i);
        switch(ch){
          case '$':
          break;
          case '*':
            i = nmea.length();
            continue;
          default:
            if(checksum == 0){
              checksum = ch;
            }
            else{
              checksum = checksum^ch;
            }
          break;
        }
      }
      return checksum;
    }
    public static String hexchecksum(int n){
      String hex = "";
      char ch;
      while(n!=0){
        int temp = 0;
        temp=n%16;
        if(temp<10){
          ch =(char)(temp + 48);
          hex = hex + ch;
        }
        else{
          ch = (char)(temp+ 55);
          hex = hex + ch;
        }
        n = n/16;
      }
      String a="";
      for(int i=hex.length()-1;i>=0;i--) {
    	  a = a + hex.charAt(i);
      }
      return a;
    }
    public static boolean Parsegga(String str) {
    	if(isValid(str))
    		return true;
    	return false;
    }
    public static boolean isValid(String str){
        boolean ret = true;
        String delim = "[,]";
        String []ele = str.split(delim);
        if(ele[0].compareTo("$GPGGA") != 0)  ret = false;
        if(Integer.parseInt(ele[6]) == 0)   ret = false;
        if(ele[2].length() < 5) ret = false;
        if(ele[4].length() < 5) ret = false;
        if(Integer.parseInt(ele[7]) == 0)   ret = false;    
        return ret;
    }
    public static double getCoordinates(String str){
        double decimal = 0;
        String degarr;
        String minarr;
        if(str.length() > 5){
            degarr = str.substring(0,(str.length()-1)-5);
            minarr = str.substring((str.length()-1)-5, str.length());
            int deg;
            double mins;
            deg = Integer.parseInt(degarr);
            mins = Double.parseDouble(minarr);
            decimal = degreestodecimal(deg,mins,0);
        }
        return decimal;
    }
    public static double degreestodecimal(int degrees, double minutes, double seconds){
        double ret = 0;
        ret = degrees + minutes/60 + seconds/3600.0;
        return ret;
    }
    
    public static void main(String...s){
    	SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Parser();
            }
        });
    }
}
