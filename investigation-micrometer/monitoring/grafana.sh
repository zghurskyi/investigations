#!/usr/bin/env bash

#!/bin/sh
docker run -i --net=host \
-p 3030:3000 \
-v $(pwd)/grafana-datasource.yml:/etc/grafana/provisioning/datasources/grafana-datasource.yml \
-v $(pwd)/dashboards/grafana-dashboard.yml:/etc/grafana/provisioning/dashboards/grafana-dashboard.yml \
-v $(pwd)/dashboards/jvmgc-dashboard.json:/etc/grafana/dashboards/jvmgc.json \
-v $(pwd)/dashboards/latency-dashboard.json:/etc/grafana/dashboards/latency.json \
-v $(pwd)/dashboards/processor-dashboard.json:/etc/grafana/dashboards/processor.json \
-v $(pwd)/dashboards/business.json:/etc/grafana/dashboards/business.json \
-v $(pwd)/dashboards/schedulers.json:/etc/grafana/dashboards/schedulers.json \
grafana/grafana:5.4.3