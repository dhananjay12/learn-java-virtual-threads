## Prometheus and Grafana

Setup taken from - https://github.com/vegasbrianc/prometheus

Prometheus is configured to fetch metrics from services.

Prometheus endpoint - http://localhost:9090

Prometheus targets - http://localhost:9090/targets

Grafana - http://localhost:3000/

Grafana is setup to use the Prometheus as datasource. A dashboard too will be 
imported (https://grafana.com/grafana/dashboards/4701).

Note - Had to replace `${DS_PROMETHEUS` to `DS_PROMETHEUS` from the default dashboard json. 
Importing directly wont have this issue.
