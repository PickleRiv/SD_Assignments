xterm  -T "Concentration Site" -hold -e "./ConcentrationSiteDeployAndRun.sh" &
xterm  -T "Assault Party 0" -hold -e "./AP0DeployAndRun.sh" &
xterm  -T "Assault Party 1" -hold -e "./AP1DeployAndRun.sh" &
xterm  -T "Museum" -hold -e "./MuseumDeployAndRun.sh" &
xterm  -T "Collection Site" -hold -e "./CollectionSiteDeployAndRun.sh" &
sleep 10
xterm  -T "Master" -hold -e "./MasterDeployAndRun.sh" &
xterm  -T "Thieves" -hold -e "./ThievesDeployAndRun.sh" &

# Registry                          - sd402@l040101-ws01.ua.pt
# Concentration Site                - sd402@l040101-ws07.ua.pt
# Assault Party 0                   - sd402@l040101-ws02.ua.pt
# Assault Party 1                   - sd402@l040101-ws03.ua.pt
# Museum                            - sd402@l040101-ws06.ua.pt
# Collection Site                   - sd402@l040101-ws05.ua.pt
# Master                            - sd402@l040101-ws08.ua.pt
# Thieves                           - sd402@l040101-ws09.ua.pt