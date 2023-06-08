echo "Transfering data to the Assault Party 0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAssaultParty0.zip sd402@l040101-ws02.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Assault Party 0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAssaultParty0.zip'
echo "Executing program at the Assault Party 0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirAssaultParty0 ; ./ap0_com_d.sh sd303'
echo "Assault Party 0 Server shutdown."