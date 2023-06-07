CODEBASE="file://"$1"/dirConSite/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     -cp "../genclass.jar:."\
     serverSide.main.ServerConSite 22213 localhost 22219