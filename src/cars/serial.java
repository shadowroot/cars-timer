package cars;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;

public class serial
{
    private static int baudrate=9600;
    
    public serial(){
        init();
    }
  
public static void input(){
    
}

public static void output(){
    
}

public static void init()
{
    try
    {
    CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
    if (portIdentifier.isCurrentlyOwned())
    {
    System.out.println("Port in use!");
    }
    else {
    System.out.println(portIdentifier.getName());

    SerialPort serialPort = (SerialPort) portIdentifier.open("ListPortClass", 300);
    int b = serialPort.getBaudRate();
    System.out.println(Integer.toString(b));
    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                InputStream mInputFromPort;
                try (OutputStream mOutputToPort = serialPort.getOutputStream()) {
                    mInputFromPort = serialPort.getInputStream();
                    String mValue = "A";
                    System.out.println("beginning to Write . \r\n");
                    mOutputToPort.write(mValue.getBytes());
                    System.out.println("AT Command Written to Port. \r\n");
                    mOutputToPort.flush();
                    System.out.println("Waiting for Reply \r\n");
                    Thread.sleep(10);
                    byte mBytesIn [] = new byte[1];
                    mInputFromPort.read(mBytesIn);
                    mInputFromPort.read(mBytesIn);
                    String value = new String(mBytesIn);
                    System.out.println("Response from Serial Device: "+value);
                }
    mInputFromPort.close();
    }
    }
    catch (Exception ex)
    {
    System.out.println("Exception : " + ex.getMessage());
    }

    }
}