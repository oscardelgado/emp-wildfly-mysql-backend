#!/bin/sh
echo "Starting maintenance..."
mysql -u $BD_USER -p$BD_PW \
        -h us-cdbr-east-03.cleardb.com -P 3306 \
        -D heroku_9f31afb0d154a85 \
        --execute="select count(*) from heroku_9f31afb0d154a85.ExportPOJO;delete from heroku_9f31afb0d154a85.ExportPOJO where updateTimestamp < DATE_SUB(curdate(), INTERVAL 21 DAY) and accountName <> 'test';select count(*) from heroku_a304a837b2c7d20.ExportPOJO;optimize table heroku_a304a837b2c7d20.exportpojo;"
echo "Done maintenance"
