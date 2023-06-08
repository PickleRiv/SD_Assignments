echo "Transfering data to the Master Control Collection Site node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirCollectionSite.zip sd402@l040101-ws05.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Master Control Collection Site node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist ; unzip -uq dirCollectionSite.zip'
echo "Executing program at the Master Control Collection Site node."
sshpass -f password ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirCollectionSite ; ./CCS_com_d.sh sd402'
echo "Master Control Collection Site Server shutdown."