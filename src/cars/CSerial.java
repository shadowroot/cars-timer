package cars;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CSerial
{
    private CCars cars;
    private String mPortName;
    private String mStatus;
    public CSerial(CCars cars)
    {
         this.cars = cars;
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        
        return list;
    }
    
    public String getStatus(){
        return mStatus;
    }
    
    
    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    /** */
    public class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            boolean ok =true;
            char data = 0;
            char last = 0;
            int len = -1;
            try
            {
                while ( ( data = (char)this.in.read()) > -1 && ok )
                {
                    if(data == 'B' && last != 'B'){
                        cars.nextLap();
                        Logger.getLogger(CSerial.class.getName()).log(Level.INFO, null, "[+] Line break");
                        ok=true;
                        last = data;
                    }
                    else if(data == 'A' && last != 'A'){
                        ok = true;
                        last = data;
                    }
                    //System.out.println(""+data);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CSerial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

    /** */
    public class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            
        }
    }
    
    
}
