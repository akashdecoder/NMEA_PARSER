import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class GGA{
    int MINUTE_LENGTH = 5;
    int Utc;
    double Latitude;
    double Longitude;
    int quality;
    double altitude;
    double heigth;
    int nos;

    public GGA(){
        Utc = 0;
        Latitude = 0;
        Longitude = 0;
        quality = 0;
        altitude = 0;
        heigth = 0;
        nos = 0;
    }
    public GGA(String str){
        if(isValid(str))
            setGGA(str);
    }
    public boolean isValid(String str){
        boolean ret = true;
        String delim = "[,]";
        String []ele = str.split(delim);
        if(ele[0].compareTo("$GPGGA") != 0)  ret = false;
        if(Integer.parseInt(ele[6]) == 0)   ret = false;
        if(ele[2].length() < MINUTE_LENGTH) ret = false;
        if(ele[4].length() < MINUTE_LENGTH) ret = false;
        if(Integer.parseInt(ele[7]) == 0)   ret = false;    
        return ret;
    }
    public void setGGA(String str){
        String delim = "[,]";
        String []ele = str.split(delim);
        assert(ele[0] == "$GPGGA");
        this.Utc = Integer.parseInt(ele[1]);
        System.out.println("UTC: " + Utc);
        this.Latitude = getCoordinates(ele[2]);
        if(ele[3] == "S")   this.Latitude = -this.Latitude;
        System.out.println("Latitude: " + Latitude + ele[3]);
        this.Longitude = getCoordinates(ele[4]);
        System.out.println("Longitude: " + Longitude + ele[5]);
        this.nos = Integer.parseInt(ele[7]);
        System.out.println("No of Satellites: " + nos);
        System.out.println("HDOP: " + ele[8]);
        this.altitude = Double.parseDouble(ele[9]);
        System.out.println("Altitude: " + altitude + ele[10]);
        this.heigth = Double.parseDouble(ele[11]);
        System.out.println("Height: " + heigth + ele[12]);
    }
    public double getCoordinates(String str){
        double decimal = 0;
        String degarr;
        String minarr;
        if(str.length() > MINUTE_LENGTH){
            degarr = str.substring(0,(str.length()-1)-MINUTE_LENGTH);
            minarr = str.substring((str.length()-1)-MINUTE_LENGTH, str.length());
            int deg;
            double mins;
            deg = Integer.parseInt(degarr);
            mins = Double.parseDouble(minarr);
            decimal = degreestodecimal(deg,mins,0);
        }
        return decimal;
    }
    public double degreestodecimal(int degrees, double minutes, double seconds){
        double ret = 0;
        ret = degrees + minutes/60 + seconds/3600.0;
        return ret;
    }
}
class RMC{
    int MINUTE_LENGTH = 5;
    double KNOTS_TO_MPS = 0.514444444;
    int Utc;
    double lat;
    double lon;
    double speed;
    double truecourse;
    int date;
    double mgv;

    public RMC(){
        Utc = 0;
        lat = 0;
        lon = 0;
        speed = 0;
        truecourse = 0;
        date = 0;
        mgv = 0;
    }
    public RMC(String str){
        if(isValid(str))
            setRMC(str);
    }
    public boolean isValid(String str){
        boolean ret = true;
        String delim = "[,]";
        String []ele = str.split(delim);
        if(ele[0].compareTo("$GPRMC") != 0)  ret = false;
        if(ele[2] == "A")   ret = false;
        if(ele[3].length() < MINUTE_LENGTH) ret = false;
        if(ele[5].length() < MINUTE_LENGTH) ret = false;    
        return ret;
    }
    public void setRMC(String str){
        String delim = "[,]";
        String []ele = str.split(delim);
        assert(ele[0] == "$GPRMC");
        this.Utc = Integer.parseInt(ele[1]);
        System.out.println("UTC: " + Utc);
        this.lat = getCoordinates(ele[3]);
        if(ele[3] == "S")   this.lat = -this.lat;
        System.out.println("Latitude: " + lat + ele[4]);
        this.lon = getCoordinates(ele[5]);
        System.out.println("Longitude: " + lon + ele[6]);
        this.speed = Double.parseDouble(ele[7])*KNOTS_TO_MPS;
        System.out.println("Speed in Knots: " + speed);
        this.truecourse = Double.parseDouble(ele[8]);
        System.out.println("TrueCourse: " + truecourse);
        this.date = Integer.parseInt(ele[9]);
        System.out.println("Date: " + date);
        this.mgv = Double.parseDouble(ele[10]);
        System.out.println("Variation: " + mgv + ele[11]);
    }
    public double getCoordinates(String str){
        double decimal = 0;
        String degarr;
        String minarr;
        if(str.length() > MINUTE_LENGTH){
            degarr = str.substring(0,(str.length()-1)-MINUTE_LENGTH);
            minarr = str.substring((str.length()-1)-MINUTE_LENGTH, str.length());
            int deg;
            double mins;
            deg = Integer.parseInt(degarr);
            mins = Double.parseDouble(minarr);
            decimal = degreestodecimal(deg,mins,0);
        }
        return decimal;
    }
    public double degreestodecimal(int degrees, double minutes, double seconds){
        double ret = 0;
        ret = degrees + minutes/60 + seconds/3600.0;
        return ret;
    }
}
public class Parser extends JFrame{
    JFrame f;
    JLabel l1,l2;
    JLabel l,l3;
    JTextField t1,t2,t3,t4;
    JButton b1, b2;
    
    public Parser(){
      f = new JFrame("NMEA GPS Parser");
      f.setLayout(null);
      f.setSize(800,300);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      l1 = new JLabel("");
      l1.setText("GGA:");
      l2 = new JLabel("");
      l2.setText("RMC:");
      t1 = new JTextField();
      t2 = new JTextField();
      b1 = new JButton("Parse GGA");
      b2 = new JButton("Parse RMC");
      l = new JLabel("GGA Checksum: ");
      t3 = new JTextField();
      l3 = new JLabel("RMS Checksum: ");
      t4 = new JTextField();
      l1.setBounds(60,30,40,30);
      t1.setBounds(100,30,500,30);
      l2.setBounds(60,60,40,30);
      t2.setBounds(100,60,500,30);
      b1.setBounds(100,100,120,30);
      b2.setBounds(480,100,120,30);
      l.setBounds(100,140,200,30);
      t3.setBounds(310,140,200,30);
      l3.setBounds(100,180,200,30);
      t4.setBounds(310,180,200,30);
      f.add(l1);
      f.add(t1);
      f.add(l2);
      f.add(t2);
      f.add(b1);
      f.add(b2);
      f.add(l);
      f.add(t3);
      f.add(l3);
      f.add(t4);
      
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
    public static void main(String...s){
    	SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Parser();
            }
        });
        /*Scanner in = new Scanner(System.in);
        String str,str1;
        System.out.print("Enter a GGA data: ");
        str = in.next();
        GGA obj = new GGA();
        GGA obj1 = new GGA(str);
        System.out.println("CheckSum: " + hexchecksum(checksum(str)));
        System.out.print("Enter a RMC data: ");
        str1 = in.next();
        RMC obj2 = new RMC();
        RMC obj3 = new RMC(str1);
        System.out.println("CheckSum: " + hexchecksum(checksum(str1)));*/
    }
}
