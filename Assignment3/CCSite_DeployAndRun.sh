echo "Transfering data to the CCSite node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirCCSite.zip sd402@l040101-ws05.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the CCSite node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist ; unzip -uq dirCCSite.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws05.ua.pt:test/MuseumHeist/dirCCSite
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirCCSite ; unzip -uq genclass.zip'
echo "Executing program at the CCSite node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirCCSite ; ./CCSite_com_d.sh sd202'
echo "CCSite Server shutdown."
