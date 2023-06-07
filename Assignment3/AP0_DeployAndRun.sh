echo "Transfering data to the AP0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAP0.zip sd402@l040101-ws02.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the AP0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP0.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws02.ua.pt:test/MuseumHeist/dirAP0
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirAP0 ; unzip -uq genclass.zip'
echo "Executing program at the AP0 node."
sshpass -f password ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirAP0 ; ./AP0_com_d.sh sd402'
echo "Assault Party 0 Server shutdown."
