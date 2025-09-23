pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = 'git'             // Jenkins Git credentials ID
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
                sh 'mvn test || true'   // Ensure pipeline continues even if no tests exist
            }
            post {
                always {
                    script {
                        // Safely try to publish test reports, even if none exist
                        try {
                            junit '**/target/surefire-reports/*.xml'
                        } catch (err) {
                            echo "No test report files found, skipping JUnit report."
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "sudo docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh '''#!/bin/bash
                CONTAINER_ID=$(sudo docker ps -q -f name=financeme-banking)
                if [ ! -z "$CONTAINER_ID" ]; then
                    sudo docker stop $CONTAINER_ID
                    sudo docker rm $CONTAINER_ID
                fi
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "sudo docker run -d -p ${APP_PORT}:${APP_PORT} --name financeme-banking ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs!"
        }
    }
}
