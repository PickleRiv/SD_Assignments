echo "Transfering data to the RMI Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'mkdir -p Public/classes/interfaces'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'rm -rf Public/classes/interfaces/*'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'mkdir -p Public/classes/commInfra'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'rm -rf Public/classes/commInfra/*'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'mkdir -p Public/classes/clientSide/entities'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'rm -rf Public/classes/clientSide/entities/*'
sshpass -f password scp dirRMIRegistry.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the RMI Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirRMIRegistry.zip'
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRMIRegistry ; cp clientSide/entities/*.class /home/sd402/Public/classes/clientSide/entities; cp interfaces/*.class /home/sd402/Public/classes/interfaces; cp commInfra/*.class /home/sd402/Public/classes/commInfra ; cp set_rmiregistry_d.sh /home/sd402'
echo "Executing program at the RMI Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt './set_rmiregistry_d.sh sd402 22410'