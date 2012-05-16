package cars;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.util.Enumeration;


public class test {

    private static SerialPort p;
    
    public test(){
        main();
    }

    /**
     * @param args
     */
    public static void main() 
    {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        
        while(ports.hasMoreElements())
        {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            System.out.print(port.getName() + " -> " + port.getCurrentOwner() + " -> ");
            switch(port.getPortType())
            {
                case CommPortIdentifier.PORT_PARALLEL:
                    System.out.println("parell");
                break;
                case CommPortIdentifier.PORT_SERIAL:
                    //System.out.println("serial");
                try {
                    p = (SerialPort) port.open("core", 1000);
                    int baudRate = 57600; // 57600bps
                    p.setSerialPortParams(
                            baudRate,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                } catch (PortInUseException e) {
                    System.out.println(e.getMessage());
                } catch (UnsupportedCommOperationException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
        System.out.println("stop");
    }
}
