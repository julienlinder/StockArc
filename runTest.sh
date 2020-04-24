#!/bin/bash

Xvfb :99 &
export local_addr="127.0.0.1:8080"

/opt/katalonstudio/katalon --args -noSplash -runMode=console \
	-projectPath="StockArcKatalon" -retry=0 -testSuitePath="Test Suites/Test_RegressionTest" \
	-executionProfile="default" -browserType="Chrome (headless)"