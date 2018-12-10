#!/bin/bash
echo $SYSTEMCONFIG >/usr/src/app/qloudmart/config/config.json
node /usr/src/app/qloudmart/app.js
