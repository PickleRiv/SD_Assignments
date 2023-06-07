echo "Transfering data to the Thieves node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirOrdinary.zip sd202@l040101-ws09.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Thieves node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinary.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws09.ua.pt:test/MuseumHeist/dirOrdinary
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; unzip -uq genclass.zip'
echo "Executing program at the Thieves node."
sshpass -f password ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; ./Thief_com_d.sh'
echo "Thieves client shutdown."
