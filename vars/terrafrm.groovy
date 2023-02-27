#!/usr/bin/env groovy

def call(Map config, String init="init"){

    def secretToken = config.secretToken ?:error("No token Provided")
    def awsProfile = config.awsProfile ?: error("No prfile provided")
    def awsRegion = config.awsRegion ?: error("No region provided")
    def workDir = config.workDir ?: error("No working Directory provided")
    def terraformDockerImage = "msshahanshah/tools:terrform02"
    def imgTag = config.imgTag ?: "${param.ImageTag}"


    withCredentials([string(credentialsId: "${secretToken}", variable: "setup")]) {
                def AWS_CREDENTIALS = sh (
                    script: """
                        echo ${setup} | awk '{print \"-e\", \$1, \"-e\", \$2}'
                    """,
                    returnStdout: true
                ).trim()
        if (init == "init"){
         sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w ${workDir} ${terraformDockerImage} terraform init"   
        }
        else if(init == "plan"){
             sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w ${workDir} ${terraformDockerImage} terraform plan -var image_number=${imgTag}"
        }
       
                //sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=accel -e AWS_REGION=us-east-1 -v ${workspace}/:/code -w /code/accel-ec2/services-prod/website-comparison msshahanshah/tools:terrform01 terraform apply -var image_number=${img_tag}"

        }
}
