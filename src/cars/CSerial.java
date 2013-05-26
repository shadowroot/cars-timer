package cars;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CSerial
{
    private CCars cars;
    public CSerial(CCars cars)
    {
         this.cars = cars;
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
            byte[] buffer = new byte[1];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 && ok )
                {
                    if(buffer[0] == 'B'){
                        Logger.getLogger(CSerial.class.getName()).log(Level.INFO, null, "[+] Sync serial");
                    }
                    else if(buffer[0] == 'A' ){
                        cars.nextBreak();
                        Logger.getLogger(CSerial.class.getName()).log(Level.INFO, null, "[+] Line break");
                        ok=true;
                    }
                    else{
                        ok = false;
                        System.err.println("Problem");
                    }
                    try {
                        Thread.sleep(100);
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
