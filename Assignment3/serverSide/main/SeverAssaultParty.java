package serverSide.main;

import interfaces.AssaultPartyInterface;
import interfaces.Register;
import serverSide.objects.AssaultParty;

import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *    Instantiation and registering of an Assault Party object.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */
public class ServerAssaultParty {
    /**
     *  Flag signaling the end of operations.
     */

    private static boolean end = false;

    /**
     *  Main method.
     *
     *        args[0] - port number for listening to service requests
     *        args[1] - name of the platform where is located the RMI registering service
     *        args[2] - port number where the registering service is listening to service requests
     */

    public static void main (String[] args)
    {
        int ap_id = -1;
        int portNumb = -1;                                             // port number for listening to service requests
        String rmiRegHostName;                                         // name of the platform where is located the RMI registering service
        int rmiRegPortNumb = -1;                                       // port number where the registering service is listening to service requests

        if (args.length != 3)
        { System.out.println ("Wrong number of parameters!");
            System.exit (1);
        }
        try
        { portNumb = Integer.parseInt (args[0]);
        }
        catch (NumberFormatException e)
        { System.out.println ("args[0] is not a number!");
            System.exit (1);
        }
        if ((portNumb < 4000) || (portNumb >= 65536))
        { System.out.println ("args[0] is not a valid port number!");
            System.exit (1);
        }
        if(portNumb == 22416){
            ap_id = 0;
        }else{
            ap_id = 1;
        }
        rmiRegHostName = args[1];
        try
        { rmiRegPortNumb = Integer.parseInt (args[2]);
        }
        catch (NumberFormatException e)
        { System.out.println ("args[2] is not a number!");
            System.exit (1);
        }
        if ((rmiRegPortNumb < 4000) || (rmiRegPortNumb >= 65536))
        { System.out.println ("args[2] is not a valid port number!");
            System.exit (1);
        }

        /* create and install the security manager */

        if (System.getSecurityManager () == null)
            System.setSecurityManager (new SecurityManager ());
        System.out.println ("Security manager was installed!");


        Registry registry = null;                                      // remote reference for registration in the RMI registry service

        try
        { registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
        }
        catch (RemoteException e)
        { System.out.println ("RMI registry creation exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("RMI registry was created!");


        /* instantiate an assault party object */

        AssaultParty AP = new AssaultParty(ap_id);                 // barber shop object
        AssaultPartyInterface APStub = null;                          // remote reference to the barber shop object

        try
        { APStub = (AssaultPartyInterface) UnicastRemoteObject.exportObject (AP, portNumb);
        }
        catch (RemoteException e)
        { System.out.println ("Assault party " + ap_id + " stub generation exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("Stub was generated!");

        /* register it with the general registry service */

        String nameEntryBase = "RegisterHandler";                      // public name of the object that enables the registration
        String nameEntryObject = "";                         // public name of the Assault party object
        // of other remote objects
        if(ap_id == 0){
            nameEntryObject = "AssaultParty0";
        }else{
            nameEntryObject = "AssaultParty1";
        }

        Register reg = null;                                           // remote reference to the object that enables the registration
        // of other remote objects

        try
        { reg = (Register) registry.lookup (nameEntryBase);
        }
        catch (RemoteException e)
        { System.out.println ("RegisterRemoteObject lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("RegisterRemoteObject not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { reg.bind (nameEntryObject, APStub);
        }
        catch (RemoteException e)
        { System.out.println ("Assault party " + ap_id + " registration exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (AlreadyBoundException e)
        { System.out.println ("Assault party already bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("Assault party object was registered!");

        /* wait for the end of operations */

        System.out.println ("Assault party is in operation!");
        try
        { while (!end)
            synchronized (Class.forName ("serverSide.main.ServerAssaultParty"))
            { try
            { (Class.forName ("serverSide.main.ServerAssaultParty")).wait ();
            }
            catch (InterruptedException e)
            { System.out.println ("Assault party main thread was interrupted!");
            }
            }
        }
        catch (ClassNotFoundException e)
        { System.out.println ("The data type ServerAssaultParty was not found (blocking)!");
            e.printStackTrace ();
            System.exit (1);
        }

        /* server shutdown */

        boolean shutdownDone = false;                                  // flag signalling the shutdown of the barber shop service

        try
        { reg.unbind (nameEntryObject);
        }
        catch (RemoteException e)
        { System.out.println ("Assault party deregistration exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("Assault party not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("Assault party was deregistered!");

        try
        { shutdownDone = UnicastRemoteObject.unexportObject (AP, true);
        }
        catch (NoSuchObjectException e)
        { System.out.println ("Assault party unexport exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        if (shutdownDone)
            System.out.println ("Assault party was shutdown!");
    }

    /**
     *  Close of operations.
     */

    public static void shutdown ()
    {
        end = true;
        try
        { synchronized (Class.forName ("serverSide.main.ServerAssaultParty"))
        { (Class.forName ("serverSide.main.ServerAssaultParty")).notify ();
        }
        }
        catch (ClassNotFoundException e)
        { System.out.println ("The data type ServerAssaultParty was not found (waking up)!");
            e.printStackTrace ();
            System.exit (1);
        }
    }
}