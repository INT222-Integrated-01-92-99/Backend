pipeline {
    agent any

    tools {
        nodejs "nodejs"
    }

    stages {

        stage('(stop), remove container and image') {
            steps {
                script {
                    def haveImg = sh(script: 'docker images -q backend', returnStdout: true) == ""
                    println haveImg

                    if( !haveImg ){
                           sh 'docker stop ppclothesBE'
                           sh 'docker rm ppclothesBE'
                           sh 'docker image rm backend'
                    }else {
                        echo 'Skip this stage '
                    }
                }
            }
        }

        stage('remove whole data') {
            steps {
                sh 'rm -rf *'
            }
        }

        stage('git clone') {
            steps {
                git branch: 'main',
                    credentialsId: 'jenkinsid',
                    url: 'https://github.com/INT222-Integrated-01-92-99/Backend.git'
            }
        }

        stage('(deploy) start contianer') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('test') {
            steps {
                sh 'node --version '
                sh 'npm --version '
                sh 'npm install -g newman'
                sh 'newman run https://www.getpostman.com/collections/ead9f4c1b0c12b35303b'
            }
        }
    }
}
