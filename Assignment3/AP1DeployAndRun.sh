echo "Transfering data to the Assault Party 1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAssaultParty1.zip sd402@l040101-ws03.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Assault Party 1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAssaultParty1.zip'
echo "Executing program at the Assault Party 1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'cd test/MuseumHeist/dirAssaultParty1 ; ./ap1_com_d.sh sd402'
echo "Assault Party 1 Server shutdown."