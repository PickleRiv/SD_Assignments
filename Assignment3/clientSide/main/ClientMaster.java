package clientSide.main;

import clientSide.entities.MasterThief;
import interfaces.*;
import serverSide.main.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *    Client side of the Heist to the Museum (Master thief).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */

public class ClientMasterThief {
    /**
     *  Main method.
     *
     *    @param args runtime arguments
     *        args[0] - name of the platform where is located the RMI registering service
     *        args[1] - port number where the registering service is listening to service requests
     */

    public static void main (String [] args)
    {
        String rmiRegHostName;                                         // name of the platform where is located the RMI registering service
        int rmiRegPortNumb = -1;                                       // port number where the registering service is listening to service requests


        /* getting problem runtime parameters */

        if (args.length != 2)
        { System.out.println ("Wrong number of parameters!");
            System.exit (1);
        }
        rmiRegHostName = args[0];
        try
        { rmiRegPortNumb = Integer.parseInt (args[1]);
        }
        catch (NumberFormatException e)
        { System.out.println ("args[1] is not a number!");
            System.exit (1);
        }
        if ((rmiRegPortNumb < 4000) || (rmiRegPortNumb >= 65536))
        { System.out.println ("args[1] is not a valid port number!");
            System.exit (1);
        }

        /* problem initialization */

        AssaultPartyInterface[] APStub = new AssaultPartyInterface[2];                          // remote reference to the Assault Party object
        String nameEntryAP0 = "AssaultParty0";                     // public name of the AP0 object
        String nameEntryAP1 = "AssaultParty1";                     // public name of the AP1 object
        String nameEntryCSite = "CollectionSite";                     // public name of the CCS object
        CollectionSiteInterface cSiteStub = null;                          // remote reference to the CCS object
        String nameEntryConSite = "ConcentrationSite";                     // public name of the CS object
        ConcentrationSiteInterface conSiteStub = null;                          // remote reference to the CS object
        String nameEntryMuseum = "Museum";                     // public name of the Museum object
        MuseumInterface MuseumStub = null;                          // remote reference to the Museum object
        Registry registry = null;                                      // remote reference for registration in the RMI registry service
        MasterThief master;                    // Master thief

        try
        { registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
        }
        catch (RemoteException e)
        { System.out.println ("RMI registry creation exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { APStub[0] = (AssaultPartyInterface) registry.lookup (nameEntryAP0);
        }
        catch (RemoteException e)
        { System.out.println ("AP0 lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("AP0 not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { APStub[1] = (AssaultPartyInterface) registry.lookup (nameEntryAP1);
        }
        catch (RemoteException e)
        { System.out.println ("AP1 lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("AP1 not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { conSiteStub = (ConcentrationSiteInterface) registry.lookup (nameEntryConSite);
        }
        catch (RemoteException e)
        { System.out.println ("CS lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("CS not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { cSiteStub = (CollectionSiteInterface) registry.lookup (nameEntryCSite);
        }
        catch (RemoteException e)
        { System.out.println ("CCS lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("CCS not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { MuseumStub = (MuseumInterface) registry.lookup (nameEntryMuseum);
        }
        catch (RemoteException e)
        { System.out.println ("Museum lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { System.out.println ("Museum not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }

        master = new MasterThief("Master_Thief", 0, cSiteStub, conSiteStub, APStub, MuseumStub);

        /* start of the simulation */

        master.start();

        /* waiting for the end of the simulation */

        while (master.isAlive ()) {
            Thread.yield ();
            try {
                master.join();
            }catch (InterruptedException ignored) {}
        }

        System.out.println ("The Master thief has terminated.");
        System.out.println();

        try {
        	cSiteStub.shutdown ();

        }
        catch (RemoteException e)
        { System.out.println ("Master thief generator remote exception on CCS shutdown: " + e.getMessage ());
            System.exit (1);
        }
        try {
        	conSiteStub.shutdown();
        } catch (RemoteException e) {
            System.out.println ("Master thief generator remote exception on CS shutdown: " + e.getMessage ());
            System.exit (1);
        }
        try {
            APStub[0].shutdown();
        } catch (RemoteException e) {
            System.out.println ("Master thief generator remote exception on AP0 shutdown: " + e.getMessage ());
            System.exit (1);
        }
        try {
            APStub[1].shutdown();
        } catch (RemoteException e) {
            System.out.println ("Master thief generator remote exception on AP1 shutdown: " + e.getMessage ());
            System.exit (1);
        }
        try {
            MuseumStub.shutdown();
        } catch (RemoteException e) {
            System.out.println ("Master thief generator remote exception on Museum shutdown: " + e.getMessage ());
            System.exit (1);
        }
    }
}