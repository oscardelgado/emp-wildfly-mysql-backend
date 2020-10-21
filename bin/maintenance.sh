#!/bin/sh
echo "Starting maintenance..."
mysql -u $BD_USER -p$BD_PW \
        -h us-cdbr-iron-east-03.cleardb.net -P 3306 \
        -D heroku_a304a837b2c7d20 \
        --execute="select count(*) from heroku_a304a837b2c7d20.ExportPOJO;delete from heroku_a304a837b2c7d20.ExportPOJO where updateTimestamp < DATE_SUB(curdate(), INTERVAL 1 MONTH) and accountName <> 'test';select count(*) from heroku_a304a837b2c7d20.ExportPOJO;optimize table heroku_a304a837b2c7d20.exportpojo;"
echo "Done maintenance"
