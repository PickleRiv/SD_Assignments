#xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
#sleep 10
xterm  -T "Thiefs Concentration Site" -hold -e "./ConSite_DeployAndRun.sh" &
xterm  -T "Assault Party 0" -hold -e "./AP0_DeployAndRun.sh" &
xterm  -T "Assault Party 1" -hold -e "./AP1_DeployAndRun.sh" &
xterm  -T "Museum" -hold -e "./Museum_DeployAndRun.sh" &
xterm  -T "Master Control Collection Site" -hold -e "./CCSite_DeployAndRun.sh" &
sleep 10
xterm  -T "Master" -hold -e "./Master_DeployAndRun.sh" &
xterm  -T "Ordinary" -hold -e "./Ordinary_DeployAndRun.sh" &
