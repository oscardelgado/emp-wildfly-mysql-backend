#!/bin/sh
echo "Starting maintenance..."
mysql -u $BD_USER -p'$BD_PW' \
        -h us-cdbr-iron-east-03.cleardb.net -P 3306 \
        -D heroku_a304a837b2c7d20 \
        --execute="select count(*) from heroku_a304a837b2c7d20.ExportPOJO;"
echo "Done maintenance"
