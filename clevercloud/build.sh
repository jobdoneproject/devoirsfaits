#!/bin/sh
echo "Copy deployment War file is started"
DEVOIRS_WAR=`ls target/*.war|grep war`
timestp = date -j -f "%m/%d/%y %H:%M:%S %p" now +"%m%d%y_%H%M%S"
 $DEVOIRS_WAR_DEST = devoirsfaits_$timestp.war
touch $DEVOIRS_WAR_DEST
chmod 755 $DEVOIRS_WAR_DEST
#curl -L http://mirrors.jenkins.io/war/latest/jenkins.war --output jenkins.war
echo "Configured CC_FS_BUCKET folder is : " {$CC_FS_BUCKET}
echo "...Copy " $DEVOIRS_WAR " to CC_FS_BUCKET folder as " $DEVOIRS_WAR_DEST

cp $DEVOIRS_WAR {$CC_FS_BUCKET}/$DEVOIRS_WAR_DEST

