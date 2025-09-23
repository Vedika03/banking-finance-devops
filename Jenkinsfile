pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = 'git'
        DOCKER_IMAGE = 'financeme-banking:latest'
        APP_PORT = '8081'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Vedika03/banking-finance-devops.git',
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'sudo docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh 'if [ $(sudo docker ps -q -f name=financeme-banking) ]; then ' +
                   'sudo docker stop financeme-banking; ' +
                   'sudo docker rm financeme-banking; ' +
                   'fi'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'sudo docker run -d -p ${APP_PORT}:${APP_PORT} --name financeme-banking ${DOCKER_IMAGE}'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs!'
        }
    }
}
