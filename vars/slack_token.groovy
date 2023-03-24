#!/usr/bin/env groovy

def call(Map config) {
  
  def channel = config.channel ?: error('No channel provided')                       // Channel Id 
  def slackToken = config.slackToken ?: error('No token provided')                   // Token to access the workspace
  def teamDomain = config.teamDomain ?: error("No teamDomain provided")              // Workspace name
  def message = config.message ?: ''                                                 // Customize message according to needs
  def pass = config.pass ?: 'S'                                                      // Status of the job [S(success),F(Fail)]
  def title = config.title ?: 'Build Successful'                                              // Any specific title to send
  def build_num = config.BUILD_NUM ?: env.BUILD_NUMBER
  def job_name = config.job_name ?: env.JOB_NAME
  def footer = config.footer ?: 'Jenkins'                                            // Name on footer
  def pretext = config.pretext ?: 'Jenkins'                                          // Pretext
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png' // Logo on footer
  
  
  def image_url = "https://ralfneubauer.info/wp-content/uploads/2021/01/ValueStream.gif"

<<<<<<< HEAD
  def color= '#006400'                                                               // Green for successful build
  if(pass == 'F'){                                                     
    color= '#FF0000'                                                                 // Red for fail build
    title = "Build Failed"
    image_url = "https://1b-f.s3.eu-west-1.amazonaws.com/a/76143-C9ABDAA4-8720-475C-8AAE-DCF8FC40EC05-0-1493951982.gif"
  }else if(pass == 'U'){
    color = '#FFFF00'
    title = "Build Successful with Instability"
    image_url = "https://ralfneubauer.info/wp-content/uploads/2021/01/OpsZapDev.gif"
=======
  def color= '#006400'                                                    // Green for successful build
  
  if(pass == 'F'){                                               
    color= '#FF0000'                                                      // Red for fail build
    title = "Build Failed"                                               // Status fo the build
  }else if(pass == 'U'){
    color = '#b0af3e'
    title = "Build Successfull (UNSTABLE)"
>>>>>>> main
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}" //Fetching the build trigger
  slackSend (    
    attachments: [
        [
          color: "${color}",
          pretext: "Notification From ${pretext}",
          title: "${title}",
          fallback: "${title}",
          image_url: "${image_url}",
          text: "${env.BUILD_TRIGGER_BY}\nJob Name: ${job_name}\nBuild Number: ${build_num}\nBuild Output: (<${env.BUILD_URL}console|Open>)\n${message}",
          footer: "${footer}",
          footer_icon: "${footerIcon}",
        ]
      ],
      channel: "${channel}",
      username: "Devops Team", 
      teamDomain:"${teamDomain}",
      tokenCredentialId: "${slackToken}",
  )
}
