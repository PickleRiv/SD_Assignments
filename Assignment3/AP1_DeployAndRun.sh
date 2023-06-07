echo "Transfering data to the AP1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAP1.zip sd402@l040101-ws03.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the AP1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP1.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws03.ua.pt:test/MuseumHeist/dirAP1
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'cd test/MuseumHeist/dirAP1 ; unzip -uq genclass.zip'
echo "Executing program at the AP1 node."
sshpass -f password ssh sd402@l040101-ws03.ua.pt 'cd test/MuseumHeist/dirAP1 ; ./AP1_com_d.sh sd402'
echo "Assault Party 1 Server shutdown."
