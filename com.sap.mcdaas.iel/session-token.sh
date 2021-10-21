#!/bin/bash
########################################################################################################
# title           : session-token.sh								       #
# description     : This script will get a temporary session token for MFA (2FA) managed IAM accounts  #
# author  	  : Les Fleming								       	       #
# date            : 2021/10/06									       #
# version         : 1.0.0    									       #
# usage		  : session-token.sh -t add-mfa-token-here | source /tmp/aws-session		       #
# notes           : Tested with macOS Big Sur v11.6 						       #
#		    source will read and execute the export commands in the aws-session file in /tmp   #
#                   Session timeouts =  900 seconds (15 minutes) to 129600 seconds (36 hours).         #
#					86400 (24 hours)					       #
# bash_version    : 3.2										       #
########################################################################################################

# temp file name and location
fileName=/tmp/aws-session.sh

# delete existing file
rm -f $fileName

# lifetime of the session token in seconds ( min=900)
tokenLifespan=86400

# getopts to process cli argument
 while getopts t: flag
 do
  case "${flag}" in
     t) token=${OPTARG}
  esac
 done

# get session token
jsonResult=$(aws sts get-session-token --serial-number arn:aws:iam::310139436030:mfa/leslie.fleming@sap.com --token-code $token --profile default --duration-seconds $tokenLifespan)

# export aws global settings
echo "export AWS_ACCESS_KEY_ID=$(echo $jsonResult | awk '{print $5}' | cut -d , -f1)" > $fileName
echo "export AWS_SECRET_ACCESS_KEY=$(echo $jsonResult | awk '{ print $7}' | cut -d , -f1)" >> $fileName
echo "export AWS_SESSION_TOKEN=$(echo $jsonResult | awk '{ print $9}' | cut -d , -f1)" >> $fileName

AWS_SESSION_TOKEN_EXPIRY=$(echo $jsonResult | awk '{ print $11}'| cut -d , -f1)

# verify if export commands were added to /tmp/aws-session file
lineCount=$(wc -l < $fileName)

#ensure file is not empty
if [[ lineCount -eq 3 ]]; then
  $(chmod u+x $fileName)
fi

echo "AWS Session Details Exported. Token will expire " $AWS_SESSION_TOKEN_EXPIRY
