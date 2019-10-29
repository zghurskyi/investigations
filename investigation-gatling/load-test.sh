#!/bin/bash

mvn gatling:test -Dgatling.skip=false -Dgatling.simulationClass=com.oxymorus.greeting.LoadScript