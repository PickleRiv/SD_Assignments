echo "Transfering data to the ConSite node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirConSite.zip sd402@l040101-ws07.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the ConSite node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirConSite.zip'
sshpass -f password scp genclass.zip sd402@l040101-ws07.ua.pt:test/MuseumHeist/dirConSite
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirConSite ; unzip -uq genclass.zip'
echo "Executing program at the ConSite node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirConSite ; ./ConSite_com_d.sh sd402'
echo "ConSite Server shutdown."
