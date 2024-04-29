import java.rmi.*;

public interface ServerRmiIntf extends Remote {
    public double add(double num1, double num2) throws RemoteException;
    public double sub(double num1, double num2) throws RemoteException;
    public double mul(double num1, double num2) throws RemoteException;  
}

import java.rmi.*;
import java.rmi.server.*;
public class ServerRmiInmpl extends UnicastRemoteObject implements ServerRmiIntf{

    public ServerRmiInmpl() throws RemoteException{
    }
    public double add(double num1, double num2) throws RemoteException
    {
        return num1 + num2;
    }
    public double sub(double num1, double num2) throws RemoteException{
        return num1 - num2;
    }
    public double mul(double num1, double num2) throws RemoteException
    {
        return num1 * num2;
    }
}

import java.rmi.*;
public class ServerRmi {
    public static void main(String args[])
    {
        try {
           ServerRmiInmpl serverimpl = new ServerRmiInmpl();
           Naming.rebind("Server",serverimpl); 
        } catch (Exception e) {
           System.out.println("Exception occured at server" + e.getMessage());
        }
    }
}

import java.rmi.*;
import java.util.*;
public class ClinetRmi {
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    try {
        String serverURL = "rmi://localhost/Server";
        ServerRmiIntf serverIntf = (ServerRmiIntf) Naming.lookup(serverURL);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter first number: ");
        double num2 = sc.nextDouble();

        System.out.println("First number is: "+ num1);
        System.out.println("Second number is: "+ num2);

        System.out.print("------------------- Results -------------");
        System.out.print("Addition is: "+ serverIntf.add(num1, num2));
        System.out.print("Addition is: "+ serverIntf.sub(num1, num2));
        System.out.print("Addition is: "+ serverIntf.mul(num1, num2));
    } catch (Exception e) {
        System.out.println("Exception occured at Client" + e.getMessage());
    }
    sc.close();
  }  
}


/*  javac *.javac
   rmi registry
   java Server
   java Client
*/
