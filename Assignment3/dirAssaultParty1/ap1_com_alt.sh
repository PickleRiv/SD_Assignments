CODEBASE="file://"$1"/dirAssaultParty0/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     serverSide.main.ServerAssaultParty 22011 localhost 22000