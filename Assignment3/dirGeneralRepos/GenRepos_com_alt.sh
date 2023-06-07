CODEBASE="file://"$1"/dirGeneralRepos/"
java -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     -cp "../genclass.jar:."\
     serverSide.main.ServerGeneralRepos 22214 localhost 22219