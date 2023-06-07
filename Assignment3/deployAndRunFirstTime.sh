xterm  -T "RMI registry" -hold -e "./RMIRegistry_DeployAndRun.sh" &
sleep 10
xterm  -T "Registry" -hold -e "./Registry_DeployAndRun.sh" &
sleep 10
xterm  -T "Concentration Site" -hold -e "./ConSiteDeployAndRun.sh" &
xterm  -T "Assault Party 0" -hold -e "./AP0_DeployAndRun.sh" &
xterm  -T "Assault Party 1" -hold -e "./AP1_DeployAndRun.sh" &
xterm  -T "Museum" -hold -e "./MuseumDeployAndRun.sh" &
xterm  -T "Collection Site" -hold -e "./CCSite_DeployAndRun.sh" &
sleep 10
xterm  -T "Master Thief" -hold -e "./Master_DeployAndRun.sh" &
xterm  -T "Ordinary Thieves" -hold -e "./Ordinary_DeployAndRun.sh" &
