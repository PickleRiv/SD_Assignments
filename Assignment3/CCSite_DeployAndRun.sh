echo "Executing program at the CCSite node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirCCSite
./CCSite_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "CCSite Server shutdown."

echo "Transfering data to the CCSite node."
sshpass qwerty ssh sd402@l040101-ws05.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws05.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirCCSite.zip sd402@l040101-ws05.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the CCSite node."
sshpass qwerty ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist ; unzip -uq dirCCSite.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws05.ua.pt:test/MuseumHeist/dirCCSite
sshpass qwerty ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirCCSite ; unzip -uq genclass.zip'
echo "Executing program at the CCSite node."
sshpass qwerty ssh sd402@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirCCSite ; ./CCSite_com_d.sh sd202'
echo "CCSite Server shutdown."