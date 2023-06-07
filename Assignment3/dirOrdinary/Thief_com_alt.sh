CODEBASE="file://"$1"/dirOrdinary/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     -cp "../genclass.jar:."\
     clientSide.main.ClientThief localhost 22211 logger