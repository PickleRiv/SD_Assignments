echo "Executing program at the ConSite node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirConSite
./ConSite_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "ConSite Server shutdown."

echo "Transfering data to the ConSite node."
sshpass qwerty ssh sd402@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirConSite.zip sd402@l040101-ws07.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the ConSite node."
sshpass qwerty ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirConSite.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws07.ua.pt:test/MuseumHeist/dirConSite
sshpass qwerty ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirConSite ; unzip -uq genclass.zip'
echo "Executing program at the ConSite node."
sshpass qwerty ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirConSite ; ./ConSite_com_d.sh sd402'
echo "ConSite Server shutdown."