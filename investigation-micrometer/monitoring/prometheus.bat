SET CURRENT_DIR=%~dp0

docker run^
 -p 9090:9090^
 -v %CURRENT_DIR%\prometheus.yml:/etc/prometheus/prometheus.yml^
 prom/prometheus:v2.13.1