echo "Executing program at the Thieves node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirOrdinary
./Thief_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "Thieves Client shutdown."

echo "Transfering data to the Thieves node."
sshpass qwerty ssh sd402@l040101-ws09.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws09.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirOrdinary.zip sd202@l040101-ws09.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Thieves node."
sshpass qwerty ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinary.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws09.ua.pt:test/MuseumHeist/dirOrdinary
sshpass qwerty ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; unzip -uq genclass.zip'
echo "Executing program at the Thieves node."
sshpass qwerty ssh sd402@l040101-ws09.ua.pt 'cd test/MuseumHeist/dirOrdinary ; ./Thief_com_d.sh'
echo "Thieves client shutdown."