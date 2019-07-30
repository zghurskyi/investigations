#!/usr/bin/env bash

#!/bin/sh
docker run -i --net=host \
-p 3000:3000 \
-v $(pwd)/grafana-datasource.yml:/etc/grafana/provisioning/datasources/grafana-datasource.yml \
-v $(pwd)/dashboards/grafana-dashboard.yml:/etc/grafana/provisioning/dashboards/grafana-dashboard.yml \
-v $(pwd)/dashboards/jvmgc-dashboard.json:/etc/grafana/dashboards/jvmgc.json \
-v $(pwd)/dashboards/latency-dashboard.json:/etc/grafana/dashboards/latency.json \
-v $(pwd)/dashboards/processor-dashboard.json:/etc/grafana/dashboards/processor.json \
grafana/grafana:5.1.0
