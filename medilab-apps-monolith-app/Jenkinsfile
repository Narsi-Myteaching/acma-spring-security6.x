pipeline{
    agent any
    tools{
        maven "medilab-maven-code-build-tool"
    }
    stages{
      stage('pulling source code from scm'){
          steps{
             git branch: 'monolith-app',
             url: 'https://github.com/VSrihub/morning-devops-01.git'
             echo 'source code pulling completed'
          }
      }
      stage("building the source code"){
          steps{
              echo 'source code building started..'
              sh 'mvn clean package -DskipTests'
          }
      }
      stage("building the docker image"){
          steps{
              echo 'docker image building started..'
              sshagent(['medilab-ansible-controller-server']) {
                  sh 'scp Dockerfile ansibleadmin@172.31.42.12:/home/ansibleadmin'
                  sh 'scp target/medilab-morning-preclinic.war ansibleadmin@172.31.42.12:/home/ansibleadmin/target'
                  sh 'scp medilab-app-build-playbook.yml ansibleadmin@172.31.42.12:/home/ansibleadmin'
                  sh '''
                    ssh -tt ansibleadmin@172.31.42.12 << EOF 
                       ansible docker-host -m copy "src=/home/ansibleadmin/Docker dest=."
                       ansible docker-host -m copy -a "src=/home/ansibleadmin/target dest=target"
                       ansible-playbook medilab-app-build-playbook.yml
                       exit
                       EOF
                  '''
              }
          }
          
      }
      stage("trigger application deploy job"){
          steps{
              build job: 'medilab-preclinic-application-for-qa-deploy-pipeline'
          }
      }
    
    }
}
