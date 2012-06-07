package cars;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



public class serial
{
    public serial()
    {
        super();
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
    public static class SerialReader implements Runnable 
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
                    if(buffer[0] == 'I'){
                        ok=false;
                    }
                    if(buffer[0] != 'A' || buffer[0] != 'C'){
                        return;
                    }
                    if(buffer[0] == 'C'){
                        try {    
                            Cars.breakLine();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(serial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(buffer[0] == 'E'){
                        
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(serial.class.getName()).log(Level.SEVERE, null, ex);
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
    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {   
                boolean ok = true;
                byte c = 'B';
                while(ok){
                        this.out.write(c);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(serial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                                
            }
            catch ( IOException e )
            {
                 e.printStackTrace();
            }            
        }
    }
    
    public static void main ( String arg )
    {
        try
        {
            (new serial()).connect(arg);
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
