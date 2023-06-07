echo "Executing AP0 node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirAP0
./AP0_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "Assault Party 0 Server shutdown."

echo "Transfering data to the AP0 node."
sshpass qwerty ssh sd402@l040101-ws02.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws02.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirAP0.zip sd402@l040101-ws02.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the AP0 node."
sshpass qwerty ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP0.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws02.ua.pt:test/MuseumHeist/dirAP0
sshpass qwerty ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirAP0 ; unzip -uq genclass.zip'
echo "Executing program at the AP0 node."
sshpass qwerty ssh sd402@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirAP0 ; ./AP0_com_d.sh sd402'
echo "Assault Party 0 Server shutdown."