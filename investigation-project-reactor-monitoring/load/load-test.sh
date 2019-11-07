#!/bin/sh

ab -c 100 -n 200000 -p create-greeting.json -T application/json http://localhost:8080/greetings
