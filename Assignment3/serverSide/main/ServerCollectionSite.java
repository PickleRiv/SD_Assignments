package serverSide.main;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;
import serverSide.objects.*;
import interfaces.*;

/**
 *    Instantiation and registering of a CCS object.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */

public class ServerCollectionSite
{
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


        CollectionSite cSite = new CollectionSite();                 // Master thief control and collection site object
        cSiteInterface CCSstub = null;                          // remote reference to the Master thief control and collection site object

        try
        { CCSstub = (cSiteInterface) UnicastRemoteObject.exportObject (cSite, portNumb);
        }
        catch (RemoteException e)
        { System.out.println ("Master thief control and collection site stub generation exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("Stub was generated!");

        /* register it with the general registry service */

        String nameEntryBase = "RegisterHandler";                      // public name of the object that enables the registration
        // of other remote objects
        String nameEntryObject = "cSite";                         // public name of the CCS object
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
        { reg.bind (nameEntryObject, CCSstub);
        }
        catch (RemoteException e)
        { System.out.println ("CCS registration exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (AlreadyBoundException e)
        { System.out.println ("CCS already bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("CCS object was registered!");

        /* wait for the end of operations */

        System.out.println ("CCS is in operation!");
        try
        { while (!end)
            synchronized (Class.forName ("serverSide.main.ServerCollectionSite"))
            { try
            { (Class.forName ("serverSide.main.ServerCollectionSite")).wait ();
            }
            catch (InterruptedException e)
            { System.out.println ("CCS main thread was interrupted!");
            }
            }
        }
        catch (ClassNotFoundException e)
        { System.out.println ("The data type ServerCollectionSite was not found (blocking)!");
            e.printStackTrace ();
            System.exit (1);
        }

        /* server shutdown */

        boolean shutdownDone = false;                                  // flag signalling the shutdown of the CCS service

        try
        { reg.unbind (nameEntryObject);
        }
        catch (RemoteException e)
        { System.out.println ("CCS deregistration exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("CCS not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        System.out.println ("CCS was deregistered!");

        try
        { shutdownDone = UnicastRemoteObject.unexportObject (cSite, true);
        }
        catch (NoSuchObjectException e)
        { System.out.println ("CCS unexport exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        if (shutdownDone)
            System.out.println ("Control and Collection Site was shutdown!");
    }

    /**
     *  Close of operations.
     */

    public static void shutdown ()
    {
        end = true;
        try
        { synchronized (Class.forName ("serverSide.main.ServerCollectionSite"))
        { (Class.forName ("serverSide.main.ServerCollectionSite")).notify ();
        }
        }
        catch (ClassNotFoundException e)
        { System.out.println ("The data type ServerCollectionSite was not found (waking up)!");
            e.printStackTrace ();
            System.exit (1);
        }
    }
}