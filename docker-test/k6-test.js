import http from 'k6/http'

import {check} from 'k6'

export let options = {
    stages: [
        // Ramp-up from 1 to 5 VUs in 10s
        { duration: "10s", target: 50 },

        // Stay at rest on 100 VUs for 40s
        { duration: "40s", target: 500 },

        // Ramp-down from 10 to 0 VUs for 5s
        { duration: "10s", target: 0 }
    ]
};

export default function (){
    var url = 'http://localhost:8080/address/600'

    const response = http.get(url)

    check(response, {
        'is status is 200: ' : (r) => r.status === 200,
    })

}