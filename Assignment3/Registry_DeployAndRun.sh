echo "Executing program at the Registry node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirRegistry
./registry_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3

echo "Transfering data to the Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty scp dirRegistry.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirRegistry.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws01.ua.pt:test/MuseumHeist/dirRegistry
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRegistry ; unzip -uq genclass.zip'
echo "Executing program at the Registry node."
sshpass qwerty ssh sd402@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirRegistry ; ./registry_com_d.sh sd402'