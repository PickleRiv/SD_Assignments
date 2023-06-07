echo "Executing program at the Museum node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirMuseum
./Museum_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "Museum Server shutdown."

echo "Transfering data to the Museum node."
sshpass qwerty ssh sd402@l040101-ws06.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws06.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirMuseum.zip sd402@l040101-ws06.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Museum node."
sshpass qwerty ssh sd402@l040101-ws06.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMuseum.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws06.ua.pt:test/MuseumHeist/dirMuseum
sshpass qwerty ssh sd402@l040101-ws06.ua.pt 'cd test/MuseumHeist/dirMuseum ; unzip -uq genclass.zip'
echo "Executing program at the Museum node."
sshpass qwerty ssh sd402@l040101-ws06.ua.pt 'cd test/MuseumHeist/dirMuseum ; ./museum_com_d.sh sd402'
echo "Museum Server shutdown."