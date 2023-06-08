echo "Transfering data to the Ordinaries node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirOrdinaryThieves.zip sd402@l040101-ws09.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Ordinary node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinaryThieves.zip'
echo "Executing program at the Ordinary node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinaryThieves ; ./ot_com_d.sh'
echo "Ordinaries client shutdown."