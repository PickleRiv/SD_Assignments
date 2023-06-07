echo "Transfering data to the Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password scp dirRegistry.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirRegistry.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist/dirRegistry
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRegistry ; unzip -uq genclass.zip'
echo "Executing program at the Registry node."
sshpass -f password ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRegistry ; ./registry_com_d.sh sd402'
