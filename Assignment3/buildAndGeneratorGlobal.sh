echo "Compiling source code."
javac --release 8 -cp ".:./genclass.jar" */*.java */*/*.java


echo "Distributing intermediate code to the different execution environments."

echo "RMI registry"
rm -rf dirRMIRegistry/interfaces dirRMIRegistry/commInfra
mkdir -p dirRMIRegistry/interfaces dirRMIRegistry/commInfra
cp interfaces/*.class dirRMIRegistry/interfaces
cp commInfra/*.class dirRMIRegistry/commInfra


echo "Register Remote Objects"
rm -rf dirRegistry/serverSide dirRegistry/interfaces
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces

#echo "General Repository of Information"
#m -rf dirGeneralRepos/serverSide dirGeneralRepos/clientSide dirGeneralRepos/interfaces dirGeneralRepos/commInfra
#mkdir -p dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/objects dirGeneralRepos/interfaces dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities dirGeneralRepos/commInfra
#cp serverSide/main/SimulPar.class serverSide/main/ServerGeneralRepos.class dirGeneralRepos/serverSide/main
#cp serverSide/objects/GeneralRepo.class dirGeneralRepos/serverSide/objects
#cp interfaces/Register.class interfaces/GeneralReposInterface.class dirGeneralRepos/interfaces
#cp clientSide/entities/MasterStates.class clientSide/entities/ThiefStates.class dirGeneralRepos/clientSide/entities
#cp commInfra/*.class dirGeneralRepos/commInfra

echo "Concentration Site"
rm -rf dirConSite/serverSide dirConSite/clientSide dirConSite/interfaces dirConSite/commInfra
mkdir -p dirConSite/serverSide dirConSite/serverSide/main dirConSite/serverSide/objects dirConSite/interfaces dirConSite/clientSide dirConSite/clientSide/entities dirConSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerConSite.class dirConSite/serverSide/main
cp serverSide/objects/ConSite.class dirConSite/serverSide/objects
cp interfaces/*.class dirConSite/interfaces
cp clientSide/entities/MasterStates.class clientSide/entities/ThiefStates.class dirConSite/clientSide/entities
cp commInfra/*.class dirConSite/commInfra

echo "Assault Party 0"
rm -rf dirAP0/serverSide dirAP0/clientSide dirAP0/interfaces dirAP0/commInfra
mkdir -p dirAP0/serverSide dirAP0/serverSide/main dirAP0/serverSide/objects dirAP0/interfaces dirAP0/clientSide dirAP0/clientSide/entities dirAP0/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerAssaultParty.class dirAP0/serverSide/main
cp serverSide/objects/AP0.class dirAP0/serverSide/objects
cp interfaces/*.class dirAP0/interfaces
cp clientSide/entities/MasterStates.class clientSide/entities/ThiefStates.class dirAP0/clientSide/entities
cp commInfra/*.class dirAP0/commInfra

echo "Assault Party 1"
rm -rf dirAP1/serverSide dirAP1/clientSide dirAP1/interfaces dirAP1/commInfra
mkdir -p dirAP1/serverSide dirAP1/serverSide/main dirAP1/serverSide/objects dirAP1/interfaces dirAP1/clientSide dirAP1/clientSide/entities dirAP1/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerAssaultParty.class dirAP1/serverSide/main
cp serverSide/objects/AP1.class dirAP1/serverSide/objects
cp interfaces/*.class dirAP1/interfaces
cp clientSide/entities/MasterStates.class clientSide/entities/ThiefStates.class dirAP1/clientSide/entities
cp commInfra/*.class dirAP1/commInfra

echo "Master Control and Collection Site"
rm -rf dirCCSite/serverSide dirCCSite/clientSide dirCCSite/interfaces dirCCSite/commInfra
mkdir -p dirCCSite/serverSide dirCCSite/serverSide/main dirCCSite/serverSide/objects dirCCSite/interfaces dirCCSite/clientSide dirCCSite/clientSide/entities dirCCSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerCCSite.class dirCCSite/serverSide/main
cp serverSide/objects/CCSite.class dirCCSite/serverSide/objects
cp interfaces/*.class dirCCSite/interfaces
cp clientSide/entities/MasterStates.class clientSide/entities/ThiefStates.class dirCCSite/clientSide/entities
cp commInfra/*.class dirCCSite/commInfra

echo "Museum"
rm -rf dirMuseum/serverSide dirMuseum/clientSide dirMuseum/interfaces dirMuseum/commInfra
mkdir -p dirMuseum/serverSide dirMuseum/serverSide/main dirMuseum/serverSide/objects dirMuseum/interfaces dirMuseum/clientSide dirMuseum/clientSide/entities dirMuseum/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerMuseum.class dirMuseum/serverSide/main
cp serverSide/objects/Museum.class dirMuseum/serverSide/objects
cp interfaces/*.class dirMuseum/interfaces
cp clientSide/entities/*.class dirMuseum/clientSide/entities
cp commInfra/*.class dirMuseum/commInfra

echo "Master Thief"
rm -rf dirMaster/serverSide dirMaster/clientSide dirMaster/interfaces dirMaster/commInfra
mkdir -p dirMaster/serverSide dirMaster/serverSide/main dirMaster/clientSide dirMaster/clientSide/main dirMaster/clientSide/entities dirMaster/interfaces dirMaster/commInfra
cp serverSide/main/SimulPar.class dirMaster/serverSide/main
cp clientSide/main/ClientMaster.class dirMaster/clientSide/main
cp clientSide/entities/*.class dirMaster/clientSide/entities
cp interfaces/*.class dirMaster/interfaces
cp commInfra/*.class dirMaster/commInfra

echo "Ordinary Thieves"
rm -rf dirOrdinary/serverSide dirOrdinary/clientSide dirOrdinary/interfaces dirOrdinary/commInfra
mkdir -p dirOrdinary/serverSide dirOrdinary/serverSide/main dirOrdinary/clientSide dirOrdinary/clientSide/main dirOrdinary/clientSide/entities dirOrdinary/interfaces dirOrdinary/commInfra
cp serverSide/main/SimulPar.class dirOrdinary/serverSide/main
cp clientSide/main/ClientOrdinaryThief.class dirOrdinary/clientSide/main
cp clientSide/entities/*.class dirOrdinary/clientSide/entities
cp interfaces/*.class dirOrdinary/interfaces
cp commInfra/*.class dirOrdinary/commInfra
echo "Compressing execution environments."

echo "RMI registry"
rm -f  dirRMIRegistry.zip
zip -rq dirRMIRegistry.zip dirRMIRegistry

echo "Register Remote Objects"
rm -f  dirRegistry.zip
zip -rq dirRegistry.zip dirRegistry

#echo "General Repository of Information"
#rm -f  dirGeneralRepos.zip
#zip -rq dirGeneralRepos.zip dirGeneralRepos

echo "Concentration Site"
rm -f  dirConSite.zip
zip -rq dirConSite.zip dirConSite

echo "Assault Party 0"
rm -f  dirAP0.zip
zip -rq dirAP0.zip dirAP0

echo "Assault Party 1"
rm -f  dirAP1.zip
zip -rq dirAP1.zip dirAP1

echo "Museum"
rm -f  dirMuseum.zip
zip -rq dirMuseum.zip dirMuseum

echo "Control Collection Site"
rm -f  dirCCSite.zip
zip -rq dirCCSite.zip dirCCSite

echo "Master"
rm -f  dirMaster.zip
zip -rq dirMaster.zip dirMaster

echo "Ordinaries"
rm -f  dirOrdinary.zip
zip -rq dirOrdinary.zip dirOrdinary
echo "Genclass"
rm -f  genclass.zip
zip -rq genclass.zip genclass.jar
