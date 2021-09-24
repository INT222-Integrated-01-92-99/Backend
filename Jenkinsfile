pipeline {
    agent any

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

    }
}
