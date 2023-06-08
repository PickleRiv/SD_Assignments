echo "Transfering data to the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirMasterThief.zip sd402@l040101-ws08.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMasterThief.zip'
echo "Executing program at the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMasterThief ; ./mt_com_d.sh'
echo "Master client shutdown."