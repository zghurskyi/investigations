
SET CURRENT_DIR=%~dp0

docker run^
 -p 3030:3000^
 -v %CURRENT_DIR%\grafana-datasource.yml:/etc/grafana/provisioning/datasources/grafana-datasource.yml^
 -v %CURRENT_DIR%\dashboards\grafana-dashboard.yml:/etc/grafana/provisioning/dashboards/grafana-dashboard.yml^
 -v %CURRENT_DIR%\dashboards\jvmgc-dashboard.json:/etc/grafana/dashboards/jvmgc.json^
 -v %CURRENT_DIR%\dashboards\latency-dashboard.json:/etc/grafana/dashboards/latency.json^
 -v %CURRENT_DIR%\dashboards\processor-dashboard.json:/etc/grafana/dashboards/processor.json^
 -v %CURRENT_DIR%\dashboards\business.json:/etc/grafana/dashboards/business.json^
 -v %CURRENT_DIR%\dashboards\schedulers.json:/etc/grafana/dashboards/schedulers.json^
 grafana/grafana:5.4.3