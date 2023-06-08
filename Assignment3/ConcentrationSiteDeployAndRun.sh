echo "Transfering data to the Thieves Concentration Site node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirConcentrationSite.zip sd402@l040101-ws07.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Thieves Concentration Site node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirConcentrationSite.zip'
echo "Executing program at the Thieves Concentration Site node."
sshpass -f password ssh sd402@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirConcentrationSite ; ./CS_com_d.sh sd402'
echo "Thieves Concentration Site Server shutdown."