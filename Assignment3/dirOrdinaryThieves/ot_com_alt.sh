CODEBASE="file://"$1"/dirOrdinaryThief/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     clientSide.main.ClientThief localhost 22000