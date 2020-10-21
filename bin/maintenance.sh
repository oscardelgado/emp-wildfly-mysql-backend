#!/bin/sh
echo "Starting maintenance..."
mysql -u Prueba -p 123456 BDPrueba < remove-old-rows.sql
echo "Done maintenance"
