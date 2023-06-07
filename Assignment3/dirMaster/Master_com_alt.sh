CODEBASE="file://"$1"/dirMaster/"
java  -cp "../genclass.jar:."\
      -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     clientSide.main.ClientMaster localhost 22217
