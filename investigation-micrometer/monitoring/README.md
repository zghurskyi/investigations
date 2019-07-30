# Start Prometheus
```shell script
./prometheus.sh
```
# Start Grafana
```shell script
./grafana.sh
```
# Perform load test
```shell script
ab -n 120000 -c 2 http://localhost:8080/actuator/prometheus
```
