echo "Executing program at the Master node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirMaster
./Master_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "Master Thief Client shutdown."

echo "Transfering data to the Master node."
sshpass qwerty ssh sd402@l040101-ws08.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws08.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirMaster.zip sd202@l040101-ws08.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Master node."
sshpass qwerty ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMaster.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws08.ua.pt:test/MuseumHeist/dirMaster
sshpass qwerty ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; unzip -uq genclass.zip'
echo "Executing program at the Master node."
sshpass qwerty ssh sd402@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; ./Master_com_d.sh'
echo "Master client shutdown."