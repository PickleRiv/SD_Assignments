CODEBASE="file://"$1"/dirMasterThief/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     clientSide.main.ClientMaster localhost 22000