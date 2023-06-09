echo "Compiling source code."
javac --release 8 -cp */*/*.java


echo "Distributing intermediate code to the different execution environments."

echo "RMI registry"
rm -rf dirRMIRegistry/interfaces dirRMIRegistry/commInfra
mkdir -p dirRMIRegistry/interfaces dirRMIRegistry/commInfra dirRMIRegistry/clientSide/entities
cp interfaces/*.class dirRMIRegistry/interfaces
cp commInfra/*.class dirRMIRegistry/commInfra
cp clientSide/entities/*.class dirRMIRegistry/clientSide/entities

echo "Register Remote Objects"
rm -rf dirRegistry/serverSide dirRegistry/interfaces
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces

echo "Concentration Site"
rm -rf dirConcentrationSite/serverSide dirConcentrationSite/clientSide dirConcentrationSite/interfaces dirConcentrationSite/commInfra
mkdir -p dirConcentrationSite/serverSide dirConcentrationSite/serverSide/main dirConcentrationSite/serverSide/objects dirConcentrationSite/interfaces dirConcentrationSite/clientSide dirConcentrationSite/clientSide/entities dirConcentrationSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerConcentrationSite.class dirConcentrationSite/serverSide/main
cp serverSide/objects/OrdinaryThievesCS.class dirConcentrationSite/serverSide/objects
cp interfaces/*.class dirConcentrationSite/interfaces
cp clientSide/entities/MasterThiefStates.class clientSide/entities/ThiefStates.class dirConcentrationSite/clientSide/entities
cp commInfra/*.class dirConcentrationSite/commInfra

echo "Assault Party 0"
rm -rf dirAssaultParty0/serverSide dirAssaultParty0/clientSide dirAssaultParty0/interfaces dirAssaultParty0/commInfra
mkdir -p dirAssaultParty0/serverSide dirAssaultParty0/serverSide/main dirAssaultParty0/serverSide/objects dirAssaultParty0/interfaces dirAssaultParty0/clientSide dirAssaultParty0/clientSide/entities dirAssaultParty0/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerAssaultParty.class dirAssaultParty0/serverSide/main
cp serverSide/objects/AssaultParty.class dirAssaultParty0/serverSide/objects
cp interfaces/*.class dirAssaultParty0/interfaces
cp clientSide/entities/MasterThiefStates.class clientSide/entities/ThiefStates.class dirAssaultParty0/clientSide/entities
cp commInfra/*.class dirAssaultParty0/commInfra

echo "Assault Party 1"
rm -rf dirAssaultParty1/serverSide dirAssaultParty1/clientSide dirAssaultParty1/interfaces dirAssaultParty1/commInfra
mkdir -p dirAssaultParty1/serverSide dirAssaultParty1/serverSide/main dirAssaultParty1/serverSide/objects dirAssaultParty1/interfaces dirAssaultParty1/clientSide dirAssaultParty1/clientSide/entities dirAssaultParty1/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerAssaultParty.class dirAssaultParty1/serverSide/main
cp serverSide/objects/AssaultParty.class dirAssaultParty1/serverSide/objects
cp interfaces/*.class dirAssaultParty1/interfaces
cp clientSide/entities/MasterThiefStates.class clientSide/entities/ThiefStates.class dirAssaultParty1/clientSide/entities
cp commInfra/*.class dirAssaultParty1/commInfra

echo "Collection Site"
rm -rf dirCollectionSite/serverSide dirCollectionSite/clientSide dirCollectionSite/interfaces dirCollectionSite/commInfra
mkdir -p dirCollectionSite/serverSide dirCollectionSite/serverSide/main dirCollectionSite/serverSide/objects dirCollectionSite/interfaces dirCollectionSite/clientSide dirCollectionSite/clientSide/entities dirCollectionSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerCollectionSite.class dirCollectionSite/serverSide/main
cp serverSide/objects/MasterThiefCCS.class dirCollectionSite/serverSide/objects
cp interfaces/*.class dirCollectionSite/interfaces
cp clientSide/entities/MasterThiefStates.class clientSide/entities/ThiefStates.class dirCollectionSite/clientSide/entities
cp commInfra/*.class dirCollectionSite/commInfra

echo "Museum"
rm -rf dirMuseum/serverSide dirMuseum/clientSide dirMuseum/interfaces dirMuseum/commInfra
mkdir -p dirMuseum/serverSide dirMuseum/serverSide/main dirMuseum/serverSide/objects dirMuseum/interfaces dirMuseum/clientSide dirMuseum/clientSide/entities dirMuseum/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerMuseum.class dirMuseum/serverSide/main
cp serverSide/objects/Museum.class dirMuseum/serverSide/objects
cp interfaces/*.class dirMuseum/interfaces
cp clientSide/entities/*.class dirMuseum/clientSide/entities
cp commInfra/*.class dirMuseum/commInfra

echo "Master"
rm -rf dirMasterThief/serverSide dirMasterThief/clientSide dirMasterThief/interfaces dirMasterThief/commInfra
mkdir -p dirMasterThief/serverSide dirMasterThief/serverSide/main dirMasterThief/clientSide dirMasterThief/clientSide/main dirMasterThief/clientSide/entities dirMasterThief/interfaces dirMasterThief/commInfra
cp serverSide/main/SimulPar.class dirMasterThief/serverSide/main
cp clientSide/main/ClientMaster.class dirMasterThief/clientSide/main
cp clientSide/entities/*.class dirMasterThief/clientSide/entities
cp interfaces/*.class dirMasterThief/interfaces
cp commInfra/*.class dirMasterThief/commInfra

echo "Ordinaries"
rm -rf dirOrdinaryThieves/serverSide dirOrdinaryThieves/clientSide dirOrdinaryThieves/interfaces dirOrdinaryThieves/commInfra
mkdir -p dirOrdinaryThieves/serverSide dirOrdinaryThieves/serverSide/main dirOrdinaryThieves/clientSide dirOrdinaryThieves/clientSide/main dirOrdinaryThieves/clientSide/entities dirOrdinaryThieves/interfaces dirOrdinaryThieves/commInfra
cp serverSide/main/SimulPar.class dirOrdinaryThieves/serverSide/main
cp clientSide/main/ClientThief.class dirOrdinaryThieves/clientSide/main
cp clientSide/entities/*.class dirOrdinaryThieves/clientSide/entities
cp interfaces/*.class dirOrdinaryThieves/interfaces
cp commInfra/*.class dirOrdinaryThieves/commInfra

echo "Compressing execution environments."

echo "RMI registry"
rm -f  dirRMIRegistry.zip
zip -rq dirRMIRegistry.zip dirRMIRegistry

echo "Register Remote Objects"
rm -f  dirRegistry.zip
zip -rq dirRegistry.zip dirRegistry

echo "General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos

echo "Thiefs Concentration Site"
rm -f  dirConcentrationSite.zip
zip -rq dirConcentrationSite.zip dirConcentrationSite

echo "Assault Party 0"
rm -f  dirAssaultParty0.zip
zip -rq dirAssaultParty0.zip dirAssaultParty0

echo "Assault Party 1"
rm -f  dirAssaultParty1.zip
zip -rq dirAssaultParty1.zip dirAssaultParty1

echo "Museum"
rm -f  dirMuseum.zip
zip -rq dirMuseum.zip dirMuseum

echo "Master Control Collection Site"
rm -f  dirCollectionSite.zip
zip -rq dirCollectionSite.zip dirCollectionSite

echo "Master"
rm -f  dirMasterThief.zip
zip -rq dirMasterThief.zip dirMasterThief

echo "Thieves"
rm -f  dirOrdinaryThieves.zip
zip -rq dirOrdinaryThieves.zip dirOrdinaryThieves