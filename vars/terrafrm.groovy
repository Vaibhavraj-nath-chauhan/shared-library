#!/usr/bin/env groovy

def call(Map config){

    def secretToken = config.secretToken ?:error("No token Provided")
    def varName = config.varName ?: "setup"
    def awsProfile = config.awsProfile ?: error("No prfile provided")
    def awsRegion = config.awsRegion ?: error("No region provided")
    def workDir = config.workDir ?: error("No working Directory provided")
    def terraformDockerImage = "msshahanshah/tools:terrform02"
    def imgTag = config.imgTag ?: "${param.ImageTag}"


    withCredentials([file(credentialsId: secretToken, variable: "setup")]) {
//                 def AWS_CREDENTIALS = sh (
//                     script: """
//                         echo ${varName} | awk '{print \"-e\", \$1, \"-e\", \$2}'
//                     """,
//                     returnStdout: true
//                 ).trim()
                sh "echo ${setup} > data1"
//                 sh "set +x; docker run  ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w ${workDir} ${terraformDockerImage} sleep 10000"
//                 sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w ${workDir} ${terraformDockerImage} terraform plan -var image_number=${imgTag}"
                //sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=accel -e AWS_REGION=us-east-1 -v ${workspace}/:/code -w /code/accel-ec2/services-prod/website-comparison msshahanshah/tools:terrform01 terraform apply -var image_number=${img_tag}"

        }
}
