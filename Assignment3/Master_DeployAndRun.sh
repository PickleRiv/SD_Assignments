echo "Transfering data to the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirMaster.zip sd202@l040101-ws08.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMaster.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws08.ua.pt:test/MuseumHeist/dirMaster
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; unzip -uq genclass.zip'
echo "Executing program at the Master node."
sshpass -f password ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; ./Master_com_d.sh'
echo "Master client shutdown."
