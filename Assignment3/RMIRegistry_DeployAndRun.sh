echo "Executing program at the RMI Registry node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirOrdinary
./set_rmiregistry_alt.sh 22210

echo "Transfering data to the RMI Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'mkdir -p Public/classes/interfaces'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'rm -rf Public/classes/interfaces/*'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'mkdir -p Public/classes/commInfra'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'rm -rf Public/classes/commInfra/*'
sshpass qwerty scp dirRMIRegistry.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the RMI Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirRMIRegistry.zip'
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRMIRegistry ; cp interfaces/*.class /home/sd402/Public/classes/interfaces; cp commInfra/*.class /home/sd402/Public/classes/commInfra ; cp set_rmiregistry_d.sh /home/sd402'
echo "Executing program at the RMI Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt './set_rmiregistry_d.sh sd402 22211'