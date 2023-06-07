echo "Executing program at the General Repository node."
cd /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3/dirGeneralRepos
./GenRepos_com_alt.sh /Users/lucas/Universidade/Uni4matricula/Semestre2/SD/SD_Assignments/Assignment3
echo "General Repository Server shutdown."

echo "Transfering data to the General Repository node."
sshpass qwerty ssh sd402@l040101-ws10.ua.pt 'mkdir -p test/MuseumHeist'
sshpass qwerty ssh sd402@l040101-ws10.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass qwerty scp dirGeneralRepos.zip sd402@l040101-ws10.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the General Repository node."
sshpass qwerty ssh sd402@l040101-ws10.ua.pt 'cd test/MuseumHeist ; unzip -uq dirGeneralRepos.zip'
sshpass qwerty scp genclass.zip sd402@l040101-ws10.ua.pt:test/MuseumHeist/dirGeneralRepos
sshpass qwerty ssh sd402@l040101-ws10.ua.pt 'cd test/MuseumHeist/dirGeneralRepos ; unzip -uq genclass.zip'
echo "Executing program at the General Repository node."
sshpass qwerty ssh sd402@l040101-ws10.ua.pt 'cd test/MuseumHeist/dirGeneralRepos ; ./generalRepos_com_d.sh sd402'
sshpass qwerty scp sd402@l040101-ws10.ua.pt:test/MuseumHeist/dirGeneralRepos/log.txt .
echo "General Repository Server shutdown."