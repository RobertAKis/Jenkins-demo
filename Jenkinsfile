pipeline{
    agent any
    tools{
        maven '3.9.0'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout changelog: false, poll: false, scm: scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/RobertAKis/Jenkins-demo.git']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build --platform linux/amd64 -t kisrobert/jenkins-demo .'
                }
            }
        }
        stage('Push docker image'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpwd')]) {
                    sh 'docker login -u kisrobert -p ${dockerpwd}'
                    }
                    sh 'docker push kisrobert/jenkins-demo'
                }
            }
        }
        stage('Run docker image on ec2'){
           steps{
               script{
                withCredentials([sshUserPrivateKey(credentialsId: 'jenkins-secret', keyFileVariable: 'SSH_KEY_FILE')]){
                    sshagent(['jenkins-secret']) {
                            sh 'ssh -i $SSH_KEY_FILE ec2-user@ec2-3-76-222-242.eu-central-1.compute.amazonaws.com "docker pull kisrobert/jenkins-demo"'
                                try{
                                    sh '''
                                    ssh -i $SSH_KEY_FILE ec2-user@ec2-3-76-222-242.eu-central-1.compute.amazonaws.com "docker stop jenkins"
                                    ssh -i $SSH_KEY_FILE ec2-user@ec2-3-76-222-242.eu-central-1.compute.amazonaws.com "docker rm jenkins"
                                    '''
                                }catch(Exception e){
                                echo 'The container is not running!'
                    }
                    sh 'ssh -i $SSH_KEY_FILE ec2-user@ec2-3-76-222-242.eu-central-1.compute.amazonaws.com "docker run -p 9000:9000 --name jenkins -d kisrobert/jenkins-demo"'

                    }
                }
               }
            }
        }
    }
}
